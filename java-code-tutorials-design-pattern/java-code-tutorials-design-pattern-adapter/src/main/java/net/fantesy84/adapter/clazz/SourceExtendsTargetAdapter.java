/**
 * 项目名: java-code-tutorials-design-pattern-adapter
 * 包名:  net.fantesy84.adapter.clazz
 * 文件名: SourceTargetAdapter.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月23日
 */
package net.fantesy84.adapter.clazz;

/**
 * @author Andronicus
 * @since 2015年11月23日
 */
public class SourceExtendsTargetAdapter extends Source implements Target {
	
	/**
	 * 
	 */
	public SourceExtendsTargetAdapter() {
		super();
		super.setName("这是源类的方法一");
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.adapter.clazz.Target#method2()
	 */
	@Override
	public void method2() {
		System.out.println("this is the target method2!");
	}

}
