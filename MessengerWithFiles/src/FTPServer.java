import java.io.*;
import java.net.*;

public class FTPServer implements Runnable{
	int port_number;
	ServerSocket server_socket;
	Socket client_socket;
	String fileName;
	public FTPServer(int port_number) throws IOException {
		this.port_number = port_number;
		System.out.println("Server object created");
		
		server_socket = new ServerSocket(port_number);
		client_socket = server_socket.accept();
		System.out.println("Socket accepted");
	}
	public void serviceRequest() throws IOException {
		DataInputStream input= new DataInputStream( client_socket.getInputStream() );
		DataOutputStream output= new DataOutputStream( client_socket.getOutputStream() );		
		String file_name= input.readUTF();
		File file= new File( file_name );
		if ( file.exists() && file.canRead() )
		{
			// return the number of bytes in the file as a long int
			long file_size= file.length();
			if ( file_size > 0 )
				output.writeLong( file_size );
			else
			{
				output.writeLong( 0L );
				return;
			}
		}
		else
		{
			output.writeLong( 0L );			
			return;
		}
		FileInputStream file_input= new FileInputStream(file);
		System.out.println( "Transmitting file: " + file_name );
		byte[] file_buffer= new byte[1500];
		int number_read;
		while( (number_read= file_input.read( file_buffer )) != -1 )
			output.write( file_buffer, 0, number_read );
		file_input.close();		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Server thread started");
		try {
			
			serviceRequest();
			System.out.println("Service request for file transfer initiated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	
