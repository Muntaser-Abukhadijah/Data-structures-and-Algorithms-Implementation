package com.company;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MySequentialSearchSymbolTable<Key, Value> {

    private Node head;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        for (Node node = head; node != null; node = node.next) {
            if (node.key == key) {
                return node.value;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) {
            delete(key);
            return;
        }
        for (Node node = head; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        head = new Node(key, value, head);
        size++;
    }

    public int size() {
        return size;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        head = delete(head, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;
        if (node.key.equals(key)) {
            size--;
            return node.next;
        }
        node.next = delete(node.next, key);
        return node;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (Node node = head; node != null; node = node.next)
            queue.add(node.key);
        return queue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }


}