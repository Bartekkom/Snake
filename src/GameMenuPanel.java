import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

public class GameMenuPanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 400;
	static final int SCREEN_HEIGHT = 400;
	
	boolean menuRun = false;
	
	JButton startButton = new JButton("START");
	//JButton settingsButton = new JButton("SETTINGS");
	//JButton scoresButton = new JButton("HIGHSCORES");
	JButton exitButton = new JButton("EXIT");
	JButton [] buttonList = {startButton, exitButton};
	
	
	
	GameMenuPanel(){
		menuRun = true;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.setLayout(null);
		buttonSetup();
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.setFont(new Font("Verdana", Font.BOLD, 35));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Snake Game", (SCREEN_WIDTH - metrics.stringWidth("Snake Game"))/2, g.getFont().getSize());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void buttonSetup(){
		for(int i = 0; i < buttonList.length; i++) {
			buttonList[i].setBackground(Color.BLACK);
			buttonList[i].setForeground(Color.GRAY);
			buttonList[i].setBounds(125,75+i*75,150,50);
			buttonList[i].addActionListener(this);
			buttonList[i].setFocusable(false);
			this.add(buttonList[i]);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==startButton) {
			new GameFrame();
			menuRun = false;
		}
		
		if(e.getSource()==exitButton) {
			menuRun = false;
		}
		
	}
	
}
