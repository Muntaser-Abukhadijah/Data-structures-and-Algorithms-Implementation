package com.muntaser;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> {

	private Node<T> head;
	private int lenght;

	public void add(T value) {
		if (value == null)
			throw new IllegalArgumentException();

		Node<T> newNode = new Node(value);
		if (head == null) {
			head = newNode;
			return;
		}
		Node<T> last = head;
		while (last.next != null) {
			last = last.next;
		}
		last.next = newNode;
		lenght++;
	}

	public void delete(int index) {
		if (index < 0 || index >= lenght)
			throw new IllegalArgumentException();

		Node<T> temp = head;
		for (int i = 0; i < index-1; i++) {
			temp = temp.next;
		}
		if (temp.next.next != null) 
			temp.next = temp.next.next;
		lenght--;
	}

	public T get(int index) {
		if (index < 0 || index >= lenght)
			throw new IllegalArgumentException();

		Node<T> temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return (T) temp.next.value;
	}

	public void print() {
		Node<T> it = head;
		System.out.println("============================");
		while (it != null) {
			System.out.println(it.value);
			it = it.next;
		}
	}

}

class Node<T> {
	T value;
	Node next;

	Node(T value) {
		this.value = value;
	}
}
