package clausg_projet;

import java.net.Socket;

/**
 * @class ServeurThread
 */
public class ServeurThread  extends Thread  {
	private static int nextid = 1;
	private Socket socket;
	private int id;
	
	public ServeurThread(Socket socket) {
		this.id = nextid++;
		this.socket = socket;
		this.start();
	}

	public void run() {
		System.out.println("ServeurThread " + id + " lancé");
		System.out.println("ServeurThread " + id + " terminé");
	}
}