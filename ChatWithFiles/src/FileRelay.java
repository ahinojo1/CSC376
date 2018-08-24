import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

//FOR SERVER TO ASK FOR FILE FROM OWNER AND SEND TO REQUESTING CLIENT
public class FileRelay implements Runnable {
	String owner, file_name;
	DataInputStream input;
	DataOutputStream output;
	Socket client_socket;
	int l_port;
	ArrayList<DataOutputStream> clientList;
	public FileRelay(String owner, String file_name, int l_port, ArrayList<DataOutputStream> clientList) {
		this.owner = owner;
		this.file_name = file_name;
		this.l_port = l_port;
		this.clientList = clientList;
		try {
			client_socket = new Socket("localhost", this.l_port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void serviceRequest(DataOutputStream potentialOwner) throws IOException {
		input= new DataInputStream( client_socket.getInputStream() );
		File file= new File( file_name );
		if ( file.exists() && file.canRead() )
		{
			// return the number of bytes in the file as a long int
			long file_size= file.length();
			if ( file_size > 0 )
				potentialOwner.writeLong( file_size );
			else
			{
				potentialOwner.writeLong( 0L );
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
			potentialOwner
			.write( file_buffer, 0, number_read );
		file_input.close();		
	}
	public void run() {
		System.out.println("File relay thread received owner: " + owner + " and file name: " + file_name);
		try {
			for(DataOutputStream o : clientList) {
				serviceRequest(o);
			}
			client_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}