package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class LogicClient extends Observable implements Runnable{

	private String address;
	private int port;
	Socket clientSocket;
	String ligne;
	String word;
	String remaining;
	String results;
	BufferedReader rd;
	boolean isConnected = false;

	public LogicClient(String address, int port){
		this.address = address;
		this.port = port;
	}

	public void setObserver(Observer o){
		super.addObserver(o);
	}
	public void run() {

		this.isConnected = socketCreation();
		super.setChanged();
		super.notifyObservers("connected:"+isConnected);

		if (isConnected){
			this.newGame();
			System.out.println("Appel de la méthode receivedWord");
			this.receivedWord();
		}

	}

	public boolean isConnected(){
		return isConnected;
	}

	public boolean socketCreation(){

		try {
			clientSocket = new Socket();
			clientSocket.connect(new InetSocketAddress(address, port), 1000);
			//clientSocket.setSoTimeout(2000);
		} catch (SocketTimeoutException e) {
			return false;
		} catch (UnknownHostException e) {
			System.out.println("UnknownHost");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("IO");
			e.printStackTrace();
			return false;
		}
		return true;

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

	public void messageReceive(){

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
			System.out.println("Socket fermée côté client");
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
