package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayList<Item> {

    private Item[] items;
    private int size;

    public MyArrayList() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(Item item) {
        add(size(), item);
    }

    public void add(int index, Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        if (size() == items.length)
            ensureCapacity(size() * 2 + 1);
        for (int i = size(); i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = item;
        size++;
    }

    public Item remove() {
        return remove(size() - 1);
    }

    public Item remove(int index) {
        if (index < 0 || index > size() - 1)
            throw new IllegalArgumentException();

        Item removedItem = items[index];
        for (int i = index; i < size() - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        if (size() > 0 && size() == items.length / 4)
            ensureCapacity(items.length / 2);
        return removedItem;
    }

    public Item get(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException();
        }
        return items[index];
    }

    public void set(int index, Item item) {
        if (index < 0 || index >= size())
            throw new IllegalArgumentException();
        items[index] = item;
    }

    public void clear() {
        size = 0;
        items = (Item[]) new Object[2];
    }

    private void ensureCapacity(int capacity) {
        Item[] temp = items;
        items = (Item[]) new Object[capacity];
        for (int i = 0; i < temp.length; i++) {
            items[i] = temp[i];
        }
    }

    public int position(Item item) {

        for (int i = 0; i < size(); i++) {
            if (item.equals(items[i]))
                return i;
        }
        return -1;
    }
}
