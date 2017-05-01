package clausg_projet.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Deque;
import java.util.LinkedList;

import clausg_projet.store.MemStore;

/**
 * Class ServeurEcoute
 */
public class ServeurEcoute extends Thread  {
	private Integer port;
	private ServerSocket serveurSocket;
	private Socket socket;
	private boolean continuer;
	private MemStore store;
	private Deque<ServeurThread> threads;



	public ServeurEcoute(int port, MemStore memStore) {
		this.port = port;
		this.store = memStore;
		this.continuer = true;
		this.threads = new LinkedList<>();
	}

	public Integer getPort() {
		return port;
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
			while(continuer && serveurSocket != null) {
				socket = serveurSocket.accept();
				if (socket != null) {
					threads.addLast(new ServeurThread(socket, store));
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
		while (! threads.isEmpty()) {
			ServeurThread sth = threads.removeFirst();
			if (sth != null) {
				sth.halt();
			}
		}
	}
}
