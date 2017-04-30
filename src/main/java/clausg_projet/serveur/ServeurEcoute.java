package clausg_projet.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.Iterator;

import clausg_projet.MemStore;

/**
 * Class ServeurEcoute
 */
public class ServeurEcoute extends Thread  {
	private Integer port;
	private ServerSocket serveurSocket;
	private Socket socket;
	private boolean continuer;
	private MemStore store;
	private Vector<ServeurThread> threads;



	public ServeurEcoute(int port, MemStore memStore) {
		this.port = port;
		this.store = memStore;
		this.continuer = true;
		this.threads = new Vector<>();
	}

	/**
	 * Lance le serveur
	 */
	public void run() {
		try {
			serveurSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Problème de connexion : " + e.getMessage());
		}
		System.out.println("Serveur démarré, en attente de connexions sur le port " + port);
		try {
			while(continuer) {
				socket = serveurSocket.accept();
				if (socket != null) {
					threads.add(new ServeurThread(socket, store));
				}
			}
		} catch (IOException e) {
		} finally {
			if (serveurSocket != null) {
				try {
					serveurSocket.close();
				} catch (IOException e) {
				}
			}
		}
		System.out.println("bye");
	}

	/**
	 * stoppe le serveur
	 */
	public void halt() throws IOException {
		System.out.println("Arrêt du serveur");
		continuer = false;
		if (serveurSocket != null) {
			serveurSocket.close();
		}
		Iterator<ServeurThread> it = threads.iterator();
		while (it.hasNext()) {
			ServeurThread sth = it.next();
			if (sth != null) {
				sth.halt();
			}
		}
	}
}
