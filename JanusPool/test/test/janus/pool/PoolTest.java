package test.janus.pool;

import static org.junit.Assert.*;


import java.sql.Connection;

import org.janus.pool.ShivaPool;
import org.junit.Test;

public class PoolTest {

	@Test
	public void testCreation() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<TestString>(10, 10, shiva);
		} catch (Exception ex) {
			fail("Ausnahme aufgetreten");
		}
	}

	@Test
	public void test1() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<TestString>(10, 10, shiva);

			TestString s1 = pool.create();
			TestString s2 = pool.create();
			TestString s3 = pool.create();

			assertEquals(3, shiva.createdCount);

			pool.destroy(s1);
			pool.destroy(s2);
			pool.destroy(s3);

			assertEquals(3, shiva.createdCount);
			assertEquals(0, shiva.destroyedCount);

		} catch (Exception ex) {
			fail("Ausnahme aufgetreten");
		}
	}

	@Test
	public void test2() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<TestString>(10, 10, shiva);

			TestString s1 = pool.create();
			TestString s2 = pool.create();
			TestString s3 = pool.create();

			assertEquals(3, shiva.createdCount);
			assertEquals(3, pool.getActiveCount());

			pool.destroy(s1);
			pool.destroy(s2);
			pool.destroy(s3);

			assertEquals(3, shiva.createdCount);
			assertEquals(0, shiva.destroyedCount);
			assertEquals(0, pool.getActiveCount());
			assertEquals(3, pool.getInActiveCount());

			s3 = pool.create();

			assertEquals(3, shiva.createdCount);
			assertEquals(0, shiva.destroyedCount);
			assertEquals(1, pool.getActiveCount());
			assertEquals(2, pool.getInActiveCount());

			s3 = pool.create();

			assertEquals(3, shiva.createdCount);
			assertEquals(0, shiva.destroyedCount);
			assertEquals(2, pool.getActiveCount());
			assertEquals(1, pool.getInActiveCount());

			s3 = pool.create();

			assertEquals(3, shiva.createdCount);
			assertEquals(0, shiva.destroyedCount);
			assertEquals(3, pool.getActiveCount());
			assertEquals(0, pool.getInActiveCount());

			s3 = pool.create();

			assertEquals(4, shiva.createdCount);
			assertEquals(0, shiva.destroyedCount);
			assertEquals(4, pool.getActiveCount());
			assertEquals(0, pool.getInActiveCount());

			pool.destroyAll();
			
			assertEquals(4, shiva.createdCount);
			assertEquals(4, shiva.destroyedCount);
			assertEquals(0, pool.getActiveCount());
			assertEquals(0, pool.getInActiveCount());
			
			
		} catch (Exception ex) {
			fail("Ausnahme aufgetreten");
		}
	}

	@Test
	public void test3() {
		try {
			TestShiveFabric shiva = new TestShiveFabric();
			ShivaPool<TestString> pool = new ShivaPool<TestString>(10, 10, shiva);

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
			fail("Ausnahme aufgetreten");
		}
	}
	
	

}
