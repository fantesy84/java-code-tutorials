/**
 *
 */
package net.fantesy84.common.util.excel.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
/**
 * @author junjie.ge
 *
 */
public @interface ExcelCellStyle {
	DataType[] dataFormat();

	public enum DataType {
		
	}
}
