package clausg_projet.serveur;


import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.Scanner;

import clausg_projet.store.MemStore;

/**
 * AppServeur
 */
public class AppServeur {
	private static final int defaultServeurPort = 5342;

	private static ServeurEcoute serveur;
	private static int serveurPort; // port d'écoute du serveur

	private static MemStore mstore;


    public static void main(String[] args) {
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

		serveur = new ServeurEcoute(serveurPort, mstore);
		System.out.println("Lancement du serveur sur le port " + serveurPort);
		serveur.start();
		Scanner in = new Scanner(System.in);
		System.out.println("Type 'q' to quit.");
		while(in.hasNext()) {
			if (in.next().equals("q")) {
				try {
					serveur.halt();
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
				return;
			}
		}
    }
}
