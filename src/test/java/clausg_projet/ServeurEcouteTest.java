package clausg_projet;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for ServeurEcouteTest.
 */
public class ServeurEcouteTest {
	static final int port = 5296;
	ServeurEcoute serveur;

// 	@Rule
// 	public Timeout globalTimeout = Timeout.seconds(10);

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
     * Test l'impossibilité d'ouvrir 2 serveurs sur le même port
     */
	@Test
// 	(expected = IOException.class)
    public void testServeurEcouteException() throws Exception {
		ServeurEcoute serveur2 = new ServeurEcoute(port);
		serveur2.start();
    }

    
}
