package clausg_projet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
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
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			String message_distant = in.readLine();
			System.out.println(message_distant);
		} catch (java.io.IOException e) {
			System.out.println("ServeurThread error : " + e.getMessage());
		}
		System.out.println("ServeurThread " + id + " terminé");
	}
}