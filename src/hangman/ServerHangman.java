package hangman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerHangman {

	String address;
	int host;
	ServerSocket serverSocket;

	public ServerHangman(int host) throws IOException{

		this.address = address;
		this.host = host;

		try {
			this.serverSocket = new ServerSocket(host);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			try {
				Socket socket = serverSocket.accept();
				Handler handler =  new Handler(socket);
				handler.setPriority( handler.getPriority() + 1 );
				handler.start();
			}
			catch (SocketException e) { e.printStackTrace(); }
		}

	}

}
