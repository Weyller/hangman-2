package hangman;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionPanel extends JPanel implements ActionListener{
	
	//private Client client = new Client("127.0.0.1", 80);
	private JButton checkButton = new JButton("Connection");
	private MainPanel mainPanel;

	public ConnectionPanel(MainPanel mainPanel){
		JTextField address = new JTextField("IP address");
		JTextField port = new JTextField("Port");
		BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	    this.setLayout(bl);
		checkButton.addActionListener(this);
		this.mainPanel = mainPanel;

		this.add(address);
		this.add(port);
		this.add(checkButton);
		checkButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkButton){
			mainPanel.setGame();
		}
	}
		
}
