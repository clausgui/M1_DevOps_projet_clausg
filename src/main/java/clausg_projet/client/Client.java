package clausg_projet.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ConnectException;
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

	/**
	 * Constructeur
	 * @param serveurA server address
	 * @param serveurP server port
	 */
	public Client(String serveurA, int serveurP) {
		id = nextId++;
		serveurAddr = serveurA;
		serveurPort = serveurP;
	}

	/**
	 * start the client
	 * @throws IOException if connection can't happen
	 */
	public void start() throws IOException,ConnectException {
		System.out.println("Démarrage du client " + id + " sur le port " + serveurPort);
		socket = new Socket(InetAddress.getByName(serveurAddr), serveurPort);
		if (socket != null) {
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			System.out.println("Client " + id + " connecté sur le port " + serveurPort);
		}
	}

	/**
	 * stop the client
	 */
	public void stop() throws IOException  {
		if (socket != null && socket.isConnected() && ! socket.isClosed()) {
			out.print("QUIT\n");
			out.flush();
			socket.close();
		}
	}

	/**
	 */
	// TODO : gestion des espace dans la valeur
	public Integer store(String value) throws IOException {
		out.print("STO " + value + "\n");
		out.flush();
		String msg = in.readLine();
		System.out.println("Le client " + id + " a reçu : '" + msg + "'");
		String[] args = msg.split(" ");
		if (args.length == 2 && args[0].equals("1")) {
			return Integer.valueOf(args[1]);
		}
		return null;
	}

	/**
	 */
	// TODO : gestion des espace dans la valeur
	public Integer store(Integer key, String value) throws IOException {
		out.print("STOTO " + key + " " + value + "\n");
		out.flush();
		String msg = in.readLine();
		System.out.println("Le client " + id + " a reçu : '" + msg + "'");
		String[] args = msg.split(" ");
		if (args.length == 2 && args[0].equals("1")) {
			return Integer.valueOf(args[1]);
		}
		return null;
	}

	// TODO : gestion des espaces dans la valeur de retour
	public String get(Integer key) throws IOException {
		out.print("GET " + key + "\n");
		out.flush();
		String msg = in.readLine();
		System.out.println("Le client " + id + " a reçu : '" + msg + "'");
		String[] args = msg.split(" ");
		if (args.length >= 2 && args[0].equals("1")) {
			return args[1];
		}
		return null;
	}

	public boolean remove(Integer key) throws IOException {
		out.print("DEL " + key + "\n");
		out.flush();
		String msg = in.readLine();
		System.out.println("Le client " + id + " a reçu : '" + msg + "'");
		String[] args = msg.split(" ");
		if (args.length >= 1 && args[0].equals("1")) {
			return true;
		}
		return false;
	}

}
