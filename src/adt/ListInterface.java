/*
 * @author Loh Thiam Wei , Nicholas Yap Jia Wey
 */
package adt;

import java.util.Iterator;

public interface ListInterface<T> {
    boolean add(T newElement);
    boolean add(int index, T newElement);
    boolean remove(int index);
    boolean remove(T element);
    boolean replace(int index, T newElement);
    boolean replace(T oldElement, T newElement);
    boolean changePosition(int index, int position);
    T get(int index);
    int size();
    boolean isEmpty();
    boolean isFull();
    boolean contains(T element);
    int indexOf(T element);
    int lastIndexOf(T element);
    int count(T element);
    void clear();
    ListInterface<T> subList(int from, int to);
    void merge(ListInterface<T> newList);
    void copy(ListInterface<T> newList);
    Iterator<T> getIterator();
}
