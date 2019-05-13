package net.fantesy84.test.ftp;

import net.fantesy84.common.util.ftp.FtpClientUtils;
import org.junit.Test;

public class FtpClientUtilsTest {
    @Test
    public void test0() {
        FtpClientUtils fcu = FtpClientUtils.init("172.28.134.233", 21, "ss", "12345678");
        fcu.upload("/", "20190514.txt", "d:/20190514003400.txt");
        fcu.release();
    }
}
