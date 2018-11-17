/**
 * Created: 2016-12-06
 * ©2015-2016 junjie.ge. All rights reserved.
 */
package net.fantesy84.reacter.event;

/**
 *
 * @author junjie.ge
 *
 * @param <V>
 */
public interface Event {
	String getTargetName();
	String getTargetMapping();
	Object getResources();
}
