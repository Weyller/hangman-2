package hangman;

import java.awt.Color;
import java.awt.Component;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerPanel extends JPanel {
	
	String IPaddress;
	
	BoxLayout layoutServerPanel = new BoxLayout(this, BoxLayout.Y_AXIS);
	
	
	public ServerPanel(int width, int height){
		try {
			IPaddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel address = new JLabel("IP Address : " + IPaddress);
		JLabel port = new JLabel("Port : 10000");
		this.setSize(width, height);
		this.setBackground(Color.white);
		this.setLayout(layoutServerPanel);
		this.add(address);
		this.add(port);
		address.setAlignmentX(Component.CENTER_ALIGNMENT);
		port.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
}