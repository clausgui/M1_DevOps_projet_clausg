package clausg_projet.client;

import java.net.ConnectException;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;

import clausg_projet.MemStore;
import clausg_projet.serveur.ServeurEcoute;

/**
 * Unit test for Client.
 */
public class ClientTest {
	@Rule
	public Timeout globalTimeout= new Timeout(5000);

	static int port = 5299;
	static final String addr = "127.0.0.1";

    /**
     * Test un client sans serveur
     */
	@Test (expected = ConnectException.class)
    public void testClient() throws IOException ,ConnectException {
		Client client = new Client(addr, port++);
		client.start();
    }

//     /**
//      * Test deux clients avec serveur
//      */
// 	@Test
//     public void testStartStop() throws IOException,ConnectException  {
// 		MemStore ms = new MemStore();
// 		ServeurEcoute sv = new ServeurEcoute(port, ms);
// 		sv.start();
// 		Client client1 = new Client(addr, port);
// 		Client client2 = new Client(addr, port);
// 		client1.start();
// 		client2.start();
// 		sv.halt();
// 		port++;
//     }

//     /**
//      * Teste un store
//      */
// 	@Test
//     public void testStore() throws IOException  {
// 		String value = "truc";
// 		String value2 = "machin";
// 		MemStore ms = new MemStore();
// 		ServeurEcoute sv = new ServeurEcoute(port, ms);
// 		sv.start();
// 		Client client = new Client(addr, port);
// 		client.start();
// 		Integer i = client.store(value);
// 		assertNotNull(i);
// 		client.store(i, value2);
// 		assertEquals(value2, client.get(i));
// 		assertTrue(client.remove(i));
// 		assertNull(client.get(i));
// 		client.stop();
// 		sv.halt();
//     }
}
