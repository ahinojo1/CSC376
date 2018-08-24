
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Usage: ");
			System.err.println("java ChatClient <port number>");
			return;
		}
		else {
			int port_number = Integer.parseInt(args[0]);
			System.out.println("Enter your username: ");
			Socket client_socket;
			try {
				client_socket = new Socket( "localhost", port_number );
				ClientListener clientListener = new ClientListener(client_socket);
				Thread listenThread = new Thread(clientListener);
				listenThread.start();
				//send 
				BufferedReader std_input = new BufferedReader(new InputStreamReader(System.in ));
				PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
				String out_msg;
				while((out_msg = std_input.readLine()) != null) {
					output.println(out_msg);
				}
				client_socket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
