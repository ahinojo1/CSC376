import java.net.*;
import java.util.Scanner;
import java.io.*;
public class MessengerWithFiles {
	
	public static void server(int port_number) throws IOException {
		System.out.println("Server function started");
		FTPServer s = new FTPServer(port_number);
		Thread serverThread = new Thread(s);
		serverThread.start();
		
		MessageServer m = new MessageServer(port_number);
		Thread mThread = new Thread(m);
		mThread.start();
	}
	public static void client(int port_number, int connect_port) throws IOException{
		Socket client_socket = new Socket("localhost", port_number);
		DataInputStream input = new DataInputStream(client_socket.getInputStream());
		DataOutputStream output = new DataOutputStream(client_socket.getOutputStream());
		
		System.out.println("Send a message (m)");
		System.out.println("Request a file (f)");
		System.out.println("Exit the program (x)");
		
		BufferedReader std_input= new BufferedReader( new InputStreamReader(System.in) );
		String user_msg;
		Scanner sc;
		while ((user_msg=std_input.readLine()) != null) {
			
			
			if(user_msg.equalsIgnoreCase("m")) {
				System.out.println("Enter your message: ");
				
				sc = new Scanner(System.in);
				PrintWriter server_output = new PrintWriter( client_socket.getOutputStream(), true );
				server_output.println(sc.nextLine());
				
				//output.writeUTF(user_msg);
				
				System.out.println("Send a message (m)");
				System.out.println("Request a file (f)");
				System.out.println("Exit the program (x)");
				
			}else if (user_msg.equalsIgnoreCase("f")) {
				
				
				//create File Client ; pass file name
				System.out.println("Enter file name: ");
				sc = new Scanner(System.in);
				String fileName = sc.nextLine();
				output.writeUTF(fileName);
				FTPClient fileClient = new FTPClient(port_number, fileName);
				Thread fcThread = new Thread(fileClient);
				fcThread.start();
				
				System.out.println("Send a message (m)");
				System.out.println("Request a file (f)");
				System.out.println("Exit the program (x)");
			}else if(user_msg.equalsIgnoreCase("x")) {
				return;
			}else {
				System.out.println("Invalid command. Try again.");
				
				System.out.println("Send a message (m)");
				System.out.println("Request a file (f)");
				System.out.println("Exit the program (x)");
			}
		}
		
		System.out.println("Terminating program");
		client_socket.close();
		System.exit(0);
		//run thread depending on new option
	}
	
	
	public static void main(String[] args) {
		
		if(args.length == 2 && args[0].equals("-l")) {
			try {
				System.out.println("Listening on port " + args[1]);
				server(Integer.valueOf(args[1]));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(args.length == 4 && args[0].equals("-l") && args[2].equals("-p")) {
			int listening_port = Integer.valueOf(args[1]);
			int connect_port = Integer.valueOf(args[3]);
			try {
				client(listening_port, connect_port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else {
			System.err.println("Usage: ");
			System.err.println("For server: java MessengerWithFiles -l <listening port>");
			System.err.println("For client: java MessengerWithFiles -l <listening port> -p <connection port>");
			return;
		}
		// Am I am server or a client 
		
		// if client call client() 
		
		// else if server call server()
		
		// else print usage & return error
		
	}
}
