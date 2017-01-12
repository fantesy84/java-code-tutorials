package net.fantesy84.common.util.excel.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Excel表头注解,所有标记了该注解的属性都将作为表头属性被反射
 * <p>将来会加入多列多行合并单元格的方式的表头,在数据结构上体现为实体类的嵌套
 * @author junjie.ge
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface ExcelColumn {
	/**
	 * 该列表头名称,可使用中文
	 * @return 该列表头名称
	 */
	String name();

	/**
	 * 列下标如果自定义,从0开始第一列,最大值32767
	 * <li><strong>请注意!如果在某个实体的属性上本注解定义了列下标,则该属性之前所有的属性都必须定义下标,否则将忽略!</strong></li>
	 * @return 列下标
	 */
	int index() default -1;

	/**
	 * 自定义格式化表达式,可参照以下格式进行选择
	 * <p/>
	 * "General"<br/>
	 * "0"<br/>
	 * "0.00"<br/>
	 * "#,##0"<br/>
	 * "#,##0.00"<br/>
	 * "$#,##0_);($#,##0)"<br/>
	 * "$#,##0_);[Red]($#,##0)"<br/>
	 * "$#,##0.00);($#,##0.00)"<br/>
	 * "$#,##0.00_);[Red]($#,##0.00)"<br/>
	 * "0%"<br/>
	 * "0.00%"<br/>
	 * "0.00E+00"<br/>
	 * "# ?/?"<br/>
	 * "# ??/??"<br/>
	 * "m/d/yy"<br/>
	 * "d-mmm-yy"<br/>
	 * "d-mmm"<br/>
	 * "mmm-yy"<br/>
	 * "h:mm AM/PM"<br/>
	 * "h:mm:ss AM/PM"<br/>
	 * "h:mm"<br/>
	 * "h:mm:ss"<br/>
	 * "m/d/yy h:mm"<br/>
	 * <p/>
	 * "#,##0_);(#,##0)"<br/>
	 * "#,##0_);[Red](#,##0)"<br/>
	 * "#,##0.00_);(#,##0.00)"<br/>
	 * "#,##0.00_);[Red](#,##0.00)"<br/>
	 * "_(* #,##0_);_(* (#,##0);_(* \"-\"_);_(@_)"<br/>
	 * "_($* #,##0_);_($* (#,##0);_($* \"-\"_);_(@_)"<br/>
	 * "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)"<br/>
	 * "_($* #,##0.00_);_($* (#,##0.00);_($* \"-\"??_);_(@_)"<br/>
	 * "mm:ss"<br/>
	 * "[h]:mm:ss"<br/>
	 * "mm:ss.0"<br/>
	 * "##0.0E+0"<br/>
	 * "@" - This is text format.<br/>
	 * "text" - Alias for "@"<br/>
	 * <p/>
	 *
	 * @return 自定义格式化字符串
	 */
	String formatterPattern() default "";

	/**
	 * 单元格数据类型
	 * @return {@link CellType}
	 */
	CellType cellType() default CellType.BLANK;
	/**
	 * 默认值,在该列的数据为null时,将会填充此值.
	 * <p>默认为"-"
	 * @return 该列默认值
	 */
	String defaultNullValue() default "-";

	/**
	 * 单元格数据类型枚举,暂时定了8个类型,以后可能会增加
	 * @author junjie.ge
	 *
	 */
	public enum CellType {
		/**
		 * 数字类型,对应double
		 */
		NUMERIC,
		/**
		 * 字符串类型
		 */
		STRING,
		/**
		 * 公式,对应字符串
		 */
		FORMULA,
		/**
		 * 空白内容
		 */
		BLANK,
		/**
		 * 布尔值
		 */
		BOOLEAN,
		/**
		 * 时间类型
		 */
		DATE,
		/**
		 * 日历类型
		 */
		CALENDAR,
		/**
		 * 错误信息,byte类型
		 */
		ERROR;
	}
}
