package hangman;

import javax.swing.JFrame;

public class ServerWindow extends JFrame{

	public ServerWindow(int width, int height){
		this.setSize(width, height);
		this.setTitle("Server Hangman");
		//this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ServerPanel serverPanel = new ServerPanel(this.getWidth(), this.getHeight());
		this.setContentPane(serverPanel);
		this.setVisible(true);
	}

	
}