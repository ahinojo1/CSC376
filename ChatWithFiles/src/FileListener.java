import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

//FOR CLIENTS TO THREAD AFTER REQUESTING A FILE
//WILL GIVE FILE TO SERVER OR DOWNLOAD FILE REQUESTED FROM SERVER
public class FileListener implements Runnable{
	Socket client_socket;
	int l_port;
	static DataInputStream input;
	static DataOutputStream output;
	String file_name;
	public FileListener(String filename, int l_port) {
		this.file_name = filename;
		this.l_port = l_port;
		
		
	}
	public static long getFile(String file_name ) throws IOException {
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
	public void run() {
		try {
			client_socket= new Socket("localhost", l_port);
			input= new DataInputStream( client_socket.getInputStream() );
			output= new DataOutputStream( client_socket.getOutputStream() );
			getFile(file_name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
