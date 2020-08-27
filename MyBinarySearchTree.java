package com.company;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyBinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    class Node {
        private Node left, right;
        private Key key;
        private Value value;
        private int size;

        private Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (node == null)
            return null;
        if (key.compareTo(node.key) == 0)
            return node.value;
        else if (key.compareTo(node.key) > 0)
            return get(node.right, key);
        else
            return get(node.left, key);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return node.size;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }
        root=put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {
        if (node == null)
            return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.left = put(key, value, node.left);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException();
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;
        return max(node.right);
    }

    public Key min() {
        if (root == null)
            throw new NoSuchElementException();
        if (root.left == null)
            return root.key;
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;
        return min(node.left);
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node temp = node;
            node = min(node.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            node.left = delete(node.left, key);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public boolean isEmpty() {
        return size(root) == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        return get(key) != null;
    }

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException();
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.right == null)
            return node.left;
        node.right = deleteMin(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException();
        if (isEmpty())
            throw new NoSuchElementException();
        Node temp = floor(root, key);
        if (temp == null)
            throw new NoSuchElementException();
        return floor(root, key).key;
    }

    private Node floor(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0)
            return node;
        if (cmp < 0)
            return floor(node.left, key);
        Node temp = floor(node.right, key);
        if (temp != null)
            return temp;
        return node;
    }

    public Iterable<Key> keys() {
        if (isEmpty())
            return new LinkedList<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key hi) {
        if (low == null || hi == null) throw new IllegalArgumentException();
        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, low, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key low, Key hi) {
        if (node == null) return;
        int cmpLow = low.compareTo(node.key);
        int cmpHi = hi.compareTo(node.key);
        if (cmpLow < 0) keys(node.left, queue, low, hi);
        if (cmpLow <= 0 && cmpHi >= 0) queue.add(node.key);
        if (cmpHi > 0) keys(node.right, queue, low, hi);
    }

}
