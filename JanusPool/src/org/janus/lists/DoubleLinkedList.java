package org.janus.lists;


public class DoubleLinkedList<K extends DoubleLinkedListElement> {
	private K first;
	private int size;

	public DoubleLinkedList() {
		super();
		first = null;
		size = 0;
	}

	public synchronized void add(K elem) {
		if (elem == null) {
			throw new IllegalArgumentException("elem is null");
		}
		if (this == elem.getList()) {
			throw new IllegalArgumentException("elem is allready in List");
		}

		elem.setList(this);

		if (first == null) {
			first = elem;
		} else {
			first.append(elem);
		}
		size++;
	}

	public synchronized void remove(K elem) {
		if (elem == null) {
			throw new IllegalArgumentException("elem is null");
		}
		if (this != elem.getList()) {
			throw new IllegalArgumentException("elem is not in this List");
		}

		if (first == null) {
			throw new IllegalArgumentException("List is empty");
		}

		if (first == elem) {
			if (size == 1) {
				first = null;
			} else {
				first = (K) elem.getNext();
			}
		}
		elem.removeFromList();
		size--;
	}

	public int size() {
		return size;
	}

	public K pop() {
		K r = (K) first.getNext();
		remove(r);
		return r;
	}

	@SuppressWarnings("unchecked")
	public void perform(ActionOnObject<K> action) {
		if (first != null) {
			K obj = first;
			do {
				action.perform(obj);
				obj = (K)obj.getNext();
			} while (obj != first);
		}
	}
}
