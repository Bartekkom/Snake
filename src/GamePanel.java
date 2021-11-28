import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.event.MenuKeyEvent;
public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 50; 
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 5;
	int score;
	int appleX;
	int appleY;
	int gAppleX;
	int gAppleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	
	
	public void startGame() {
		newApple();
		goldApple();
		running = true; 
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
			if(running) {
			//Grid
			for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			//gApple
			if(score != 0 && score%5 == 0) {
				g.setColor(Color.yellow);
				g.fillOval(gAppleX, gAppleY, UNIT_SIZE, UNIT_SIZE);
			}
			
			for(int i = 0; i < bodyParts; i++) {
				if(i==0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} 
				else {
					g.setColor(new Color(45,180,0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.red);
			g.setFont(new Font("INK Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score:" +score, (SCREEN_WIDTH - metrics.stringWidth("Score:" +score))/2, g.getFont().getSize());

		} 
		else {
			gameOver(g);
		}
	}
	
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	public void goldApple() {
		{
			gAppleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
			gAppleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;	
		} 
		//while (appleX == gAppleY && appleY == gAppleY);
	}
	
	
	public void move() {
		for( int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkApple() {
		if(x[0] == appleX && y[0] == appleY) {
			bodyParts++;
			score++;
			newApple();
		}
		
		if(x[0] == gAppleX && y[0] == gAppleY) {
			bodyParts ++;
			score += 5;
			goldApple();
		}
	}
	
	public void checkCollisions() {
		//heads collide with body
		for(int i = bodyParts; i > 0; i--) {
			if(x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		//heads hit left border
		if(x[0] < 0) {
			running = false;
		}
		//heads hit right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//heads hit top border
		if(y[0] < 0) {
			running = false;
		}
		//heads hit left border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		//Game Over text
		g.setColor(Color.red);
		g.setFont(new Font("INK Free", Font.BOLD, 75));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		g.setColor(Color.red);
		g.setFont(new Font("INK Free", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score:" +score, (SCREEN_WIDTH - metrics2.stringWidth("Score:" +score))/2, g.getFont().getSize());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;	
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;	
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_ENTER:
                if(!running){
                  new GameFrame();	
                }
			}
		}
	}
}
