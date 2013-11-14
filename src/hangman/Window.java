package hangman;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame{
	
	MainPanel mainPanel;

	public Window(int width, int height){
		this.setSize(width, height);
		this.setTitle("Hangman");
		this.setLocationRelativeTo(null);
		
		
        this.addWindowListener(exitListener);
        // The Windows listener allows to properly close the socket when quitting the game panel
		
		this.mainPanel = new MainPanel(this.getWidth(), this.getHeight());
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	
	WindowListener exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
          mainPanel.getGamePanel().getLogicClient().closeSocket();
        }
    };

}
