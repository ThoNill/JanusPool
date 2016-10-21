package test.janus.pool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.janus.pool.ShivaPool;
import org.junit.Test;


public class PoolTest {
    private static final String AUSNAHME_AUFGETRETEN = "Ausnahme aufgetreten";
    private static final String UNERWARTETE_AUSNAHME = "unerwartete Ausnahme";
    public static final Logger LOG = Logger.getLogger(PoolTest.class);


	@Test
	public void testCreation() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			new ShivaPool<>(10, 10, shiva);
		} catch (Exception ex) {
		    LOG.error(UNERWARTETE_AUSNAHME,ex);
			fail(AUSNAHME_AUFGETRETEN);
		}
	}

	@Test
	public void test1() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<>(10, 10, shiva);

			TestString s1 = pool.create();
			TestString s2 = pool.create();
			TestString s3 = pool.create();

			assertEquals(3, shiva.getCreatedCount());

			pool.destroy(s1);
			pool.destroy(s2);
			pool.destroy(s3);

			assertEquals(3, shiva.getCreatedCount());
			assertEquals(0, shiva.getDestroyedCount());

		} catch (Exception ex) {
		    LOG.error(UNERWARTETE_AUSNAHME,ex);
			fail(AUSNAHME_AUFGETRETEN);
		}
	}

	@Test
	public void test2() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<>(10, 10, shiva);

			TestString s1 = pool.create();
			TestString s2 = pool.create();
			TestString s3 = pool.create();

			assertEquals(3, shiva.getCreatedCount());
			assertEquals(3, pool.getActiveCount());

			pool.destroy(s1);
			pool.destroy(s2);
			pool.destroy(s3);

			assertEquals(3, shiva.getCreatedCount());
			assertEquals(0, shiva.getDestroyedCount());
			assertEquals(0, pool.getActiveCount());
			assertEquals(3, pool.getInActiveCount());

			pool.create();

			assertEquals(3, shiva.getCreatedCount());
			assertEquals(0, shiva.getDestroyedCount());
			assertEquals(1, pool.getActiveCount());
			assertEquals(2, pool.getInActiveCount());

			pool.create();

			assertEquals(3, shiva.getCreatedCount());
			assertEquals(0, shiva.getDestroyedCount());
			assertEquals(2, pool.getActiveCount());
			assertEquals(1, pool.getInActiveCount());

			pool.create();

			assertEquals(3, shiva.getCreatedCount());
			assertEquals(0, shiva.getDestroyedCount());
			assertEquals(3, pool.getActiveCount());
			assertEquals(0, pool.getInActiveCount());

			pool.create();

			assertEquals(4, shiva.getCreatedCount());
			assertEquals(0, shiva.getDestroyedCount());
			assertEquals(4, pool.getActiveCount());
			assertEquals(0, pool.getInActiveCount());

			pool.destroyAll();
			
			assertEquals(4, shiva.getCreatedCount());
			assertEquals(4, shiva.getDestroyedCount());
			assertEquals(0, pool.getActiveCount());
			assertEquals(0, pool.getInActiveCount());
			
			
		} catch (Exception ex) {
		    LOG.error(UNERWARTETE_AUSNAHME,ex);
			fail(AUSNAHME_AUFGETRETEN);
		}
	}

	@Test
	public void test3() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<>(10, 10, shiva);

			for (int i = 1; i <= 10; i++) {
				TestString s = pool.create();
				if (s == null) {
					fail("String darf nicht Null sein");
				}

			}

			TestString s = pool.create();
			if (s != null) {
				fail("String muss Null sein");
			}

		} catch (Exception ex) {
		    LOG.error(UNERWARTETE_AUSNAHME,ex);
			fail(AUSNAHME_AUFGETRETEN);
		}
	}
	
	

}
