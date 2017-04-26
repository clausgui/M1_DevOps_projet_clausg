package clausg_projet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @class Client
 */
public class Client {
	private Socket socket;

	public Client(InetAddress addr, int port) {
		try {
			socket = new Socket(addr, port);
		} catch (IOException e) {
			System.out.println("Client error : " + e.getMessage());
		}
	}

}