package net.fantesy84.common.util.excel.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel工作表注解
 * <p>
 * 凡是被标记了该注解的类都将被扫描其中所有的属性,如果没有在任何属性上标记注解<code>@ExcelColumn</code>,
 * 则会将所有的属性作为表头进行生成,如果进行了标记,则只对标记了注解<code>@ExcelColumn</code>的属性进行表头生成
 * @author junjie.ge
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelSheet {
	/**
	 * 定义工作表名称. 默认为空,生成的工作表名称为Sheet1
	 * @return 工作表名称
	 */
	String name() default "";
}
