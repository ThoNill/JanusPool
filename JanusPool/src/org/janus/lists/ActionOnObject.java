package org.janus.lists;

@FunctionalInterface
public interface ActionOnObject<K> {
	void perform(K obj);
}
