package hangman;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Handler extends Thread{

	private Socket socket;
	private String word;
	private int results;
	private int remainingAttempts;

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
			System.out.println(str);

				if (str.equals("newgame")){

					// Random selection of a word in the provided dictionary
					try {
						int nbWords = countWords("words");
						this.word = selectWord("words", nbWords);
						System.out.println(word);
					} catch (IOException e) {
						e.printStackTrace();
					}
					

				}

				else if (str.length() == 1){

				}

				else if (str.equals(word)){

				}

				else if (remainingAttempts > 0){

				}

				else{

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
		return word;
	}

}
