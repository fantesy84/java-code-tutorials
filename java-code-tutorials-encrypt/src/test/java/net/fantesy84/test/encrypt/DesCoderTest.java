/**
 * Created: 2017-01-10
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.fantesy84.encrypt.DESCoder;

/**
 * @author junjie.ge
 *
 */
public class DesCoderTest {
	private String key;
	private String data1;
	private byte[] data2;
	@Before
	public void init() throws Exception {
		key = DESCoder.keyGen(DESCoder.ALGORITHM_3DES);
		data1 = "0123456789~!@#$%^&*()_+abcdefg中文说明";
		byte[] buffer = new byte[4];
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(new File("src/test/resources/test.rar"));
		int n;
		while ((n = fis.read(buffer)) != -1) {
			output.write(buffer, 0, n);
		}
		fis.close();
		data2 = output.toByteArray();
		output.close();
	}

	@Test
	public void testKey() throws Exception {
		System.out.println("3DES算法的KEY:" + key);
	}

	@Test
	public void testEncrypt() throws Exception {
		byte[] encrypt = DESCoder.encrypt(data1.getBytes(), key, DESCoder.ALGORITHM_3DES);
		byte[] decrypt = DESCoder.decrypt(encrypt, key, DESCoder.ALGORITHM_3DES);
		Assert.assertEquals(data1, new String(decrypt));
	}

	@Test
	public void testFileEncrypt() throws Exception {
		byte[] encrypt = DESCoder.encrypt(data2, key, DESCoder.ALGORITHM_3DES);
		byte[] decrypt = DESCoder.decrypt(encrypt, key, DESCoder.ALGORITHM_3DES);
		Assert.assertEquals(data2.length, decrypt.length);
		FileOutputStream fos = new FileOutputStream("src/test/resources/test-encrypt.rar");
		fos.write(decrypt);
		fos.close();
	}
}
