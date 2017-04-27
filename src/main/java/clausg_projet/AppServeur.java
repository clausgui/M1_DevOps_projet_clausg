package clausg_projet;


import java.lang.NumberFormatException;
import java.util.Scanner;

/**
 * AppServeur
 */
public class AppServeur {
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
			int port = 0;
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("App error : " + e.getMessage());
				return;
			}
			serveurPort = (port);
		}

		serveur = new ServeurEcoute(serveurPort);
		System.out.println("Lancement du serveur sur le port " + serveurPort);
		serveur.start();
		Scanner in = new Scanner(System.in);
		System.out.println("Type 'q' to quit.");
		while(in.hasNext()) {
			if (in.next().equals("q")) {
				serveur.halt();
				return;
			}
		}
    }
}
