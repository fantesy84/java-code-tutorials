/**
 * Created: 2016-11-28
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.test.util.excel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.fantesy84.common.util.excel.core.ExcelBuilder;
import net.fantesy84.common.util.random.RandomUtils;

/**
 * @author junjie.ge
 *
 */
public class SimpleReportTest {
	@Test
	public void test1() throws Exception {
		SimpleReportEntity entity = new SimpleReportEntity();
		List<SimpleReport> datas = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			double balance = Math.random()*100000.0;
			SimpleReport ele = new SimpleReport();
			ele.setUsername("username_" + i);
			ele.setPassword("password_" + i);
			ele.setBirthday(RandomUtils.getRandomDate("1975-01-01 00:00:00", "1995-01-01 00:00:00"));
			ele.setAccountNum(RandomUtils.getRandomStringWithSymbol(RandomUtils.NUMBERIC_TYPE, 16, 4, " "));
			ele.setAccountBalance(new BigDecimal(balance));
			ele.setDeleteFlag(Boolean.FALSE);
			datas.add(ele);
		}
		entity.setExcelData(datas);
		ExcelBuilder.getInstance().createFromEntity(entity, "C:/Users/junjie.ge/Desktop", "test.xlsx");
	}

}
