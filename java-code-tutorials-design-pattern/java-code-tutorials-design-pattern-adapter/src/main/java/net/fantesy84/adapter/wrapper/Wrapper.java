/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.obj
 * 文件名: ObjectSourceTargetAdapter.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.wrapper;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class Wrapper implements Target {
	
	private Source source;
	
	/**
	 * @param source
	 */
	public Wrapper(Source source) {
		this.source = source;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.adapter.clazz.Target#method1()
	 */
	@Override
	public void method1() {
		source.method1();
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.adapter.clazz.Target#method2()
	 */
	@Override
	public void method2() {
		System.out.println("this is the target method2!");
	}

}
