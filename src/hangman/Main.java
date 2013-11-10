package hangman;

import java.io.IOException;

public class Main {

<<<<<<< HEAD
	public static void main(String[] args) {

		Client client = new Client("127.0.0.1",80);
=======
	public static void main(String[] args) throws IOException {
		
		Client client = new Client("localhost",10000);
		
		//ServerHangman serverHangman = new ServerHangman(10000);
>>>>>>> Server
	}

}
