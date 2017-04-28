package clausg_projet;

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
}
