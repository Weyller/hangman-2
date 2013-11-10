package hangman;


public class Client{
	
	public Client(String address, int port) {
		
		//Window w1 = new Window(300,300);
		LogicClient l1 = new LogicClient("127.0.0.1",10000);
		//Thread t1 = new Thread(w1);
		Thread t2 = new Thread(l1);
		//t1.start();
		t2.start();
	}

}
