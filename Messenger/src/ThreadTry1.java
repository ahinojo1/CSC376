import java.net.*;
import java.io.*;


public class ThreadTry1{
	public static void main(String[] args){
		ThreadTry2 t2 = new ThreadTry2(args[0]);
		Thread th2 = new Thread(t2);
		th2.start();
	}
}

