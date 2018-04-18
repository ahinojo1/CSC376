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
				Socket client_socket= new Socket( "localhost", port_number );
				System.out.print("connecting to port ");
				System.out.println(client_socket.getPort());
				Client c = new Client(client_socket);
				Thread tC = new Thread(c);
				tC.start();
				tC.join();
			} catch (Exception e) {
				
			}
			
		}
		else if(args.length == 2 && args[0].equals("-l")){
			try {
				int port_number= Integer.valueOf( args[1] );
				Socket client_socket= new Socket( "localhost", port_number );
				Service s = new Service(client_socket);
				Thread tS = new Thread(s);
				tS.start();	
				tS.join();
			} catch(Exception e) {
				
			}
			
		}
		
	}

}
