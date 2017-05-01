package clausg_projet.client;


import java.io.Console;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * AppServeur
 */
public class AppClient {
	private static final int defaultServeurPort = 5342;
	private static final String defaultServeurAddr = "127.0.0.1";//"172.17.0.2";

	private static Client client;
	private static int serveurPort; // port d'écoute du serveur
	private static String serveurAddr;
	private static Console console;
	private static String cmd;


    public static void main(String[] args) {
		if ((console = System.console() ) == null) {
			System.out.println("pas de console");
			return;
		}
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
			boolean stop = false;
			Pattern cmdPattern = Pattern.compile("([a-z]+)(.*)");
			Pattern keyPattern = Pattern.compile(" (\\d+)");
			Pattern keyValPattern = Pattern.compile(" (\\d+) (.*)");
			Matcher cmdMatcher, m;
			Integer key;
			String value;

			client.start();
			while (! stop && (cmd = console.readLine("Command ('help' for help):\n")) != null) {
				cmdMatcher = cmdPattern.matcher(cmd);
				if (! cmdMatcher.matches()) {
					_bad_command();
					continue;
				}
				switch(cmdMatcher.group(1)) {
					case "sto":
						key = client.store(cmdMatcher.group(2));
						console.printf("key: %d\n", key);
						break;
					case "stoto":
						m = keyValPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = Integer.valueOf(m.group(1));
						client.store(key, m.group(2));
						console.printf("key: %d\n", key);
						break;
					case "get":
						m = keyPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = Integer.valueOf(m.group(1));
						value = client.get(key);
						if (value == null) {
							console.printf("no value\n");
						} else {
							console.printf("value: %s\n", value);
						}
						break;
					case "del":
						m = keyPattern.matcher(cmdMatcher.group(2));
						if (! m.matches()) {
							_bad_command();
							continue;
						}
						key = Integer.valueOf(m.group(1));
						value = client.get(key);
						client.remove(key);
						console.printf("key %d deleted\n", key);
						break;
					case  "quit":
						stop = true;
						break;
					case "help":
						_help();
						break;
					default:
						_bad_command();
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				client.stop();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}


    }

    private static void _bad_command() {
		console.printf("Bad command\n");
	}

	private static void _help() {
		console.printf("help :\n");
		console.printf("\tsto value\tstores the value and return the mapped key\n");
		console.printf("\tsto key value\tmaps the key to the value\n");
		console.printf("\tget key\t\treturn the value mapped with this key\n");
		console.printf("\tdel key\t\tdelete le value mapped with this key\n");
		console.printf("\tquit\t\tquit the client\n");
	}
}
