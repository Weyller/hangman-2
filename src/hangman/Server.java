package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server(){ 

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(10000);

			try {

				while(true){
					Socket clientSocket = serverSocket.accept();
					BufferedReader rd = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					String str;
					while((str = rd.readLine()) != null)
						System.out.println(str);
					clientSocket.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 

}
