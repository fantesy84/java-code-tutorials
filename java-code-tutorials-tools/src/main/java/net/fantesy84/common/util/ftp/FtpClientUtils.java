package net.fantesy84.common.util.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FtpClientUtils {
    private static final Logger LOGGER = Logger.getLogger(FtpClientUtils.class.getName());
    private FTPClient ftpClient;

    private FtpClientUtils(String host, int port, String username, String passwd) {
        this.ftpClient = new FTPClient();
        this.ftpClient.setControlEncoding("UTF-8");
        LOGGER.log(Level.FINE, String.format("连接FTP服务器:%s,端口:%d,用户名:%s,密码:%s", host, port, username, passwd));
        try {
            this.ftpClient.connect(host, port);
            this.ftpClient.login(username, passwd);
            int replyCode = this.ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                LOGGER.log(Level.WARNING, "连接失败!返回码:" + replyCode);
            }
            LOGGER.log(Level.FINE, "连接成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FtpClientUtils init(String host, int port, String username, String passwd) {
        return new FtpClientUtils(host, port, username, passwd);
    }

    public boolean upload(String serverPath, String serverFilename, String originFilePath) {
        try(InputStream in = new FileInputStream(new File(originFilePath))) {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            boolean exsitDir = ftpClient.changeWorkingDirectory(serverPath);
            if (!exsitDir) {
                ftpClient.makeDirectory(serverPath);
                ftpClient.changeWorkingDirectory(serverPath);
            }
            boolean store = ftpClient.storeFile(serverFilename, in);
            if (store) {
                return true;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return false;
    }

    public boolean download(String serverPath, String serverFile, String localPath) {
        try (OutputStream out = new FileOutputStream(new File(localPath))) {
            ftpClient.changeWorkingDirectory(serverPath);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setRemoteVerificationEnabled(false);
            boolean retrieveFile = ftpClient.retrieveFile(serverFile, out);
            if (retrieveFile) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void release() {
        try {
            this.ftpClient.logout();
            this.ftpClient.disconnect();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
