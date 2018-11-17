/**
 * Created: 2017-01-09
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES对称加密工具
 *
 * 支持的加密方式有DES,DESede(TripleDES,就是3DES),AES,Blowfish,RC2,RC4(ARCFOUR)<br/>
 * 详情请参照JDK文档:<a href="http://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html">Java加密体系加密供应商文档</a>
 * 中的Keysize Restrictions章节
 * <ul>
 * <li>DES:密钥长度56位,默认长度56</li>
 * <li>DESede(TripleDES):密钥长度112或168位,默认长度168</li>
 * <li>AES:密钥长度128,192,256位,默认长度128</li>
 * <li>Blowfish:密钥长度[32,448]且必须是8的倍数,默认长度128</li>
 * <li>RC2:密钥长度[40,1024]位,默认长度128</li>
 * <li>RC4(ARCFOUR):密钥长度[40,1024]位,默认长度128</li>
 * </ul>
 *
 * @author junjie.ge
 *
 */
public abstract class DESCoder extends Coder {
	private static final Logger LOG = LoggerFactory.getLogger(DESCoder.class);
	/** DES加密算法 **/
	public static final String ALGORITHM_DES = "DES";
	/** 3DES加密算法 **/
	public static final String ALGORITHM_3DES = "DESede";
	/** AES加密算法 **/
	public static final String ALGORITHM_AES = "AES";
	/** Blowfish加密算法 **/
	public static final String ALGORITHM_Blowfish = "Blowfish";
	/** RC2加密算法 **/
	public static final String ALGORITHM_RC2 = "RC2";
	/** RC4加密算法 **/
	public static final String ALGORITHM_RC4 = "ARCFOUR";

	/**
	 * 密钥生成器
	 * @param algorithm 加密算法名称(本工具类提供静态常量)
	 * @return 密钥字符串
	 * @throws Exception
	 */
	public static String keyGen(String algorithm) throws Exception {
		return keyGen(null, algorithm);
	}

	/**
	 * 密钥生成器,如果不知道选取的算法对应的密钥长度,请慎用本方法
	 * @param seed 密钥种子,对于不同的算法,有不同的bit长度要求,具体长度请参照本类说明
	 * @param algorithm 加密算法名称(本工具类提供静态常量)
	 * @return 密钥字符串
	 * @throws Exception
	 */
	public static String keyGen(String seed, String algorithm) throws Exception {
		SecureRandom secureRandom = null;
		if (seed != null) {
			secureRandom = new SecureRandom(base64Decode(seed.getBytes(StandardCharsets.ISO_8859_1)));
		} else {
			secureRandom = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		String result = new String(base64Encode(secretKey.getEncoded()), StandardCharsets.ISO_8859_1);
		LOG.debug("生成的密钥为:{}", result);
		return result;
	}

	/**
	 * 加密方法
	 * @param src 源byte数组
	 * @param key 密钥字符串
	 * @param algorithm 加密算法名称(本工具类提供静态常量)
	 * @return 密文byte数组
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, String key, String algorithm) throws Exception {
		Key k = toKey(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(src);
	}
	/**
	 * 解密方法
	 * @param src 源byte数组
	 * @param key 密钥字符串
	 * @param algorithm 加密算法名称(本工具类提供静态常量)
	 * @return 密文byte数组
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String key, String algorithm) throws Exception {
		Key k = toKey(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(src);
	}

	/**
	 * 由于密钥转换方式的不同,需要把DES加密和其他的加密方式分开转换
	 * @param key 密钥byte数组
	 * @param algorithm 加密算法名称(本工具类提供静态常量)
	 * @return 密钥对象
	 * @throws Exception
	 */
	private static Key toKey(String key, String algorithm) throws Exception {
		SecretKey secretKey = null;
		if (key == null || key.isEmpty()) {
			return secretKey;
		}
		byte[] keyBytes = base64Decode(key.getBytes(StandardCharsets.ISO_8859_1));
		if (ALGORITHM_DES.equals(algorithm)) {
			DESKeySpec dks = new DESKeySpec(keyBytes);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);
			secretKey = keyFactory.generateSecret(dks);
		} else {
			// 当使用其他对称加密算法时，如AES、Blowfish等算法时
			secretKey = new SecretKeySpec(keyBytes, algorithm);
		}
		return secretKey;
	}
}
