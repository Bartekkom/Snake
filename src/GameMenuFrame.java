import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.event.*;

public class GameMenuFrame extends JFrame implements ActionListener{
	
	static final int DELAY = 50;
	GameMenuPanel menu = new GameMenuPanel();
	Timer timer;
	
	GameMenuFrame(){
		
		this.add(menu);
		this.setTitle("Snake Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		timer = new Timer(DELAY, this);
		timer.start();
		//frameClose();
	}
	
	public void frameClose(){
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!menu.menuRun) {
			frameClose();
		}
	}

}
