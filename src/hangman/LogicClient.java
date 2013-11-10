package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LogicClient implements Runnable{

	private String address;
	private int port;
	Socket clientSocket;
	String ligne;

	public LogicClient(String address, int port){
		this.address = address;
		this.port = port;
	}

	public void run() {

		this.socketCreation();

	}
	
	public String socketCreation(){
		
		try {
			clientSocket = new Socket(address,port);
			clientSocket.setSoTimeout(10000);
			/*PrintWriter wr = new PrintWriter(clientSocket.getOutputStream());
			wr.println("newgame");
			wr.flush();
			clientSocket.close();*/
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OK";
	}
	
	public String newGame(){
		try {
			PrintWriter wr = new PrintWriter(clientSocket.getOutputStream());
			wr.println("newgame");
			wr.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "newgame";
	}
	
	public void closeSocket(){
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String EntreeClavier(){
		
		System.out.println("Entrer qqc");
	    BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
	    try {
			ligne = entree.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ligne;
	}

}
