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
//			if ((inputAddress.equals("IP address")) && (inputPort.equals("Port"))){
//				LogicClient logicClient = new LogicClient("127.0.0.1",10000);
//				return logicClient;
//			}
//			else if ((!inputAddress.equals("")) && (!inputPort.equals(""))){
//				LogicClient logicClient = new LogicClient(inputAddress,Integer.parseInt(inputPort));
//				return logicClient;
//			} else if ((!inputAddress.equals("")) && inputPort.equals("")) {
//				int port = 10000;
//				LogicClient logicClient = new LogicClient(inputAddress,port);
//				return logicClient;
//			} else if (inputAddress.equals("") && (!inputPort.equals(""))) {
//				String address = "127.0.0.1";
//				LogicClient logicClient = new LogicClient(address,Integer.parseInt(inputPort));
//				return logicClient;
//			} else if (inputAddress.equals("") && inputPort.equals("")) {
//				String address = "127.0.0.1";
//				int port = 10000;
//				LogicClient logicClient = new LogicClient(address,port);
//				return logicClient;
//			} else
//				return null;
			
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
