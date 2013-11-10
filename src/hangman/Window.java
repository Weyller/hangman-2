package hangman;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements Runnable{

	public Window(int width, int height){
		this.setSize(width, height);
		this.setTitle("Hangman");
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run() {
		MainPanel mainPanel = new MainPanel(this.getWidth(), this.getHeight());
		this.setContentPane(mainPanel);
	}
}
