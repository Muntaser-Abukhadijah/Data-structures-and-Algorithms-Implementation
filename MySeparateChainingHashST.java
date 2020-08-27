package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class MySeparateChainingHashST<Key, Value> {

    private int M, N;
    private Node[] chain ;

    private class Node {
        private Node next;
        private Key key;
        private Value value;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MySeparateChainingHashST() {
        this(997);
    }

    public MySeparateChainingHashST(int m) {
        M = m;
        chain = new MySeparateChainingHashST.Node[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        int index = hash(key);
        for (Node node = chain[index]; node != null; node = node.next) {
            if ((node.key).equals(key))
                return node.value;
        }
        return null;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        int index = hash(key);

        for (Node node = chain[index]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        N++;
        chain[index] = new Node(key, value, chain[index]);
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        chain[hash(key)] = delete(chain[hash(key)],key);
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;
        if (node.key.equals(key)) {
            N--;
            return node.next;
        }
        node.next = delete(node.next, key);
        return node;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            for (Node node = chain[i]; node != null; node = node.next) {
                queue.add(node.key);
            }
        }
        return queue;
    }


}
