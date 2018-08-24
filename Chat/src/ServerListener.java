import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Map.Entry;


public class ServerListener implements Runnable{
	Socket client_socket;
	Map<Socket, String> list = ChatServer.clientList;
	
	public ServerListener(Socket client_socket) {
		this.client_socket = client_socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//DO NOT JOIN THREADS
		//Assume first message from client is its name
		try {
			getClientSocket();
			for(Map.Entry<Socket, String> e : list.entrySet()) {
				BufferedReader input= new BufferedReader( 
						 new InputStreamReader(client_socket.getInputStream()) );
				PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
				String name = ChatServer.getClient(client_socket);
				String msg = input.readLine();
				if (e.getKey() != client_socket) {
					output.println(name + ":" + msg);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
