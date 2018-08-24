import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable {
	Socket client_socket;
	public ClientListener(Socket socket) {
		this.client_socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try
		{
			// input the message from standard input
			
			BufferedReader server_reply= new BufferedReader( 
			 new InputStreamReader(client_socket.getInputStream()) );
			
			while( true ) {
				String response = server_reply.readLine();
				System.out.println(response);
			}
			
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
		}
		
	
	}
}


