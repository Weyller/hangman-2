package hangman;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


	public class GamePanel extends JPanel implements ActionListener{
		
		//private Client client = new Client("127.0.0.1", 80);
		private JButton newGameButton = new JButton("New game");
		private MainPanel mainPanel;

		public GamePanel(MainPanel mainPanel){
	
			BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		    this.setLayout(bl);
			newGameButton.addActionListener(this);

			this.add(newGameButton);
			newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.mainPanel = mainPanel;

		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == newGameButton){
				mainPanel.setConnection();
			}
		}

}
