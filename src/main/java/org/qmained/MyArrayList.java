package org.qmained;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class MyArrayList<E> implements MyList<E> {

    private final int DEFAULT_CAPACITY = 10;
    private int size;

    private Object[] array = new Object[]{};

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                return (E) array[index++];
            }
        };
    }


    @Override
    public boolean add(E e) {
        if (size == array.length) {
            resize(size + 1);
        }
        array[size] = e;
        size += 1;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        final Object[] es = array;
        final int size = this.size;
        int i = 0;
        found:
        {
            if (o == null) {
                for (; i < size; i++)
                    if (es[i] == null)
                        break found;
            } else {
                for (; i < size; i++)
                    if (o.equals(es[i]))
                        break found;
            }
            return false;
        }
        fastRemove(i);
        return true;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        @SuppressWarnings("unchecked") E oldValue = (E) array[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public void clear() {
        for (int to = size, i = size = 0; i < to; i++) {
            array[i] = null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) array[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = (E) array[index];
        array[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (size == array.length) {
            array = resize(size);
        }
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        array[index] = element;
        size += 1;
    }


    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private Object[] resize(int minCapacity) {
        int oldCapacity = array.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity >> 1;
            return array = Arrays.copyOf(array, newCapacity);
        } else {
            return array = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void fastRemove(int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(array, i + 1, array, i, newSize - i);
        array[size = newSize] = null;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
