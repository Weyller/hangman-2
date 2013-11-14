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
