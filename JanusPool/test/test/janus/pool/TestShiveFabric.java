package test.janus.pool;

import org.janus.pool.ShivaFabric;


public class TestShiveFabric implements ShivaFabric<TestString> {
    private int createdCount;
    private int destroyedCount;

    public TestShiveFabric() {
    }

	@Override
	public TestString create() {
		createdCount++;
		return new TestString(Integer.toString(createdCount));
	}

	@Override
	public void destroy(TestString obj) {
		destroyedCount++;
	}

	@Override
	public boolean isAlive(TestString obj) {
		return true;
	}

    public int getCreatedCount() {
        return createdCount;
    }

    public int getDestroyedCount() {
        return destroyedCount;
    }
}
