/**
 * Created: 2017-01-12
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.encrypt;

import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import net.fantesy84.encrypt.PBECoder;

/**
 * @author junjie.ge
 *
 */
public class PBECoderTest {
	private String pwd = "admin123";
	private byte[] salt;
	private byte[] data;
	@Before
	public void init() {
		salt = PBECoder.initSalt();
		data = "test information".getBytes(StandardCharsets.ISO_8859_1);
	}

	@Test
	public void testEncrypt() throws Exception {
		byte[] encrypt = PBECoder.encrypt(data, pwd, salt, PBECoder.ALGORITHM_PBE_MD5_DES);
		byte[] decrypt = PBECoder.decrypt(encrypt, pwd, salt, PBECoder.ALGORITHM_PBE_MD5_DES);
		System.out.println(new String(decrypt));
	}
}
