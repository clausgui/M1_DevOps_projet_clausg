package clausg_projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @class Client
 */
public class Client {
	private static final int defaultServeurPort = 5342;
	private static final String defaultServeurAddr = "127.0.0.1";
	private static Socket socket;
	private static int serveurPort;
	private static String serveurAddr;


    public static void main(String[] args) {
		if (args != null) {
			if (args.length >= 2) {
				serveurAddr = args[0];
				serveurPort = Integer.parseInt(args[1]);
			} else if (args.length == 1) {
				serveurAddr = defaultServeurAddr;
				serveurPort = Integer.parseInt(args[0]);
			} else {
				serveurAddr = defaultServeurAddr;
				serveurPort = defaultServeurPort;
			}
		} else {
			serveurAddr = defaultServeurAddr;
			serveurPort = defaultServeurPort;
		}

		try {
			socket = new Socket(InetAddress.getByName(serveurAddr), serveurPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream());

                out.println("Vous êtes connecté zéro !");

                out.flush();


		} catch (IOException e) {
			System.out.println("Client error : " + e.getMessage());
		}
	}

}