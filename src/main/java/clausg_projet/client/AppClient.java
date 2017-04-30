package clausg_projet.client;

import java.io.IOException;
import java.lang.NumberFormatException;

/**
 * AppServeur
 */
public class AppClient {
	private static final int defaultServeurPort = 5342;
	private static final String defaultServeurAddr = "127.0.0.1";//"172.17.0.2";

	private static Client client;
	private static int serveurPort; // port d'écoute du serveur
	private static String serveurAddr;


    public static void main(String[] args) {
		try {
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
		} catch (NumberFormatException e) {
			System.err.println("AppClient error : " + e.getMessage());
			return;
		}

		client = new Client(serveurAddr, serveurPort);
		try {
			client.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
}
