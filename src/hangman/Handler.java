package hangman;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Handler extends Thread{

	private Socket socket;

	Handler(Socket socket) throws IOException { // thread constructor
		this.socket = socket;
	}
	
	public void run(){
		
		try {
			int nbWords = countWords("words");
			String word = selectWord("words", nbWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private int countWords(String fileName) throws IOException{
		// TODO Auto-generated method stub
		 Scanner scanner = new Scanner(new FileInputStream(fileName));
		 int c = 0;
		    try {
		      while (scanner.hasNextLine()){
		    	scanner.nextLine();
		    	c++;
		      }
		    }
		    finally{
		      scanner.close();
		    }
			return c;
		
	}

	private String selectWord(String fileName, int nbWords) throws IOException {
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    int c = 0;
		    int r = (int) Math.round(Math.random() * nbWords);
		    String word = "";
		    try {
		      while (scanner.hasNextLine()){
		    	c++;
		    	String nextLine = scanner.nextLine();
		    	if (c == r){
		    		word = nextLine;
		    	}
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return word;
	}

}
