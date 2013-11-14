package hangman;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionPanel extends JPanel implements ActionListener, Observer{
	
	// ConnectionPanel invites the user to connect to a game server via an IP and a port
	// If no information is provided, the default IP is set to "localhost" and the default port to 10000

	private JButton checkButton = new JButton("Connection");
	private MainPanel mainPanel;
	String inputAddress;
	String inputPort;
	JTextField address = new JTextField("IP address");
	JTextField port = new JTextField("Port");
	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	private LogicClient logicClient;
	
	public ConnectionPanel(MainPanel mainPanel){
		
		this.setLayout(bl);
		checkButton.addActionListener(this);
		this.mainPanel = mainPanel;
		this.add(address);
		this.add(port);
		this.add(checkButton);
		checkButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	public void choiceLogic(){
			inputAddress = address.getText();
			inputPort = port.getText() ;

			
			if(inputAddress.equals("IP address")){
				inputAddress = "localhost";
			}
			if(inputPort.equals("Port")){
				inputPort = "10000";
			}
			
			logicClient = new LogicClient(inputAddress,Integer.parseInt(inputPort));
			logicClient.setObserver(this);
			
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == checkButton){
			 choiceLogic();
			 Thread logicThread = new Thread(logicClient);
			 logicThread.start();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		String action = ((String) arg1).split(":")[0];
		String argument = ((String) arg1).split(":")[1];
		if (action.equals("connected")){
			updatePanel(argument);
		}
	}

	private void updatePanel(String argument) {
		// TODO Auto-generated method stub
		if (argument.equals("true")){
			 GamePanel gamePanel = new GamePanel(mainPanel,logicClient);
			 mainPanel.setGame(gamePanel);
		 }
		 else {
			 JOptionPane.showMessageDialog(null,"Server couldn't be reached");
		 }
	}

}
