package test.janus.pool;

import org.janus.lists.DoubleLinkedListElement;

public class TestString extends DoubleLinkedListElement {
	String text;

	public TestString(String text) {
		super();
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "TestString [text=" + text + "]";
	}

}
