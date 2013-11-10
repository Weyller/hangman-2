package hangman;

import java.io.IOException;
import java.net.Socket;

public class Handler extends Thread{

	private Socket socket;

	Handler(Socket socket) throws IOException { // thread constructor
		this.socket = socket;
	}
	
	public void run(){
		System.out.println("Foutre.");
	}

}
