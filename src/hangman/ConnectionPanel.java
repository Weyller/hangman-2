package hangman;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionPanel extends JPanel implements ActionListener{

	private JButton checkButton = new JButton("Connection");
	private MainPanel mainPanel;
	String inputAddress;
	int inputPort;
	JTextField address = new JTextField("IP address");
	JTextField port = new JTextField("Port");
	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	
	public ConnectionPanel(MainPanel mainPanel){
		
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
			 inputAddress = address.getText();
			 inputPort = Integer.parseInt(port.getText());
			 LogicClient logicClient = new LogicClient(inputAddress,inputPort);
			 Thread logicThread = new Thread(logicClient);
			 logicThread.start();
			 if (logicClient.socketCreation().equals("OK")){
				 logicClient.newGame();
				 GamePanel gamePanel = new GamePanel(mainPanel,logicClient);
				 mainPanel.setGame();
			 }
			 logicClient.closeSocket();
		}
	}

}
