package clausg_projet;



/**
 * Hello world!
 *
 */
public class App {
	private static final int defaultServeurPort = 5342;
	
	private static ServeurEcoute serveur;
	private static int serveurPort; // port d'écoute du serveur
	
	private static MemStore mstore;
	
	
    public static void main(String[] args) {
        System.out.println( "Hello World!" );
		mstore = new MemStore();
		if (args == null || args.length < 1) {
			serveurPort = defaultServeurPort;
		} else {
			serveurPort = Integer.parseInt(args[0]);
		}

		serveur = new ServeurEcoute(serveurPort);
		System.out.println("Lancement du serveur sur le port " + serveurPort);
		serveur.start();
		serveur.halt();
    }
}
