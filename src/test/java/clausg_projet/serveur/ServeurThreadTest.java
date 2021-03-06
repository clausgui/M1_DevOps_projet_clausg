package clausg_projet.serveur;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import org.junit.rules.Timeout;

import clausg_projet.store.MemStore;
import clausg_projet.client.Client;

// TODO tester avec de mauvaise commande, ou avec des valeurs null; " "


/**
 * Unit test for ServeurThread
 */
public class ServeurThreadTest {
	@Rule
	public Timeout globalTimeout= new Timeout(5000);

	static int port = 5296;
	private ServeurEcoute serveur;


	@Before
	public void startServeur() {
		serveur = new ServeurEcoute(port++, new MemStore());
		serveur.start();
	}

	@After
	public void stopServeur() throws IOException {
		serveur.halt();
	}

    /**
     * Test la connection du client
     */
	@Test
    public void testServeurEcouteClient() throws IOException {
		Client client = new Client("127.0.0.1", serveur.getPort());
		client.start();
    }

    /**
     * Test l'impossibilité d'ouvrir 2 serveurs sur le même port
     */
	@Test
    public void testServeurEcouteException(){
		ServeurEcoute serveur2 = new ServeurEcoute(port, new MemStore());
		serveur2.start();
    }


}
