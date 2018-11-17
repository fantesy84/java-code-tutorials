/**
 * Created: 2017-01-10
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * PBE: Password-based
 * encryption,基于密码加密,其特点在于口令由用户自己掌管,不借助任何物理媒体;采用随机数杂凑多重加密等方法保证数据的安全性,是一种简便的加密方式.
 *
 * @author junjie.ge
 *
 */
public abstract class PBECoder extends Coder {
	/** MD5加密算法 **/
	public static final String ALGORITHM_PBE_MD5_DES = "PBEWithMD5AndDES";
	/** HmacMD5加密算法 **/
	public static final String ALGORITHM_PBE_SHA1_3DES = "PBEWithSHA1AndDESede";
	/** HmacSHA1加密算法 **/
	public static final String ALGORITHM_PBE_SHA1_RC2 = "PBEWithSHA1AndRC2_40";

	public static byte[] initSalt() {
		byte[] randomBytes = new byte[8];
		Random r = new Random();
		r.nextBytes(randomBytes);
		return randomBytes;
	}

	public static byte[] encrypt(byte[] data, String password, byte[] salt, String algorithm) throws Exception {
		Key key = toKey(password, algorithm);
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 512);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		return cipher.doFinal(data);
	}

	public static byte[] decrypt(byte[] data, String password, byte[] salt, String algorithm) throws Exception {
		Key key = toKey(password, algorithm);
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 512);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		return cipher.doFinal(data);
	}

	private static Key toKey(String password, String algorithm) throws Exception {
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		return secretKey;
	}

}
