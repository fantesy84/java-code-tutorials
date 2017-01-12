/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt.keypair;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import net.fantesy84.encrypt.Coder;

/**
 * RSA密钥对
 * @author junjie.ge
 *
 */
public class RSAKeyPair extends StoredKeyPair {
	private static final long serialVersionUID = 1410644994275590910L;

	public RSAKeyPair(PublicKey publicKey, PrivateKey privateKey) {
		super(publicKey, privateKey);
	}

	/**
	 * 获取公钥字符串
	 * @return 通过base64加密后的公钥字符串,使用ISO_8859_1编码
	 * @throws Exception
	 */
	public String getPublic() throws Exception {
		RSAPublicKey publicKey = (RSAPublicKey) super.getPublicKey();
		byte[] keyBytes = Coder.base64Encode(publicKey.getEncoded());
		return new String(keyBytes, StandardCharsets.ISO_8859_1);
	}

	/**
	 * 获取私钥字符串
	 * @return 通过base64加密后的私钥字符串,使用ISO_8859_1编码
	 * @throws Exception
	 */
	public String getPrivate() throws Exception {
		RSAPrivateKey publicKey = (RSAPrivateKey) super.getPrivateKey();
		byte[] keyBytes = Coder.base64Encode(publicKey.getEncoded());
		return new String(keyBytes, StandardCharsets.ISO_8859_1);
	}

}
