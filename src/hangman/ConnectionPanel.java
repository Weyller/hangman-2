package hangman;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionPanel extends JPanel implements ActionListener{
	
	// ConnectionPanel invites the user to connect to a game server via an IP and a port
	// If no information is provided, the default IP is set to "localhost" and the default port to 10000

	private JButton checkButton = new JButton("Connection");
	private MainPanel mainPanel;
	String inputAddress;
	String inputPort;
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
	
	public LogicClient choiceLogic(){
			inputAddress = address.getText();
			inputPort = port.getText() ;

			
			if(inputAddress.equals("IP address")){
				inputAddress = "localhost";
			}
			if(inputPort.equals("Port")){
				inputPort = "10000";
			}
			
			LogicClient logicClient = new LogicClient(inputAddress,Integer.parseInt(inputPort));
			return logicClient;
			
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkButton){
			
			 LogicClient logicClient = this.choiceLogic();
			 Thread logicThread = new Thread(logicClient);
			 logicThread.start();
			 GamePanel gamePanel = new GamePanel(mainPanel,logicClient);
			 mainPanel.setGame(gamePanel);
			 
			 
		}
	}

}
