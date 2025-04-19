/*
 * @author Loh Thiam Wei , Nicholas Yap Jia Wey
 */
package adt;

import java.util.Iterator;

public interface SortedListInterface<T extends Comparable<T>> {
    Iterator<T> getIterator();
    boolean add(T newElement);
    boolean remove(T element);
    T get(T element);
    T get(int index);
    int size();
    boolean isEmpty();
    boolean contains(T element);
    int indexOf(T element);
    int lastIndexOf(T element);
    int count(T element);
    void clear();
    SortedListInterface<T> subList(T from, T to);
    void merge(SortedListInterface<T> newList);
    void copy(SortedListInterface<T> aList);
}

