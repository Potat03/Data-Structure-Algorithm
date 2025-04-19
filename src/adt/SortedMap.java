/*
 * @author Loh Thiam Wei 
 */
package adt;

import java.io.Serializable;
import java.util.Iterator;

public class SortedMap<K extends Comparable<K>, V> implements SortedMapInterface<K, V>, Serializable {

    private int size;
    private Node<K, V> root;

    public SortedMap() {
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
            size++;
        } else {
            int cmp = root.key.compareTo(key);
            if (cmp > 0) {
                newNode.next = root;
                root = newNode;
                size++;
            } else if (cmp == 0) {
                root.value = value;
            } else {
                Node<K, V> current = root;
                while (current.next != null && current.next.key.compareTo(key) < 0) {
                    current = current.next;
                }
                if (current.next == null) {
                    current.next = newNode;
                    size++;
                } else if (current.next.key.compareTo(key) > 0) {
                    newNode.next = current.next;
                    current.next = newNode;
                    size++;
                } else {
                    current.next.value = value;
                }
            }
        }
    }

    @Override
    public String toString() {
        Node t = root;
        while (t != null) {
            System.out.println(t.key + " " + t.value);
            t = t.next;
        }
        return "";
    }

    @Override
    public V get(K key) {
        Node<K, V> temp = root;
        while (temp != null) {
            if (temp.key.compareTo(key) == 0) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (root != null) {
            Node<K, V> current = root;
            if (root.key.compareTo(key) == 0) {
                root = root.next;
                size--;
                return true;
            }
            while (current.next != null && current.next.key.compareTo(key) != 0) {
                current = current.next;
            }
            if (current.next != null) {
                current.next = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> temp = root;
        while (temp != null) {
            if (temp.key.compareTo(key) == 0) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
    }

    private static class Node<K, V> implements Serializable {

        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private class SortedMapIterator implements Iterator<V> {

        private Node<K, V> currNode = null;

        public SortedMapIterator() {
            if (!isEmpty()) {
                currNode = root;
            }
        }

        @Override
        public boolean hasNext() {
            return currNode != null;
        }

        @Override
        public V next() {
            V currElement = null;
            if (hasNext()) {
                currElement = currNode.value;
                currNode = currNode.next;
            }
            return currElement;
        }

    }

    public Iterator<V> getIterator() {
        return new SortedMapIterator();
    }
}
