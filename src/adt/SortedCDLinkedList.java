/*
 * @SortedCircularDoublyLinkedList.java
 * @By : Nicholas Yap Jia Wey, Loh Thiam Wei, Loo Wee Kiat, Tan Wei Siang
 */
package adt;

import java.io.Serializable;
import java.util.Iterator;

public class SortedCDLinkedList<T extends Comparable<T>> implements SortedListInterface<T>, Serializable {

    private Node lastNode;
    private int numberOfEntries;

    public SortedCDLinkedList() {
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void copy(SortedListInterface<T> aList) {
        SortedCDLinkedList<T> list = (SortedCDLinkedList<T>) aList;
        clear(); 

        if (!list.isEmpty()) {
            Node currNode = list.lastNode.next;
            do {
                add(currNode.data);
                currNode = currNode.next;
            } while (currNode != list.lastNode.next);
        }
    }

    private class Node implements Serializable {

        private T data;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    @Override
    public String toString() {
        String str = "";
        if (!isEmpty()) {
            Node currNode = lastNode.next;
            do {
                str +=  currNode.data + "\n" ;
                currNode = currNode.next;
            } while (currNode != lastNode.next);
        }
        return str;
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node currNode = null;

        public LinkedListIterator() {
            if (!isEmpty()) {
                currNode = lastNode.next;
            }
        }

        @Override
        public boolean hasNext() {
            return currNode != null;
        }

        @Override
        public T next() {
            T currElement = null;
            if (hasNext()) {
                currElement = currNode.data;
                if (currNode == lastNode) {
                    currNode = null;
                } else {
                    currNode = currNode.next;
                }
            }
            return currElement;
        }

        public boolean isLast() {
            return currNode == lastNode;
        }

    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedListIterator();
    }

    @Override
    public boolean add(T newElement) {
        Node newNode = new Node(newElement);
        if (isEmpty()) { // If empty, directly create a node point to itself
            lastNode = newNode;
            lastNode.prev = lastNode;
            lastNode.next = lastNode;
            numberOfEntries++;
            return true;
        } else {
            return add(lastNode.next, newNode);
        }
    }

    private boolean add(Node currNode, Node newNode) {
        // If currNode is equal or bigger than newNode, add the newNode infront of currNode
        if (newNode.data.compareTo(currNode.data) <= 0) {
            newNode.next = currNode;
            newNode.prev = currNode.prev;
            currNode.prev.next = newNode;
            currNode.prev = newNode;
            numberOfEntries++;
            return true;
        }

        // If currNode.next is not first Node, means more node behind, move to Next node
        if (currNode.next != lastNode.next) {
            return add(currNode.next, newNode);
        } else { // If currNode is lastNode, add newNode after lastNode and update lastNode pointer
            newNode.next = currNode.next;
            newNode.prev = currNode;
            lastNode.next.prev = newNode;
            currNode.next = newNode;
            lastNode = newNode;
            numberOfEntries++;
            return true;
        }
    }

    @Override
    public boolean remove(T element) {
        if (isEmpty()) {
            return false;
        }
        return remove(lastNode.next, element);
    }

    private boolean remove(Node currNode, T element) {

        int cmp = element.compareTo(currNode.data);

        if (cmp > 0) { // If node to deleted is still bigger , move to next node
            if (currNode == lastNode) { // If this node is last node , means not found
                return false;
            }
            return remove(currNode.next, element); // Else move to next Node 
        } else if (cmp < 0) { // If node to deleted is smaller than current node, means not found
            return false;
        }

        // If only have a node in list, clear the list
        if (numberOfEntries == 1) {
            clear();
            return true;
        }
        
        if(currNode == lastNode){
            lastNode = currNode.prev;
        }

        currNode.next.prev = currNode.prev;
        currNode.prev.next = currNode.next;

        numberOfEntries--;
        return true;
    }

    @Override
    public T get(T element) {
        int index = indexOf(element);

        // If element not exist, return false;
        if (index == -1) {
            return null;
        }

        // Loop until point to the Node contain the element
        Node currNode = lastNode.next;
        for (int i = 1; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.data;
    }

    @Override
    public T get(int index) {

        if (index < 1 || index > numberOfEntries) {
            return null;
        }

        // Loop until point to the Node contain the element
        Node currNode = lastNode.next;
        for (int i = 1; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode.data;
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return lastNode == null;
    }

    @Override
    public boolean contains(T element) {
        return count(element) != 0;
    }

    @Override
    public int indexOf(T element) {
        if (isEmpty()) {
            return -1;
        }
        int index = 1;
        Node currNode = lastNode.next;
        do {
            if (element.compareTo(currNode.data) == 0) {
                return index;
            }
            index++;
            currNode = currNode.next;
        } while (currNode != lastNode.next);
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        if (isEmpty()) {
            return -1;
        }
        int index = numberOfEntries;
        Node currNode = lastNode;
        do {
            if (element.compareTo(currNode.data) == 0) {
                return index;
            }
            index--;
            currNode = currNode.prev;
        } while (currNode != lastNode);
        return -1;
    }

    @Override
    public int count(T element) {
        if (isEmpty()) {
            return 0;
        }

        int count = 0;
        Node currNode = lastNode.next;
        do {
            if (element.compareTo(currNode.data) == 0) {
                count++;
            }
            currNode = currNode.next;
        } while (currNode != lastNode.next);
        return count;
    }

    @Override
    public void clear() {
        // Clear all possible pointer that point to lastNode
        if(numberOfEntries != 0){
            lastNode = lastNode.next = lastNode.prev = null;
            numberOfEntries = 0;
        }
    }

    @Override
    public SortedListInterface<T> subList(T from, T to) {
        if (isEmpty()) {
            return null;
        }
        int count = 0;
        SortedCDLinkedList<T> subList = new SortedCDLinkedList<>();
        Node currNode = lastNode.next;
        do {
            int cmpStart = currNode.data.compareTo(from); // If currNode is bigger (1), equal (0), smaller (-1)
            int cmpEnd = currNode.data.compareTo(to);

            // Current Node element is between from/to or equal to from/to, add to subList
            if (cmpStart >= 0 && cmpEnd <= 0) {
                subList.add(currNode.data);
                count++;
            }

            if (cmpEnd > 0) {
                break;
            }

            currNode = currNode.next;
        } while (currNode != lastNode.next);

        // If no element in between from and To return null
        if (count == 0) {
            return null;
        }
        return subList;
    }

    @Override
    public void merge(SortedListInterface<T> newList) {
        SortedCDLinkedList<T> list = (SortedCDLinkedList) newList;
        if (!list.isEmpty()) {
            Node currNode = list.lastNode.next;
            do {
                add(currNode.data);
                currNode = currNode.next;
            } while (currNode != list.lastNode.next);
        }
    }
}
