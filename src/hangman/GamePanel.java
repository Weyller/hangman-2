package hangman;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GamePanel extends JPanel implements ActionListener{

	private JButton send = new JButton("Send");
	private MainPanel mainPanel;
	private JLabel attempts = new JLabel("Failed Attempts");
	private JLabel score = new JLabel("Score");
	private JTextField letter = new JTextField();
	private JLabel word = new JLabel("_____");

	public GamePanel(MainPanel mainPanel){

		BoxLayout blverticalglobal = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(blverticalglobal);

		JPanel above = new JPanel();
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

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == send){
			mainPanel.setConnection();
		}
	}

}
