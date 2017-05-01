package clausg_projet.store;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;

/**
 * Unit test for MemStore
 */
public class MemStoreTest {
	@Rule
	public Timeout globalTimeout= new Timeout(5000);

	private MemStore store;

	@Before
	public void CreateStore() {
		store = new MemStore();
	}

    /**
     * Teste un store/get
     */
	@Test
    public void testStore() {
		String s = "Test";
		Integer i = store.store(s);
		assertEquals(s, store.get(i));
    }

    /**
     * Teste une modification
     */
	@Test
    public void testModif() {
		String s = "Test";
		Integer i = store.store(s);
		String s2 = "Test2";
		store.store(i, (Object)s2);
		assertEquals(s2, store.get(i));
    }

    /**
     * Teste un get sur id inconnu
     */
	@Test
    public void testUnknownId() {
		assertNull(store.get(1));
    }

    /**
     * Teste un remove
     */
	@Test
    public void testRemove() {
		String s = "Test";
		Integer i = store.store(s);
		store.remove(i);
		assertNull(store.get(1));
    }

    /**
     * Teste la taille max
     */
	@Test
    public void testSize() {
		store = new MemStore(2);
		Integer i1 = store.store("test1");
		Integer i2 = store.store("test2");
		Integer i3 = store.store("test3");
		assertNull(store.get(i1));
    }

    /**
     * Teste la politique LRU
     */
	@Test
    public void testLRU() {
		store = new MemStore(2);
		Integer i1 = store.store("test1");
		Integer i2 = store.store("test2");
		store.store(i1, "test3");
		Integer i3 = store.store("test4");
		assertNull(store.get(i2));
    }
}
