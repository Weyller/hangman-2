package hangman;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
	// The main panel contains the choice panel, the connecion panel and the game panel

	private ConnectionPanel connectionPanel;
	private ChoicePanel choicePanel;
	private GamePanel gamePanel;
	
	public MainPanel(int width, int height){
		this.setSize(width, height);
		this.setBackground(Color.white);
		setChoice();
	}
	
	public void setChoice(){
		choicePanel = new ChoicePanel(this);
		this.removeAll();
		this.add(choicePanel);
		this.repaint();
		this.revalidate();
	}

	public void setConnection(){
		connectionPanel = new ConnectionPanel(this);
		this.removeAll();
		this.add(connectionPanel);
		this.repaint();
		this.revalidate();
	}

	public void setGame(GamePanel gamePanel){
		this.setGamePanel(gamePanel); 
		this.removeAll();
		this.add(gamePanel);
		this.repaint();
		this.revalidate();
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
}
