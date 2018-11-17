/**
 * Created: 2017-01-03
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.common.util.node;

import java.util.Collection;

/**
 * @author junjie.ge
 *
 */
public interface Node<T> {
	T value();
	Node<T> parent();
	Collection<Node<T>> children();
}
