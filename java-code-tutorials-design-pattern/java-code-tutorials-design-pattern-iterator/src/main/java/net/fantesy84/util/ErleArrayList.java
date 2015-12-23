/**
 * 项目名: java-code-tutorials-design-pattern-iterator
 * 包名:  net.fantesy84.util
 * 文件名: ErleArrayList.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月23日
 */
package net.fantesy84.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.RandomAccess;

import net.fantesy84.iterator.ErleIterator;

/**
 * 
 * @author Andronicus
 * @since 2015年12月23日
 * @param <E>
 */
public class ErleArrayList<E> implements ErleList<E>, Cloneable, RandomAccess, Serializable {
	private static final long serialVersionUID = 5948218677846560845L;

	private static final int DEFAULT_CAPACITY = 10;
	private static final Object[] EMPTY_ELEMENTDATA = {};
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	transient Object[] eleData;
	private int size;

	public ErleArrayList() {
		this.eleData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}

	public ErleArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.eleData = new Object[initialCapacity];
		} else if (initialCapacity == 0) {
			this.eleData = EMPTY_ELEMENTDATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
	}

	@SuppressWarnings("unchecked")
	E elementData(int index) {
		return (E) eleData[index];
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (eleData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity) {
		// overflow-conscious code
		if (minCapacity - eleData.length > 0) {
			grow(minCapacity);
		}
	}

	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = eleData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		// minCapacity is usually close to size, so this is a win:
		eleData = Arrays.copyOf(eleData, newCapacity);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			// overflow
			throw new OutOfMemoryError();
		}
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.collection.ErleCollection#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.collection.ErleCollection#add(java.io.Serializable)
	 */
	@Override
	public void add(E e) {
		ensureCapacityInternal(size + 1); // Increments modCount!!
		eleData[size++] = e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.collection.ErleCollection#get(int)
	 */
	@Override
	public E get(int index) {
		return elementData(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.collection.ErleCollection#remove(int)
	 */
	@Override
	public E remove(int index) {
		E oldValue = elementData(index);
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(eleData, index + 1, eleData, index, numMoved);
		}
		eleData[--size] = null; // Let gc do its work
		return oldValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.collection.ErleCollection#iterator()
	 */
	@Override
	public ErleIterator<E> iterator() {
		return new Itr();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.util.ErleList#toArray()
	 */
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(eleData, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.util.ErleList#toArray(java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			// Make a new array of a's runtime type, but my contents:
			return (T[]) Arrays.copyOf(eleData, size, a.getClass());
		}
		System.arraycopy(eleData, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.util.ErleList#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (eleData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(eleData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.fantesy84.util.ErleList#lastIndexOf(java.lang.Object)
	 */
	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
            for (int i = size-1; i >= 0; i--){
                if (eleData[i]==null){
                    return i;
                }
            }
        } else {
            for (int i = size-1; i >= 0; i--){
                if (o.equals(eleData[i])){
                    return i;
                }
            }
        }
        return -1;
	}

	private class Itr implements ErleIterator<E> {
		private int pos = -1;

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.fantesy84.iterator.ErleIterator#previous()
		 */
		@Override
		public E previous() {
			if (pos > 0) {
				pos--;
			}
			return elementData(pos);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.fantesy84.iterator.ErleIterator#next()
		 */
		@Override
		public E next() {
			if (pos < (size - 1)) {
				pos++;
			}
			return elementData(pos);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.fantesy84.iterator.ErleIterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (pos < (size - 1)) {
				return true;
			}
			return false;
		}

	}
}
