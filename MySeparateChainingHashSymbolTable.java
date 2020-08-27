package com.company;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MySeparateChainingHashSymbolTable<Key, Value> {
    private int M;
    private int N;
    private MySequentialSearchSymbolTable<Key, Value>[] chain;

    public MySeparateChainingHashSymbolTable() {
        this(997);
    }

    public MySeparateChainingHashSymbolTable(int M) {
        this.M = M;
        chain = (MySequentialSearchSymbolTable<Key, Value>[]) new MySequentialSearchSymbolTable[M];
        for (int i = 0; i < M; i++) {
            chain[i] = new MySequentialSearchSymbolTable<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return (Value) chain[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        if (N >= 10 * M) resize(2 * M);

        if (!chain[hash(key)].contains(key))
            N++;
        chain[hash(key)].put(key, value);
    }

    private void resize(int M) {
        MySeparateChainingHashSymbolTable<Key, Value> temp = new MySeparateChainingHashSymbolTable<>(M);
        for (int i = 0; i < this.M; i++) {
            for (Key key : chain[i].keys()) {
                temp.put(key, chain[i].get(key));
            }
        }
        this.M = temp.M;
        this.chain = temp.chain;
        this.N = temp.N;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (chain[hash(key)].contains(key)) N--;
        chain[hash(key)].delete(key);
        if (M > 997 && N <= 2 * M) resize(M / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : chain[i].keys())
                queue.add(key);
        }
        return queue;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }


}
