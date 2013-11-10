package hangman;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private ConnectionPanel connectionPanel;
	private GamePanel gamePanel;
	
	public MainPanel(int width, int height){

		this.setSize(width, height);
		this.setBackground(Color.white);
		setConnection();
	}

	public void setConnection(){
		connectionPanel = new ConnectionPanel(this);
		this.removeAll();
		this.add(connectionPanel);
		this.repaint();
		this.revalidate();
	}

	public void setGame(){
		gamePanel = new GamePanel(this);
		this.removeAll();
		this.add(gamePanel);
		this.repaint();
		this.revalidate();
	}
	
}
