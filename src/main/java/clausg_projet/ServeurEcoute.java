package clausg_projet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @class ServeurEcoute
 */
public class ServeurEcoute extends Thread  {
	private int port;
	private ServerSocket serveurSocket;
	private Socket socket;
	private boolean continuer;


	public ServeurEcoute(int port) {

		this.port = port;
		continuer = true;
	}

	/**
	 * Lance le serveur
	 */
	public void run() {
		try {
			serveurSocket = new ServerSocket(port);
			System.out.println("Serveur démarré, en attente de connexions");
			while(continuer) {
				socket = serveurSocket.accept();
				new ServeurThread(socket);
			}
			serveurSocket.close();
			System.out.println("Serveur Arrêté");
		} catch (IOException e) {
			System.err.println("Problème de connexion : " + e.getMessage());
		}
	}

	/**
	 * stoppe le serveur
	 */
	public void halt() {
// 		try {
			continuer = false;
// 			serveurSocket.close();
			System.out.println("Arrêt du serveur");
// 		} catch (IOException e) {
// 			System.err.println("Problème de connexion : " + e.getMessage());
// 		}

	}
}
