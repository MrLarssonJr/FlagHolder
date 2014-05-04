package insertName.flagHolder.entities;

import java.awt.*;
import java.io.*;

import simpleEngine.collison.*;
import simpleEngine.standardObjects.*;

public class Player extends Entity {
	
	
	
	public Player() {
		super();
	}
	
	public Player(double x, double y, double width, double heigth) {
		super(x, y, width, heigth);
	}
	
	@Override
	public void draw(Graphics2D g) {
		Image img = Toolkit.getDefaultToolkit().getImage("res" + File.separator + "img.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}
	
	@Override
	public void update(long deltaT) {
		int speedX = 4, speedY = 4;
		this.move(2, 2);
		//		int speed = (int) (0.48 * deltaT);
		//		if(KeyboardListener.isKeyPressed(KeyEvent.VK_UP) && !KeyboardListener.isKeyPressed(KeyEvent.VK_DOWN)) {
		//			this.move(0, -speed);
		//		}
		//		else if(!KeyboardListener.isKeyPressed(KeyEvent.VK_UP) && KeyboardListener.isKeyPressed(KeyEvent.VK_DOWN)) {
		//			this.move(0, speed);
		//		}
		//		if(KeyboardListener.isKeyPressed(KeyEvent.VK_LEFT) && !KeyboardListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
		//			this.move(-speed, 0);
		//		}
		//		else if(!KeyboardListener.isKeyPressed(KeyEvent.VK_LEFT) && KeyboardListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
		//			this.move(speed, 0);
		//		}
	}
	
	@Override
	public void collidedWith(Collideable arg0) {
		
	}
	
}
