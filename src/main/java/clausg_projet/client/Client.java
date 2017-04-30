package clausg_projet.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Class Client
 */
public class Client {
	private static int nextId = 0;
	private Socket socket;
	private int serveurPort;
	private String serveurAddr;
	private int id;
	private BufferedReader in;
	private PrintWriter out;

	public Client(String serveurA, int serveurP) {
		id = nextId++;
		serveurAddr = serveurA;
		serveurPort = serveurP;
	}


	public void start() {
		System.out.println("Démarrage du client " + id + " sur le port " + serveurPort);
		try {
			socket = new Socket(InetAddress.getByName(serveurAddr), serveurPort);
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Client error : " + e.getMessage());
		}
	}

	public void stop() {
		try {
			out.print("QUIT");
			out.flush();
			socket.close();
		} catch (IOException e) {
			System.err.println("Client error : " + e.getMessage());
		}
	}

}
