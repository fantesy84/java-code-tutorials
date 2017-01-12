package net.fantesy84.common.util.excel.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ExcelExport {
	String attachmentName() default "export-excel";
	String charsetName() default "ISO-8859-1";
	String contentType() default "application/octet-stream";
}
