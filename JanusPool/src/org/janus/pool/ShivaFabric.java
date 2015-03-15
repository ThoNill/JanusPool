package org.janus.pool;

import org.janus.lists.DoubleLinkedListElement;

public interface ShivaFabric<K extends DoubleLinkedListElement > {
	
	K create();
	void destroy(K obj);
	boolean isAlive(K obj);

}
