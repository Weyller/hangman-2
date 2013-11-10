package hangman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerHangman implements Runnable {

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

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				Handler handler =  new Handler(socket);
				handler.setPriority( handler.getPriority() + 1 );
				handler.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
