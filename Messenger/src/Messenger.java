import java.net.ServerSocket;
import java.net.Socket;

public class Messenger {
	public static void main (String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: For server, use '-l' option; <port_number>");
			return;
		}
		else if(args.length == 1) {
			
			try {
				int port_number= Integer.valueOf( args[0] );
				Socket client_socket = new Socket("localhost", port_number);
				System.out.print("connecting to port ");
				System.out.println(port_number);
				Client c = new Client(client_socket, port_number);
				Thread tc = new Thread(c);
				Server s = new Server(client_socket, port_number);
				Thread ts1 = new Thread(s);
				tc.start();
				ts1.start();
				tc.join();
				ts1.join();
			} catch (Exception e) {
				System.err.println(e);
			}
			
		}
		else if(args.length == 2 && args[0].equals("-l")){
			try {
				int port_number= Integer.valueOf( args[1] );
				
				ServerSocket server_socket= new ServerSocket(port_number);
				System.out.println( "Listening on port " + Integer.toString(port_number));
				Socket client_socket= server_socket.accept();
				server_socket.close();
				
				Server s = new Server(client_socket, port_number);
				Thread ts = new Thread(s);
				Client c1 = new Client(client_socket, port_number);
				Thread tc1 = new Thread(c1);
				ts.start();	
				tc1.start();
				ts.join();
				tc1.join();
			} catch(Exception e) {
				System.err.println(e);
			}
			
		}
		
	}

}
