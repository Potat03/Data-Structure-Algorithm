/*
 * @author Loh Thiam Wei 
 */
package adt;

import java.util.Iterator;

public interface SortedMapInterface<K extends Comparable<K>, V> {

    void put(K key, V value);

    V get(K key);

    boolean remove(K key);

    boolean containsKey(K key);

    boolean isEmpty();

    int size();

    void clear();

    Iterator<V> getIterator();
}
