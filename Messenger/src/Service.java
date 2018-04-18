import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Service implements Runnable {
	Socket client_socket;
	public Service(Socket socket) {
		this.client_socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try	{
			
			ServerSocket server_socket= new ServerSocket( client_socket.getPort());
			System.out.println( "Listening on port " + Integer.toString( client_socket.getPort() ) );
			Socket client_socket= server_socket.accept();
			server_socket.close();
			BufferedReader input= new BufferedReader( 
			 new InputStreamReader(client_socket.getInputStream()) );
			PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
			String line= input.readLine();
			while( line != null ){
				output.println( line );
				line= input.readLine();
			}
			client_socket.close();
		}
		catch ( Exception e ){
			System.out.println( e.getMessage() );
		}
		
	}
}
