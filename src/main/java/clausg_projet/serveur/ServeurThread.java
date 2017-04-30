package clausg_projet.serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import clausg_projet.MemStore;

/**
 * Class ServeurThread
 */
public class ServeurThread  extends Thread  {
	private static int nextid = 1;
	private Socket socket;
	private int id;
	private MemStore store;
	private BufferedReader in;
	private PrintWriter out;

	public ServeurThread(Socket socket, MemStore memStore) {
		this.id = nextid++;
		this.socket = socket;
		store = memStore;
		this.start();
	}

	public void run() {
		boolean stop = false;
		Integer key;
		Object value;

		System.out.println("ServeurThread " + id + " lancé");
		try {
			in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			while (! stop) {
				String msg = in.readLine();
				if (msg == null) {
					continue;
				}
				System.out.println("ServeurThread " + id + " a reçu '" + msg + "'");
				String[] args = msg.split(" ");

				switch(args[0]) {
					case "STO":
						if (args.length == 2) {
							key = store.store(args[1]);
							_send(key);
						} else if (args.length == 3) {
							key = Integer.valueOf(args[1]);
							store.store(key, args[2]);
							_send(key);
						} else {
							_bad_command();
						}
						break;
					case "GET":
						if (args.length == 2) {
							key = Integer.valueOf(args[1]);
							value = store.get(key);
							_send(value);
						} else {
							_bad_command();
						}
						break;
					case "DEL":
						if (args.length == 2) {
							key = Integer.valueOf(args[1]);
							store.remove(key);
							_send("");
						} else {
							_bad_command();
						}
						break;
					case  "QUIT":
						stop = true;
						break;
					default:
						_bad_command();
				}
			}
			socket.close();
		} catch (java.io.IOException e) {
			System.err.println("ServeurThread error : " + e.getMessage());
		}
		System.out.println("ServeurThread " + id + " terminé");
	}

	private void _send(Object msg) {
		if (msg == null) {
			out.print("2 ");
		} else {
			out.print("1 " + msg.toString());
		}
		out.flush();
	}

	private void _bad_command() {
		out.print("0 BAD COMMAND");
		out.flush();
	}



}
