package clausg_projet;

import org.junit.*;
import static org.junit.Assert.assertTrue;
import org.junit.rules.Timeout;

/**
 * Unit test for AppClient
 */
public class AppClientTest {
	@Rule
	public Timeout globalTimeout= new Timeout(5000);

    /**
     * Teste le l'application sans argument
     */
     @Test
    public void testAppWithoutArg() {
		AppClient app = new AppClient();
		app.main(null);
	    assertTrue(true);
    }

    /**
     * Teste le l'application sans argument
     */
     @Test
    public void testAppEmptyArray() {
		AppClient app = new AppClient();
		app.main(new String[]{});
	    assertTrue(true);
    }

    /**
     * Teste le l'application avec un argument numérique
     */
     @Test
    public void testAppWithNumArg() {
		AppClient app = new AppClient();
		app.main(new String[]{"12345"});
	    assertTrue(true);
    }

    /**
     * Teste le l'application avec un argument numérique
     */
     @Test
    public void testAppWith2Args() {
		AppClient app = new AppClient();
		app.main(new String[]{"127.0.0.1", "12345"});
	    assertTrue(true);
    }

    /**
     * Teste le l'application avec un argument numérique
     */
     @Test
    public void testAppWithNanArg() {
		AppClient app = new AppClient();
		app.main(new String[]{"a"});
	    assertTrue(true);
    }

}
