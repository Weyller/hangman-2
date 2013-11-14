package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Observable;

import javax.swing.JOptionPane;

public class LogicClient extends Observable implements Runnable{
	// LogisClient is the thread that is responsible for communicating with the server during the game

	private String address;
	private int port;
	Socket clientSocket;
	String ligne;
	String word;
	String remaining;
	String results;
	BufferedReader rd;

	public LogicClient(String address, int port){
		this.address = address;
		this.port = port;
	}

	public void setObserver(GamePanel gamePanel){
		super.addObserver(gamePanel);
	}
	public void run() {

		if (this.socketCreation().equals("OK")){
			
			
			this.newGame();
			this.receivedWord();
		}
		
		else {
			JOptionPane.showMessageDialog(null,"Server could not be reached");
		}
	}

	public String socketCreation(){

			try {
				clientSocket = new Socket(address,port);
				//clientSocket.setSoTimeout(5000);
			} catch (SocketTimeoutException e) {
				System.out.println("TIme out !!");
				e.printStackTrace();
				return "ERROR";
			} catch (UnknownHostException e) {
				System.out.println("UnknownHost");
				e.printStackTrace();
			} catch (IOException e) {
				
				System.out.println("IO");
				e.printStackTrace();
			}
			return "OK";
			
	}

	public void newGame(){
		try {
			PrintWriter wr = new PrintWriter(clientSocket.getOutputStream());
			wr.println("newgame");
			wr.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendWord(String inputLetter){
		try {
			
			PrintWriter wr = new PrintWriter(clientSocket.getOutputStream());
			wr.println(inputLetter);
			wr.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeSocket(){
		try {
			clientSocket.close();
			System.out.println("Socket ferm�e c�t� client");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String EntreeClavier(){

		BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
		try {
			ligne = entree.readLine();
			System.out.println(ligne);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ligne;
	}

	public void receivedWord(){
		BufferedReader rd = null;

		try {
			rd = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str;
		try {
			while ((str = rd.readLine()) != null){
				super.setChanged();
				super.notifyObservers(str);
			}
		} catch (IOException e) {
			//e.printStackTrace();
		}

	}
}
