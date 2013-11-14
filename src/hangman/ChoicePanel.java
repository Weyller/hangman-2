package hangman;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChoicePanel extends JPanel implements ActionListener {
	
	// ChoicePanel allows a user to launch a server, a client, or both (in two separate threads)
	
	private JButton clientButton = new JButton("Client");
	private JButton serverButton = new JButton("Server");
	private MainPanel mainPanel;
	BoxLayout layoutChoicePanel = new BoxLayout(this, BoxLayout.Y_AXIS);
	
	public ChoicePanel(MainPanel mainPanel){
		
		this.mainPanel = mainPanel;
		this.setLayout(layoutChoicePanel);
		clientButton.addActionListener(this);
		serverButton.addActionListener(this);

		this.add(clientButton);
		this.add(serverButton);
		clientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == clientButton){
			 mainPanel.setConnection();
		}
		
		if (e.getSource() == serverButton){
			try {
				ServerHangman serverHangman = new ServerHangman(10000);
				Thread serverThread = new Thread(serverHangman);
				serverThread.start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ServerWindow serverWindow = new ServerWindow(300,300);
		}
	}

}
