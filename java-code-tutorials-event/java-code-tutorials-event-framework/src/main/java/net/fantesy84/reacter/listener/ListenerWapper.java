/**
 * Created: 2017-01-05
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.reacter.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.reacter.annotations.EventListener;
import net.fantesy84.reacter.annotations.EventMapping;
import net.fantesy84.reacter.event.Event;

/**
 * @author junjie.ge
 *
 */
public class ListenerWapper implements Listener {
	private static final Logger LOG = LoggerFactory.getLogger(ListenerWapper.class);
	private String eventListenerName;
	private Object adapterSource;

	public ListenerWapper(Object source) {
		if (source == null) {
			throw new NullPointerException("Source instance could not be null!");
		}
		Class<?> clazz = source.getClass();
		EventListener el = clazz.getAnnotation(EventListener.class);
		if (el == null) {
			throw new IllegalStateException("Type " + clazz.getName() + " must annotationed by @EventListener");
		}
		this.eventListenerName = el.name();
		this.adapterSource = source;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.reacter.listener.Listener#onEvent(java.lang.Object)
	 */
	@Override
	public void onEvent(Event e) {
		Class<?> clazz = adapterSource.getClass();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			EventMapping em = method.getAnnotation(EventMapping.class);
			if (em == null) {
				continue;
			}
			if (em.value().equals(e.getTargetMapping())) {
				LOG.debug("映射{}适配成功,找到的方法名:{}", new Object[]{e.getTargetMapping(), method.getName()});
				int parameterCount = method.getParameterCount();
				try {
					if (parameterCount == 0) {
						LOG.debug("适配方法无参数!");
						method.invoke(adapterSource, new Object[]{});
					}
					if (parameterCount == 1) {
						Class<?> paramType = method.getParameterTypes()[0];
						LOG.debug("适配方法唯一参数类型:{}", paramType.getName());
						Object paramObject = null;
						if (Event.class.isAssignableFrom(paramType)) {
							LOG.debug("参数类型为接口{}的实现类", Event.class.getName());
							paramObject = paramType.cast(e);
						} else {
							Class<?> resourceClass = e.getResources().getClass();
							LOG.debug("事件的资源对象类型:{}", resourceClass.getName());
							if (paramType.isAssignableFrom(resourceClass)) {
								LOG.debug("[事件资源对象]是[适配方法参数]的实现类或子类或本身,可直接赋值");
								paramObject = e.getResources();
							} else if (resourceClass.isAssignableFrom(paramType)) {
								LOG.debug("[适配方法参数]是[事件资源对象]的实现类或子类,强制向下转型");
								paramObject = paramType.cast(e.getResources());
							}
						}
						if (paramObject != null) {
							method.invoke(adapterSource, new Object[]{paramObject});
						} else {
							throw new IllegalArgumentException("使用注解@EventMapping的方法参数类型必须是net.fantesy84.reacter.event.Event类型或者Event对象中资源属性的子类或超类");
						}
					} else if (parameterCount > 1) {
						throw new IllegalArgumentException("使用注解@EventMapping的方法最多只能有一个参数");
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException ex) {
					throw new IllegalStateException(ex);
				}
				break;
			}
		}
	}

	/**
	 * @return the adapterSource
	 */
	public Object getAdapterSource() {
		return adapterSource;
	}

	/**
	 * @return the eventListenerName
	 */
	public String getEventListenerName() {
		return eventListenerName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ListenerWapper) {
			ListenerWapper target = (ListenerWapper) obj;
			if (target.getEventListenerName() == this.eventListenerName) {
				return true;
			}
		}
		return false;
	}

}
