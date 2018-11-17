/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import net.fantesy84.encrypt.keypair.DSAKeyPair;

/**
 * DSA主要用于数字签名加密,私钥加密生成数字签名,公钥验证数据及签名.<br/>
 * 如果数据和签名不匹配则认为验证失败!传输中可不再进行二次加密<br/>
 * Keysize must be a multiple of 64, ranging from 512 to 1024 (inclusive), or
 * 2048.<br/>
 *
 * @author junjie.ge
 *
 */
public abstract class DSACoder extends Coder {
	private static final String ALGORITHM = "DSA";
	public static final int DEFAULT_KEY_SIZE = 1024;
	public static final String SIGNATURE_SHA1 = "SHA1withDSA";
	public static final String SIGNATURE_SHA256 = "SHA256withDSA";

	public static DSAKeyPair init() throws Exception {
		return init(DEFAULT_KEY_SIZE);
	}

	public static DSAKeyPair init(int keysize) throws Exception {
		KeyPairGenerator kpgen = KeyPairGenerator.getInstance(ALGORITHM);
		kpgen.initialize(keysize);
		KeyPair keys = kpgen.genKeyPair();
		DSAKeyPair kp = new DSAKeyPair(keys.getPublic(), keys.getPrivate());
		return kp;
	}

	public static String sign(byte[] data, String privateKey, String signatureAlgorithm) throws Exception {
		byte[] keyBytes = base64Decode(privateKey.getBytes(StandardCharsets.ISO_8859_1));
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey priKey = factory.generatePrivate(keySpec);// 生成 私钥
		// 用私钥对信息进行数字签名
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initSign(priKey);
		signature.update(data);
		return new String(base64Encode(signature.sign()), StandardCharsets.ISO_8859_1);
	}

	public static boolean verify(byte[] data, String publicKey, String signatureAlgorithm, String sign)
			throws Exception {
		byte[] keyBytes = base64Decode(publicKey.getBytes(StandardCharsets.ISO_8859_1));
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(signatureAlgorithm);
		signature.initVerify(pubKey);
		signature.update(data);
		return signature.verify(base64Decode(sign.getBytes(StandardCharsets.ISO_8859_1))); // 验证签名
	}
}
