package clausg_projet.serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clausg_projet.store.MemStore;

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
		Pattern cmdPattern = Pattern.compile("(\\p{Upper}+)(.*)");
		Pattern keyPattern = Pattern.compile(" (\\d+)");
		Pattern valPattern = Pattern.compile(" (.*)");
		Pattern keyValPattern = Pattern.compile(" (\\d+) (.*)");
		Matcher cmdMatcher, m;

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
				cmdMatcher = cmdPattern.matcher(msg);
				if (! cmdMatcher.matches()) {
					_bad_command();
					continue;
				}

				switch(cmdMatcher.group(1)) {
					case "STO":
						m = valPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = store.store(m.group(1));
						_send(key);
						break;
					case "STOTO":
						m = keyValPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = Integer.valueOf(m.group(1));
						store.store(key, m.group(2));
						_send(key);
						break;
					case "GET":
						m = keyPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = Integer.valueOf(m.group(1));
						value = store.get(key);
						_send(value);
						break;
					case "DEL":
						m = keyPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = Integer.valueOf(m.group(1));
						value = store.get(key);
						store.remove(key);
						_send("");
						break;
					case  "QUIT":
						stop = true;
						break;
					default:
						_bad_command();
				}
			}
			_close();
		} catch (java.io.IOException e) {
			System.err.println("ServeurThread : " + e.getMessage());
		}
		System.out.println("ServeurThread " + id + " terminé");
	}

	public void halt() throws IOException {
		_close();
	}

	private void _send(Object msg) {
		System.out.println("_send(" + msg +")");
		if (msg == null) {
			out.print("2\n");
		} else {
			out.print("1 " + msg.toString() + "\n");
		}
		out.flush();
	}

	private void _bad_command() {
		out.print("0 BAD_COMMAND\n");
		out.flush();
	}

	private void _close() throws IOException {
		if (socket != null) {
			socket.close();
			System.out.println("Arrêt du ServeurTread " + id);
		}
	}


}
