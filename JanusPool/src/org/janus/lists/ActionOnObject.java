package org.janus.lists;

public interface ActionOnObject<K> {
	void perform(K obj);
}
