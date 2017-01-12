/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.encrypt.keypair;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

import net.fantesy84.encrypt.Coder;

/**
 * @author junjie.ge
 *
 */
public class DSAKeyPair extends StoredKeyPair {
	private static final long serialVersionUID = -110131066715981639L;

	/**
	 * @param publicKey 公钥对象
	 * @param privateKey 私钥对象
	 */
	public DSAKeyPair(PublicKey publicKey, PrivateKey privateKey) {
		super(publicKey, privateKey);
	}

	/**
	 * 获取公钥字符串
	 * @return 通过base64加密后的公钥字符串,使用ISO_8859_1编码
	 * @throws Exception
	 */
	public String getPublic() throws Exception {
		return new String(Coder.base64Encode(super.getPublicKey().getEncoded()), StandardCharsets.ISO_8859_1);
	}
	/**
	 * 获取私钥字符串
	 * @return 通过base64加密后的私钥字符串,使用ISO_8859_1编码
	 * @throws Exception
	 */
	public String getPrivate() throws Exception {
		return new String(Coder.base64Encode(super.getPrivateKey().getEncoded()), StandardCharsets.ISO_8859_1);
	}
}
