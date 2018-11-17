/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.encrypt;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.fantesy84.encrypt.RSACoder;
import net.fantesy84.encrypt.keypair.RSAKeyPair;

/**
 * @author junjie.ge
 *
 */
public class RSACoderTest {
	private String src = "这是源字符串";
	private RSAKeyPair kp;
	@Before
	public void init() throws Exception {
		kp = RSACoder.initKey(2048);
		System.out.println("公钥:" + kp.getPublic());
		System.out.println("私钥:" + kp.getPrivate());
	}
	@Test
	public void testEncryptAndDecrypt() throws Exception {
		byte[] data = src.getBytes(StandardCharsets.UTF_8);
		// 服务器加密后客户端解密
		byte[] encryptByPrivateKey = RSACoder.encryptByPrivateKey(data, kp.getPrivate());
		byte[] decryptByPublicKey = RSACoder.decryptByPublicKey(encryptByPrivateKey, kp.getPublic());
		Assert.assertEquals(src, new String(decryptByPublicKey, StandardCharsets.UTF_8));
		// 客户端加密后服务端解密
		byte[] encryptByPublicKey = RSACoder.encryptByPublicKey(data, kp.getPublic());
		byte[] decryptByPrivateKey = RSACoder.decryptByPrivateKey(encryptByPublicKey, kp.getPrivate());
		Assert.assertEquals(src, new String(decryptByPrivateKey, StandardCharsets.UTF_8));
	}

	@Test
	public void testSign() throws Exception {
		byte[] data = src.getBytes(StandardCharsets.UTF_8);
		// 服务器签发数字证书
		String sign = RSACoder.sign(data, kp.getPrivate(), RSACoder.SIGNATURE_MD5);
		// 客户端验签
		boolean verify = RSACoder.verify(data, kp.getPublic(), RSACoder.SIGNATURE_MD5, sign);
		Assert.assertTrue(verify);
	}
}
