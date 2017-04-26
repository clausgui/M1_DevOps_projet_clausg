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
			System.out.println("Serveur Arrêté");
		} catch (IOException e) {
			System.out.println("Problème de connexion : ");
		}
	}

	/**
	 * stoppe le serveur
	 */
	public void halt() {
		continuer = false;
		System.out.println("Arrêt du serveur");
	}
}