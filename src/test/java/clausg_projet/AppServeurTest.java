package clausg_projet;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import org.junit.rules.Timeout;

/**
 * Unit test for AppServeur.
 */
public class AppServeurTest {
	InputStream stdin;

	@Rule
	public Timeout globalTimeout= new Timeout(5000);

	@Before
	public void redirectStdin() {
		String data = "q";
		stdin = System.in;
		System.setIn(new ByteArrayInputStream(data.getBytes()));
	}

	@After
	public void restoreStdin() {
		System.setIn(stdin);
	}

    /**
     * Teste le l'application sans argument
     */
     @Test
    public void testAppWithoutArg() {
		AppServeur app = new AppServeur();
		app.main(null);
	    assertTrue(true);
    }

    /**
     * Teste le l'application sans argument
     */
     @Test
    public void testAppEmptyArray() {
		AppServeur app = new AppServeur();
		app.main(new String[]{});
	    assertTrue(true);
    }

    /**
     * Teste le l'application avec un argument numérique
     */
     @Test
    public void testAppWithNumArg() {
		AppServeur app = new AppServeur();
		app.main(new String[]{"12345"});
	    assertTrue(true);
    }

    /**
     * Teste le l'application avec un argument numérique
     */
     @Test
    public void testAppWithNanArg() {
		AppServeur app = new AppServeur();
		app.main(new String[]{"a"});
	    assertTrue(true);
    }

    /**
     * Teste une autre entrée que 'q'
     */
     @Test
    public void testAppOutChar() {
		String data = "a";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		data = "b";
		System.setIn(new ByteArrayInputStream(data.getBytes()));
		AppServeur app = new AppServeur();
		app.main(null);
	    assertTrue(true);
    }

}
