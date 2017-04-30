package clausg_projet.client;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import org.junit.rules.Timeout;

/**
 * Unit test for Client.
 */
public class ClientTest {
	@Rule
	public Timeout globalTimeout= new Timeout(5000);

	static final int port = 5299;
	static final String addr = "127.0.0.1";

    /**
     * Test un client sans serveur
     */
	@Test
    public void testClient() {
		Client client = new Client(addr, port);
		client.start();
// 		client.stop();
    }
}
