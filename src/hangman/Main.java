package hangman;

public class Main {

	public static void main(String[] args) {
		String address = "127.0.0.1"; 
		int port = 10000;
		
		Server server = new Server();
		
		Client client = new Client(address, port);
	}

}
