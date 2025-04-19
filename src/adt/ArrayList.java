/*
 * @author Nicholas Yap Jia Wey, Loh Thiam Wei
 */
package adt;

import java.io.Serializable;
import java.util.Iterator;

public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialSize) {
        this.numberOfEntries = 0;
        array = (T[]) new Object[initialSize];
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }
        return outputStr;
    }

    @Override
    public boolean add(T newElement) {
        if (isFull()) {
            doubleArray();
        }
        array[numberOfEntries++] = newElement;
        return true;
    }

    @Override
    public boolean add(int index, T newElement) {
        if ((index >= 1) && (index <= numberOfEntries + 1)) {
            if (isFull()) {
                doubleArray();
            }

            moveBack(index);
            array[index - 1] = newElement;
            numberOfEntries++;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(int index) {

        if (checkIndex(index)) {
            if (index < numberOfEntries) {
                clearGaps(index);
            }
            numberOfEntries--;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            clearGaps(index);
            numberOfEntries--;
            return true;
        } 
        return false;
    }

    @Override
    public boolean replace(int index, T newElement) {
        if (checkIndex(index)) {
            array[index - 1] = newElement;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean replace(T oldElement, T newElement) {
        int index = indexOf(oldElement);
        if (index != -1) {
            array[index - 1] = newElement;
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean changePosition(int index, int position) {
        if (!(checkIndex(index) && checkIndex(position))) {
            return false;
        }

        if (index == position) {
            return false;
        }

        T temp = array[index - 1];
        array[index - 1] = array[position - 1];
        array[position - 1] = temp;
        return true;
    }

    private boolean checkIndex(int index) {
        return (index >= 1) && (index <= numberOfEntries);
    }

    @Override
    public T get(int index) {
        if (!checkIndex(index)) {
            return null;
        }
        return array[index - 1];
    }

    @Override
    public int size() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == array.length;
    }

    @Override
    public boolean contains(T element) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (element.equals(array[index])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        if (!isEmpty() && contains(element)) {
            for (int i = 1; i <= numberOfEntries; i++) {
                if (element.equals(array[i - 1])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        if (!isEmpty() && contains(element)) {
            for (int i = numberOfEntries; i > 0; i--) {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int count(T element) {
        int count = 0;
        if (!isEmpty() && contains(element)) {
            for (int i = 0; i < numberOfEntries; i++) {
                if (element.equals(array[i])) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    @Override
    public ListInterface<T> subList(int from, int to) {
        ArrayList<T> subList = new ArrayList<>(numberOfEntries);
        if (!isEmpty() && from >= 0 && to < numberOfEntries) {
            for (int i = from; i <= to; i++) {
                subList.add(array[i]);

            }
        }
        return subList;
    }

    @Override
    public void merge(ListInterface<T> newList) {
        ArrayList subList = (ArrayList) newList;
        if (!subList.isEmpty()) {
            int index = 0;
            for (int i = numberOfEntries; i < (numberOfEntries + subList.array.length); i++) {
                if (isFull()) {
                    doubleArray();
                }
                array[i] = (T) subList.array[index++];
            }
        }
    }

    @Override
    public void copy(ListInterface<T> newList) {
        ArrayList<T> givenList = (ArrayList<T>) newList;
        clear();
        for (int i = 0; i < givenList.numberOfEntries; i++) {
            this.add(givenList.array[i]);
        }
    }

    private void doubleArray() {
        T[] oldArray = array;
        array = (T[]) new Object[array.length * 2];
        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = oldArray[i];
        }
    }

    private void moveBack(int newIndex) {
        newIndex--;
        int lastIndex = numberOfEntries - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void clearGaps(int newIndex) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = newIndex - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    @Override
    public Iterator<T> getIterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int currIndex;

        public ArrayListIterator() {
            currIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currIndex < numberOfEntries;
        }

        @Override
        public T next() {
            return array[currIndex++];
        }

    }
}
