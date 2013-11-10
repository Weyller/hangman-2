package hangman;

import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private ConnectionPanel connectionPanel;
	private GamePanel gamePanel;
	private ChoicePanel choicePanel;
	private LogicClient logicClient;
	
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
		this.removeAll();
		this.add(gamePanel);
		this.repaint();
		this.revalidate();
	}
	
}
