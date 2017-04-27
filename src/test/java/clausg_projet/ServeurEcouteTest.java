package clausg_projet;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import org.junit.rules.Timeout;

/**
 * Unit test for ServeurEcouteTest.
 */
public class ServeurEcouteTest {
	@Rule
	public Timeout globalTimeout= new Timeout(5000);

	static final int port = 5296;
	ServeurEcoute serveur;


	@Before
	public void startServeur() {
		serveur = new ServeurEcoute(port);
		serveur.start();
	}

	@After
	public void stopServeur() {
		serveur.halt();
	}

    /**
     * Test la connection du client
     */
	@Test
    public void testServeurEcouteClient() {
		Client client = new Client("127.0.0.1", port);
		client.start();
    }

    /**
     * Test l'impossibilité d'ouvrir 2 serveurs sur le même port
     */
	@Test
    public void testServeurEcouteException(){
		ServeurEcoute serveur2 = new ServeurEcoute(port);
		serveur2.start();
    }


}
