/**
 * Created: 2017-01-09
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <h1>基础加密工具类</h1>
 * <p>
 * 基础加密算法包含四种:
 * <ul>
 * <li>Base64 <p>详情请参加{@link Base64},可逆加密</p></li>
 * <li>MD5 <p>信息摘要算法(Message Digest algorithm 5),单向不可逆加密</p></li>
 * <li>SHA <p>安全散列算法(Secure Hash Algorithm),单向不可逆加密</p></li>
 * <li>HMAC <p>散列消息鉴别码(Hash Message Authentication Code),单向不可逆加密</p></li>
 * </ul>
 * </p>
 * @author junjie.ge
 *
 */
public abstract class Coder {
	private static final Logger LOG = LoggerFactory.getLogger(Coder.class);
	/** MD5加密算法 **/
	public static final String ALGORITHM_MD5 = "MD5";
	/** SHA加密算法 **/
	public static final String ALGORITHM_SHA = "SHA";
	/** HmacMD5加密算法 **/
	public static final String ALGORITHM_HMAC_MD5 = "HmacMD5";
	/** HmacSHA1加密算法 **/
	public static final String ALGORITHM_HMAC_SHA1 = "HmacSHA1";
	/** HmacSHA256加密算法 **/
	public static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";
	/** HmacSHA384加密算法 **/
	public static final String ALGORITHM_HMAC_SHA384 = "HmacSHA384";
	/** HmacSHA512加密算法 **/
	public static final String ALGORITHM_HMAC_SHA512 = "HmacSHA512";

	/**
	 * Base64加密(基础加密)
	 * @param src 源byte数组
	 * @return 加密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64Encode(byte[] src) throws Exception {
		return Base64.getEncoder().encode(src);
	}
	/**
	 * Base64加密URL字符串
	 * @param src 源byte数组
	 * @return 加密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64EncodeURL(byte[] src) throws Exception {
		return Base64.getUrlEncoder().encode(src);
	}
	/**
	 * Base64加密Mime数据
	 * @param src 源byte数组
	 * @return 加密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64EncodeMime(byte[] src) throws Exception {
		return Base64.getMimeEncoder().encode(src);
	}
	/**
	 * Base64加密Mime数据,可指定行长度(默认76)和分隔符(默认为"\r"或"\n")
	 * @param src 源byte数组
	 * @return 加密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64EncodeMime(byte[] src, int lineLength, byte[] lineSeparator) throws Exception {
		return Base64.getMimeEncoder(lineLength, lineSeparator).encode(src);
	}
	/**
	 * Base64解密(基础解密)
	 * @param src 密文byte数组
	 * @return 解密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64Decode(byte[] src) throws Exception {
		return Base64.getDecoder().decode(src);
	}
	/**
	 * Base64解密URL密文
	 * @param src 密文byte数组
	 * @return 解密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64DecodeURL(byte[] src) throws Exception {
		return Base64.getUrlDecoder().decode(src);
	}
	/**
	 * Base64解密Mime数据密文
	 * @param src 密文byte数组
	 * @return 解密后的byte数组
	 * @throws Exception
	 */
	public static byte[] base64DecodeMime(byte[] src) throws Exception {
		return Base64.getMimeDecoder().decode(src);
	}
	/**
	 * MD5加密
	 * @param src 源byte数组
	 * @return 密文byte数组
	 * @throws Exception
	 */
	public static byte[] md5(byte[] src) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM_MD5);
		return digest.digest(src);
	}
	/**
	 * SHA加密
	 * @param src 源byte数组
	 * @return 密文byte数组
	 * @throws Exception
	 */
	public static byte[] sha(byte[] src) throws Exception {
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM_SHA);
		return digest.digest(src);
	}
	/**
	 * HMAC密钥生成器
	 * @param algorithm HMAC算法名称(本工具可通过静态常量选取)
	 * @return 密钥字符串(ISO-8859-1编码)
	 * @throws Exception
	 */
	public static String macKeyGen(String algorithm) throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
		SecretKey secretKey = keyGen.generateKey();
		byte[] content = secretKey.getEncoded();
		String result = new String(base64Encode(content), StandardCharsets.ISO_8859_1);
		LOG.debug("生成HMAC加密密钥: {}", result);
		return result;
	}

	/**
	 * HMAC加密
	 * @param src 源byte数组
	 * @param algorithm HMAC算法名称(本工具可通过静态常量选取)
	 * @param key 密钥字符串(必须是ISO-8859-1编码)
	 * @return 密文byte数组
	 * @throws Exception
	 */
	public static byte[] hmac(byte[] src, String algorithm, String key) throws Exception {
		if (key != null && !key.isEmpty()) {
			byte[] keyBytes = base64Decode(key.getBytes(StandardCharsets.ISO_8859_1));
			SecretKey secretKey = new SecretKeySpec(keyBytes, algorithm);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			return mac.doFinal(src);
		}
		return null;
	}
	/**
	 * 将byte数组转换为16进制字符串
	 * @param data 密文byte数组
	 * @return 密文16进制字符串
	 */
	public static String toHexString(byte[] data) {
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < data.length; i++){
			int val = (data[i]) & 0xff;
			if (val < 16){
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
