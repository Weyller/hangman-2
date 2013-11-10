package hangman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	
	private String address;
	private int port;

	public Client(String address, int port) {
		// TODO Auto-generated constructor stub
		this.address = address;
		this.port = port;
	
	try {
		Socket socket = new Socket(address,port);
		socket.setSoTimeout(10000);
		PrintWriter wr = new PrintWriter(socket.getOutputStream());
		wr.println("newgame");
		wr.flush();
		socket.close();
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


}
