package test.janus.pool;

import org.janus.pool.ShivaFabric;


public class TestShiveFabric implements ShivaFabric<TestString> {
	public int createdCount;
	public int destroyedCount;
	
	public TestShiveFabric() {
		
	
	}

	@Override
	public TestString create() {
		createdCount++;
		return new TestString("" + createdCount);
	}

	@Override
	public void destroy(TestString obj) {
		destroyedCount++;
	}

	@Override
	public boolean isAlive(TestString obj) {
		return true;
	}
}
