package hangman;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GamePanel extends JPanel implements ActionListener, Observer{

	private JButton send = new JButton("Send");
	private MainPanel mainPanel;
	private JLabel attempts = new JLabel("Remaining : ");
	private JLabel score = new JLabel("Score : ");
	private JTextField letter = new JTextField();
	private JLabel word = new JLabel("");
	private LogicClient logicClient;
	JPanel above = new JPanel();

	public GamePanel(MainPanel mainPanel,LogicClient logicClient){

		BoxLayout blverticalglobal = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(blverticalglobal);


		this.add(above);

		JPanel below = new JPanel();
		this.add(below);
		BoxLayout blhorizontal = new BoxLayout(above, BoxLayout.X_AXIS);
		above.setLayout(blhorizontal);

		BoxLayout blvertical = new BoxLayout(below, BoxLayout.Y_AXIS);
		below.setLayout(blvertical);

		send.addActionListener(this);

		above.add(score);
		above.add(Box.createRigidArea(new Dimension(15,0)));
		above.add(attempts);
		below.add(word);
		below.add(letter);
		below.add(send);
		send.setAlignmentX(Component.CENTER_ALIGNMENT);
		attempts.setAlignmentX(Component.CENTER_ALIGNMENT);
		score.setAlignmentX(Component.CENTER_ALIGNMENT);
		letter.setAlignmentX(Component.CENTER_ALIGNMENT);
		word.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.mainPanel = mainPanel;
		this.logicClient = logicClient;
		logicClient.setObserver(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send){
			String inputLetter = letter.getText();
			logicClient.sendWord(inputLetter);
			letter.setText("");
		}
	}

	public void update(Observable arg0, Object arg1) {
		String action = ((String) arg1).split(":")[0];
		String argument = ((String) arg1).split(":")[1];
		if (action.equals("word")){
			updateWord(argument);
		}
		if (action.equals("remaining")){
			updateRemaining(argument);
		}
		if (action.equals("results")){
			updateResults(argument);
		}
		if (action.equals("win")){
			popUpWin();
		}
		if (action.equals("lose")){
			popUpLose();
		}
	}

	private void popUpLose() {
		JOptionPane.showMessageDialog(null,"Looser !");
		logicClient.newGame();
	}

	private void popUpWin() {
		JOptionPane.showMessageDialog(null,"Congratulations, you won !");
		logicClient.newGame();
	}

	private void updateResults(String argument) {
		score.setText("Score : " + argument);
		this.repaint();
		this.revalidate();
	}

	private void updateRemaining(String argument) {
		attempts.setText("Remaining : " + argument);
		this.repaint();
		this.revalidate();
	}

	private void updateWord(String argument) {
		word.setText(argument);
		this.repaint();
		this.revalidate();
	}



}
