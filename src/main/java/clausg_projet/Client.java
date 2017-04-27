package clausg_projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @class Client
 */
public class Client {
	private static int nextId = 0;
	private Socket socket;
	private int serveurPort;
	private String serveurAddr;
	private int id;

	public Client(String serveurA, int serveurP) {
		id = nextId++;
		serveurAddr = serveurA;
		serveurPort = serveurP;
	}


	public void start() {
		System.out.println("Démarrage du client " + id + " sur le port " + serveurPort);
		try {
			socket = new Socket(InetAddress.getByName(serveurAddr), serveurPort);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.print("Message du client" + id);
			out.flush();
			socket.close();
		} catch (IOException e) {
			System.err.println("Client error : " + e.getMessage());
		}

	}
}
