import java.net.*;
import java.io.*;
public class MessageServer implements Runnable{
	ServerSocket server_socket;
	Socket client_socket;
	int port_number;
	
	public MessageServer(int port_number) throws UnknownHostException, IOException {
		this.port_number = port_number;
		client_socket = new Socket("localhost", port_number);
	}
	
	public void listen() throws IOException {
				while(true) {
					BufferedReader reader= new BufferedReader( 
							 new InputStreamReader(client_socket.getInputStream()) );
					
					
					// read the input
					String input_line= reader.readLine();
					System.out.println( "Received from client: " );
					System.out.println( input_line );
				}

	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
