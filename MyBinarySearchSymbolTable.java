package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class MyBinarySearchSymbolTable<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    int size;

    public MyBinarySearchSymbolTable() {
        this(2);
    }

    public MyBinarySearchSymbolTable(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            return null;
        int index = rank(key);
        if (index < size && key.compareTo(keys[index]) == 0)
            return values[index];
        return null;
    }

    private void resize(int capacity) {
        Key[] keysTemp = (Key[]) new Comparable[capacity];
        Value[] valuesTemp = (Value[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            keysTemp[i] = keys[i];
            valuesTemp[i] = values[i];
        }
        keys = keysTemp;
        values = valuesTemp;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        int low = 0, hi = size() - 1, mid;
        while (low <= hi) {
            mid = low + (hi - low) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp == 0)
                return mid;
            else if (cmp > 0)
                low = mid + 1;
            else hi = mid - 1;
        }
        return low;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        int index = rank(key);
        if (index < size() && key.compareTo(keys[index]) == 0) {
            values[index] = value;
            return;
        }

        if (size() == keys.length)
            resize(2 * keys.length);

        for (int i = size(); i > index; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        int index = rank(key);
        if (index < size() && key.compareTo(keys[index]) == 0) {
            for (int i = index; i < size() - 1; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }
            size--;
            keys[size()] = null;
            values[size()] = null;
            if (size() == 0 || values.length == size() * 4)
                resize(values.length / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < size(); i++)
            queue.add(keys[i]);
        return queue;
    }
}
