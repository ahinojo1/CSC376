import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	Socket client_socket;
	
	public Client(Socket client_socket) {
		this.client_socket = client_socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//System.out.print("connecting to port ");
			//System.out.println(client_socket.getPort());
			
			BufferedReader std_input= new BufferedReader( new InputStreamReader(System.in) );
			BufferedReader input= new BufferedReader( 
			 new InputStreamReader(client_socket.getInputStream()) );
			PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
			String user_msg, reply;
			while( (user_msg= std_input.readLine()) != null ){
				output.println( user_msg );
				reply= input.readLine();
				System.out.println( reply );
			}
		} catch(Exception e){
			System.out.println( "Error: " + e.getMessage() );
		}
		
	}

}
