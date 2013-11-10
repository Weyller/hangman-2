package hangman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Handler extends Thread{

	private Socket socket;
	private String word;
	private int results = 0;
	private int remainingAttempts;
	private List<Character> foundLetters = new ArrayList<Character>();

	Handler(Socket socket) throws IOException { // thread constructor
		this.socket = socket;
	}

	public void run(){

		BufferedReader rd;
		try {
			PrintWriter wr = new PrintWriter(socket.getOutputStream());

			rd = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			String str;
			while ((str = rd.readLine()) != null){

				if (str.equals("newgame")){

					this.remainingAttempts = 10;

					// Random selection of a word in the provided dictionary
					try {
						int nbWords = countWords("words");
						this.word = selectWord("words", nbWords);
						wr.println("word:" + hiddenWord(foundLetters, word));
						wr.println("remaining:" + remainingAttempts);
						wr.println("results:" + results);
						wr.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}


				}

				else if (str.length() == 1){
					if(word.contains(str) && ! foundLetters.contains(str.charAt(0))){
						foundLetters.add(str.charAt(0));
						wr.println("word:" + hiddenWord(foundLetters, word));
						wr.println("remaining:" + remainingAttempts);
						wr.flush();
					}
					else if (remainingAttempts > 0){
						remainingAttempts--;
						wr.println("word:" + hiddenWord(foundLetters, word));
						wr.println("remaining:" + remainingAttempts);
						wr.flush();
					}

				}

				else if (str.equals(word)){
					results++;
					wr.println("win:");
					wr.println("results:" + results);
					wr.flush();
				}

				else if (remainingAttempts > 0){
						remainingAttempts--;
						wr.println("word:" + hiddenWord(foundLetters, word));
						wr.flush();
				}
				
				else{
					results--;
					wr.println("lose:");
					wr.println("results:" + results);
					wr.flush();
				}

			}



		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return word.toLowerCase();
	}

	private String hiddenWord(List<Character> foundLetters, String word){

		String hiddenWord = "";
		for(int i = 0; i < word.length(); i++){
			
			if(foundLetters.contains(word.charAt(i))){
				hiddenWord += word.charAt(i);
			}
			else{
				hiddenWord += "-";
			}
		}
		return hiddenWord;
	}

}
