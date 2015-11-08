/**
 * Project gb-platform-sys-common
 * File: MD5Utils.java
 * CreateTime: 2015年10月17日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.common.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TypeName: MD5Utils
 * <P>本工具类包含MD5加密,Base64加密算法模板的加密和解密.
 * 
 * <P>CreateTime: 2015年10月17日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class EncryptUtils {
	private static final Logger logger = Logger.getLogger(EncryptUtils.class.getName());
	static Charset charset = StandardCharsets.UTF_8;
	
	/**
	 * 使用MD5算法加密指定字符串
	 * @param str 源字符串
	 * @return 加密后字符串
	 */
	public static String encryptWithMD5(String str) {
		byte[] bytes = digest(str);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < bytes.length; i++){
			int val = ((int) bytes[i]) & 0xff;
			if (val < 16){
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	/**
	 * 采用RFC4648算法进行加密
	 * @param str 源字符串
	 * @return 加密后字符串
	 * @see java.util.Base64.Encoder
	 */
	public static String encryptWithBase64Encoder(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes(charset));
	}
	
	/**
	 * 采用RFC4648算法进行解密
	 * @param str 源字符串
	 * @return 解密后字符串
	 * @see java.util.Base64.Decoder
	 */
	public static String decryptWithBase64Encoder(String str) {
		return new String(Base64.getDecoder().decode(str.getBytes(charset)));
	}
	
	/**
	 * 采用RFC2045算法进行加密
	 * @param str 源字符串
	 * @return 加密后字符串
	 * @see java.util.Base64.Encoder
	 */
	public static String encryptWithBase64MimeEncoder(String str) {
		return Base64.getMimeEncoder().encodeToString(str.getBytes(charset));
	}
	
	/**
	 * 采用RFC2045算法进行加密
	 * @param str 源字符串
	 * @param lineLength 单行输出最大长度
	 * @param lineSeparator 换行符
	 * @return 加密后字符串
	 * @see java.util.Base64.Encoder
	 */
	public static String encryptWithBase64MimeEncoder(String str, int lineLength, byte[] lineSeparator) {
		return Base64.getMimeEncoder(lineLength, lineSeparator).encodeToString(str.getBytes(charset));
	}
	
	/**
	 * 采用RFC2045算法进行解密
	 * @param str 源字符串
	 * @return 解密后字符串
	 * @see java.util.Base64.Decoder
	 */
	public static String decryptWithBase64MimeEncoder(String str) {
		return new String(Base64.getMimeDecoder().decode(str.getBytes(charset)));
	}
	
	/**
	 * 将RFC4648算法最后两个元字符改为('-', '_'),将不影响URL格式.可用于加密URL
	 * @param str 源字符串
	 * @return 加密后字符串
	 * @see java.util.Base64.Encoder
	 */
	public static String encryptWithBase64UrlEncoder(String str) {
		return Base64.getUrlEncoder().encodeToString(str.getBytes(charset));
	}
	
	/**
	 * 将RFC4648算法最后两个元字符改为('-', '_'),将不影响URL格式.可用于解密URL
	 * @param str 源字符串
	 * @return 解密后字符串
	 * @see java.util.Base64.Decoder
	 */
	public static String decryptWithBase64UrlEncoder(String str) {
		return new String(Base64.getUrlDecoder().decode(str.getBytes(charset)));
	}
	
	private static byte[] digest(String str) {
		if (str == null) {
			logger.log(Level.WARNING, "参数为空!将返回空字节数组");
			return new byte[]{};
		}
		MessageDigest md5 = getMD5instance();
		byte[] bytes = null;
		bytes = md5.digest(str.getBytes(charset));
		return bytes;
	}
	
	private static MessageDigest getMD5instance(){
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE, "未找到MD5加密算法类", e);
			e.printStackTrace();
		}
		return md5;
	}
}
