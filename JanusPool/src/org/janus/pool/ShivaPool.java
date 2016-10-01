package org.janus.pool;

import org.janus.lists.DoubleLinkedList;
import org.janus.lists.DoubleLinkedListElement;


public class ShivaPool<K extends DoubleLinkedListElement> implements ShivaFabric<K > {
	private int maxCount;
	private int normalCount;

	protected ShivaFabric<K> shiva;
	private DoubleLinkedList<K> inactive;
	private DoubleLinkedList<K> active;

	public ShivaPool(int normalCount, int maxCount, ShivaFabric<K> shiva) {
		super();
		if (normalCount <= 0) {
			throw new IllegalArgumentException("normalCount muss > 0 sein");
		}
		if (maxCount < 0) {
			throw new IllegalArgumentException("maxCount muss >= 0 sein");
		}
		if (maxCount > 0 && normalCount > maxCount) {
			throw new IllegalArgumentException(
					"normalCount passt nicht zu maxCount");
		}
		if (shiva == null) {
			throw new IllegalArgumentException("Shive darf nicht Null sein");
		}
		this.normalCount = normalCount;
		this.maxCount = maxCount;
		this.shiva = shiva;
		inactive = new DoubleLinkedList<>();
		active = new DoubleLinkedList<>();
	}

	@Override
	public K create() {
		K obj;

		synchronized (this) {
			if (maxCount > 0 && active.size() + inactive.size()>= maxCount ) {
				return null;
			}
			
			if (inactive.size()> 0) {
				obj = inactive.pop();
		
				if (shiva.isAlive(obj)) {
					active.add(obj);
					return obj;
				} else {
					shiva.destroy(obj);
				}
			}
		}
		obj = shiva.create();
		active.add(obj);
		return obj;
	}

	@Override
	public void destroy(K obj) {
		synchronized (this) {
			active.remove(obj);
			if (shiva.isAlive(obj) && inactive.size() <= normalCount) {
				inactive.add(obj);
			} else {
				shiva.destroy(obj);
			}
		}
	}

	public void destroyAll() {
		synchronized (this) {
			while (inactive.size() >0) {
				shiva.destroy(inactive.pop());
			}
			while (active.size() >0) {
				shiva.destroy(active.pop());
			}
		}
	}

	@Override
	public boolean isAlive(K obj) {
		return shiva.isAlive(obj);
	}

	public int getActiveCount() {
		return active.size();
	}
	
	public int getInActiveCount() {
		return inactive.size();
	}
	
	public int getCount() {
		return active.size() + inactive.size();
	}
	
	
}
