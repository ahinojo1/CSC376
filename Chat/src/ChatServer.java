import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
	
	public static Map<Socket, String> clientList = new HashMap<Socket, String>();
	static int port_number;
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: java ChatServer <port number>");
			return;
		}
		else {
			port_number = Integer.parseInt(args[0]);
			ServerSocket server_socket = new ServerSocket(port_number);
			ServerListener listener = new ServerListener(server_socket);
			Thread listenThread = new Thread(listener);
			listenThread.start();
			
			
			while(true) {
				
				try {
					Socket client_socket = server_socket.accept();
					communicate(client_socket);
					BufferedReader reader= new BufferedReader( 
							 new InputStreamReader(client_socket.getInputStream()) );
					String clientName = reader.readLine();
					System.out.println( clientName + " connected to server");
					ChatServer.addClient(clientName, client_socket);
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void communicate(Socket client_socket) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader (client_socket.getInputStream()));
		PrintWriter output = new PrintWriter(client_socket.getOutputStream());
		while((input.readLine()) != null){
			output.println(input.readLine());
		}
		
	}
	
	public static void addClient(String clientName, Socket socket) {
		clientList.put(socket, clientName);
	}
	public static void deleteClient(Socket socket) {
		clientList.remove(socket);
	}
	public static String getClient(Socket socket) {
		return clientList.get(socket);
	}

}
