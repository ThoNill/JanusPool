package org.janus.lists;

public class DoubleLinkedListElement {

	private DoubleLinkedListElement next;
	private DoubleLinkedListElement pre;
	private DoubleLinkedList list;


	public DoubleLinkedListElement() {
		super();
		loopToMyself();
	}

	void removeFromList() {
		DoubleLinkedListElement p = this.pre;
		DoubleLinkedListElement n = this.next;
		n.pre = p;
		p.next = n;
		loopToMyself();
	}

	void append(DoubleLinkedListElement newNext) {

		DoubleLinkedListElement oldNext = this.next;
		newNext.next = oldNext.next;
		oldNext.next = newNext;
		newNext.pre = oldNext;
		newNext.next.pre = newNext;
	}

	private void loopToMyself() {
		next = this;
		pre = this;
		list = null;
	}

	DoubleLinkedListElement getNext() {
		return next;
	}

	DoubleLinkedListElement getPre() {
		return pre;
	}

	public DoubleLinkedList getList() {
		return list;
	}

	
	public void setList(DoubleLinkedList list) {
		this.list = list;
	}
}
