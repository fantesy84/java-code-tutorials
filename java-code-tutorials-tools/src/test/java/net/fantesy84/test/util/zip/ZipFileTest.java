/**
 * Created: 2017-01-03
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.util.zip;

import org.junit.Test;

import net.fantesy84.common.util.zip.ZipUtils;

/**
 * @author junjie.ge
 *
 */
public class ZipFileTest {
	@Test
	public void testCompress() throws Exception {
		ZipUtils.compress("F:/pdf-tools", "F:", "pdf-tools.zip");
	}
}
