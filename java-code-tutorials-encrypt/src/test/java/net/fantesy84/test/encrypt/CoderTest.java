/**
 * Created: 2017-01-09
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.encrypt.Coder;

/**
 * @author junjie.ge
 *
 */
public class CoderTest {
	private static final Logger LOG = LoggerFactory.getLogger(CoderTest.class);

	@Test
	public void testBase64_1() throws Exception {
		String str1 = "https://www.baidu.com/s?wd=Java%20%E6%95%B0%E5%AD%97%E8%AF%81%E4%B9%A6&rsv_spt=1&rsv_iqid=0x8900b59e00015974&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=98012088_5_dg&rsv_enter=1&rsv_sug3=8&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&inputT=3789&rsv_sug4=4642";
		byte[] utf8Bytes = str1.getBytes(StandardCharsets.UTF_8);
		byte[] latinBytes = str1.getBytes(StandardCharsets.ISO_8859_1);
		Assert.assertNotEquals(utf8Bytes, latinBytes);
		String encryptStr1 = new String(Coder.base64Encode(utf8Bytes), StandardCharsets.UTF_8);
		String encryptStr2 = new String(Coder.base64Encode(latinBytes), StandardCharsets.ISO_8859_1);
		LOG.info("UTF-8解码后加密密文:{}", encryptStr1);
		LOG.info("ISO_8859_1解码后加密密文:{}", encryptStr2);
		Assert.assertEquals(encryptStr1, encryptStr2);
		String decryptStr1 = new String(Coder.base64Decode(encryptStr1.getBytes(StandardCharsets.UTF_8)),
				StandardCharsets.UTF_8);
		LOG.info("UTF-8编码后解密原文:{}", decryptStr1);
		Assert.assertEquals(decryptStr1, str1);
	}

	@Test
	public void testBase64_2() throws Exception {
		String str1 = "https://www.baidu.com/s?wd=Java%20%E6%95%B0%E5%AD%97%E8%AF%81%E4%B9%A6&rsv_spt=1&rsv_iqid=0x8900b59e00015974&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=98012088_5_dg&rsv_enter=1&rsv_sug3=8&rsv_sug1=6&rsv_sug7=100&rsv_sug2=0&inputT=3789&rsv_sug4=4642";
		byte[] src = str1.getBytes(StandardCharsets.ISO_8859_1);
		byte[] encryptBytes = Coder.base64EncodeURL(src);
		String encryptString = new String(encryptBytes, StandardCharsets.ISO_8859_1);
		LOG.info("密文值:\n{}", encryptString);
	}

	@Test
	public void testBase64_3() throws Exception {
		String str1 = "abcde,fghi;jklm.nopq\rrstuvwxyz";
		byte[] src = str1.getBytes(StandardCharsets.ISO_8859_1);
		byte[] encryptBytes = Coder.base64EncodeMime(src, 16, "\r".getBytes());
		String encryptString = new String(encryptBytes, StandardCharsets.ISO_8859_1);
		LOG.info("密文值:\n{}", encryptString);
		byte[] decryptBytes = Coder.base64DecodeMime(encryptBytes);
		String decryptString = new String(decryptBytes, StandardCharsets.ISO_8859_1);
		LOG.info("明文值:\n{}", decryptString);
	}

	@Test
	public void testMD5() throws Exception {
		String str1 = "这是测试用的字符串,反正就是一堆没什么用的废话什么的.lafdafjdkslafdlahsfdas!@^&#*%@$&@*!#15643153";
		byte[] src = str1.getBytes(StandardCharsets.UTF_8);
		byte[] encryptBytes = Coder.md5(src);
		String encryptString = Coder.toHexString(encryptBytes);
		LOG.info("密文值:\n{}", encryptString);
		byte[] data = null;
		byte[] buffer = new byte[10240];
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(new File("src/test/resources/test.rar"));
		int n;
		while ((n = fis.read(buffer)) != -1) {
			output.write(buffer, 0, n);
		}
		fis.close();
		data = output.toByteArray();
		output.close();
		LOG.info(new String(data));
		String fileEncrypt = Coder.toHexString(Coder.md5(data));
		LOG.info("文件密文值:\n{}", fileEncrypt);
	}

}
