package test.janus.pool;

import static org.junit.Assert.*;

import org.janus.lists.ActionOnObject;
import org.janus.lists.DoubleLinkedList;
import org.janus.lists.DoubleLinkedListElement;
import org.junit.Test;

public class DoubleLinkedListTest {

	@Test
	public void test() {
		
		DoubleLinkedList<DoubleLinkedListElement> liste = new DoubleLinkedList<>();
		
		countElementTest(liste, 0);
		
		addNewElement(liste);
		countElementTest(liste, 1);
			
		addNewElement(liste);
		countElementTest(liste, 2);
			
		DoubleLinkedListElement elem = addNewElement(liste);
		countElementTest(liste, 3);
		
		liste.remove(elem);
		
		countElementTest(liste, 2);
		
		elem = addNewElement(liste);
		countElementTest(liste, 3);
	
		addNewElement(liste);
		countElementTest(liste, 4);
	
		addNewElement(liste);
		countElementTest(liste, 5);
	
		liste.remove(elem);
		
		countElementTest(liste, 4);
			
	}

	private DoubleLinkedListElement addNewElement(DoubleLinkedList<DoubleLinkedListElement> liste) {
		DoubleLinkedListElement elem = new DoubleLinkedListElement();
		
		liste.add(elem);
		return elem;
	}

	private void countElementTest(
			DoubleLinkedList<DoubleLinkedListElement> liste, int anz) {
		TestCounter counter = new TestCounter();
		liste.perform(counter);
		assertEquals(anz,counter.size);
		assertEquals(anz,liste.size());
	}

	class TestCounter implements ActionOnObject<DoubleLinkedListElement> {
		public int size=0;
		
	
		@Override
		public void perform(DoubleLinkedListElement obj) {
			size++;
		}
		
	}
}
