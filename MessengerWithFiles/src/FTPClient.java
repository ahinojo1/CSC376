import java.io.*;
import java.net.*;

public class FTPClient implements Runnable{
	Socket client_socket;
	DataInputStream input;
	DataOutputStream output;
	String fileName;
	
	public FTPClient(int port_number, String fileName) throws UnknownHostException, IOException {
		client_socket = new Socket("localhost", port_number);
		input = new DataInputStream( client_socket.getInputStream() );
		output= new DataOutputStream( client_socket.getOutputStream() );
		
	}
	
	public long getFile( String file_name ) throws IOException  {
		output.writeUTF( file_name );
		long file_size= input.readLong();
		if ( file_size == 0 )
			return 0;
		FileOutputStream file_out= new FileOutputStream( file_name );	
		int number_read;
		byte[] buffer= new byte[1500];
		while( (number_read= input.read( buffer)) != -1 )
			file_out.write( buffer, 0, number_read );
		file_out.close();
		return file_size;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			getFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
