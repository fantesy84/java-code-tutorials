/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import net.fantesy84.encrypt.keypair.RSAKeyPair;

/**
 * RSA非对称加密常用于单向验证和加解密密钥长度必须在512bit至65536bit之间,默认长度1024<br/>
 * 数字证书可保证特定的对方(拥有该私钥对应的公钥)才可以通过验签并且解密
 * 详情请参阅: <a href=
 * "http://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunRsaSignProvider">RSA验签</a>
 * <p>
 * 校验算法暂时提供两种: MD5withRSA, SHA1withRSA
 *
 * @author junjie.ge
 *
 */
public abstract class RSACoder extends Coder {
	private static final String ALGORITHM = "RSA";
	private static final int DEFAULT_KEY_SIZE = 1024;
	public static final String SIGNATURE_MD5 = "MD5withRSA";
	public static final String SIGNATURE_SHA1 = "SHA1withRSA";

	/**
	 * 采用默认密钥长度初始化的密钥对
	 *
	 * @return RSA密钥对对象
	 * @throws Exception
	 */
	public static RSAKeyPair initKey() throws Exception {
		return initKey(DEFAULT_KEY_SIZE);
	}

	/**
	 * 初始化密钥对
	 *
	 * @param keysize
	 *            密钥长度,默认1024bits
	 * @return RSA密钥对对象
	 * @throws Exception
	 */
	public static RSAKeyPair initKey(int keysize) throws Exception {
		RSAKeyPair keyPair = null;
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGen.initialize(keysize);
		KeyPair kp = keyPairGen.generateKeyPair();
		keyPair = new RSAKeyPair(kp.getPublic(), kp.getPrivate());
		return keyPair;
	}

	/**
	 * 使用公钥加密数据
	 *
	 * @param data
	 *            源数据
	 * @param publicKey
	 *            公钥字符串
	 * @return 密文数据
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		// 解密公钥
		byte[] keyBytes = base64Decode(publicKey.getBytes(StandardCharsets.ISO_8859_1));
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key key = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 使用私钥加密数据
	 *
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥字符串
	 * @return 密文数据
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		// 解密私钥
		byte[] keyBytes = base64Decode(privateKey.getBytes(StandardCharsets.ISO_8859_1));
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key key = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 使用公钥解密数据
	 *
	 * @param data
	 *            密文数据
	 * @param publicKey
	 *            公钥字符串
	 * @return 明文数据
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String publicKey) throws Exception {
		// 对密钥解密
		byte[] keyBytes = base64Decode(publicKey.getBytes(StandardCharsets.ISO_8859_1));
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key key = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 使用私钥解密数据
	 *
	 * @param data
	 *            密文数据
	 * @param privateKey
	 *            私钥字符串
	 * @return 明文数据
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		// 对密钥解密
		byte[] keyBytes = base64Decode(privateKey.getBytes(StandardCharsets.ISO_8859_1));
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key key = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(data);
	}

	/**
	 * 校验数字签名
	 *
	 * @param data
	 *            数字签名数据
	 * @param publicKey
	 *            公钥
	 * @param signatureAlgorithm
	 *            验签算法,本工具类提供静态常量
	 * @param sign
	 *            数字签名
	 * @return 通过返回true,失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String signatureAlgorithm, String sign)
			throws Exception {
		// 解密由base64编码的公钥
		byte[] keyBytes = base64Decode(publicKey.getBytes(StandardCharsets.ISO_8859_1));
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initVerify(pubKey);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(base64Decode(sign.getBytes(StandardCharsets.ISO_8859_1)));
	}

	/**
	 * 对指定数据生成数字签名
	 * @param data 源数据
	 * @param privateKey 私钥
	 * @param signatureAlgorithm 验签算法,本工具类提供静态常量
	 * @return 数字签名字符串
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey, String signatureAlgorithm) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = base64Decode(privateKey.getBytes(StandardCharsets.ISO_8859_1));
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initSign(priKey);
		signature.update(data);
		return new String(base64Encode(signature.sign()), StandardCharsets.ISO_8859_1);
	}
}
