package org.qmained;

import java.util.Iterator;

public interface MyList<E> {


    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    void clear();

    @SuppressWarnings("unchecked")
    E get(int index);

    @SuppressWarnings("unchecked")
    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);
}
