import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server implements Runnable {
	Socket client_socket;
	int port;
	public Server(Socket s, int port_number) {
		this.client_socket = s;
		this.port = port_number;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try	{
			
			
			BufferedReader std_input= new BufferedReader( new InputStreamReader(System.in) );
			BufferedReader input= new BufferedReader( 
			 new InputStreamReader(client_socket.getInputStream()) );
			PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
			String user_msg, reply;
			while( (user_msg= std_input.readLine()) != null ){
				
				output.println( user_msg );
				reply= input.readLine();
				System.out.println( reply );
				System.out.println(user_msg);
			}
			client_socket.close();
		}
		catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		
	}
	
	//public void listen(ServerSocket ss) throws IOException {
	//	Socket client_socket= ss.accept();
	//}
}
