
public class Messenger2 {
	public static void main(String[] args) {
		if(args.length > 1) {
			if (args[0].equals("-l")) {
				Server2 server = new Server2();
				Thread threadServer = new Thread(server);
				threadServer.start();
			}
		}
	}

}
