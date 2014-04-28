package insertName.flagHolder.entities;

import java.awt.*;
import java.io.*;

import Engine.*;

public class Player extends Entity {
	
	@Override
	public void draw(Graphics2D g) {
		Image img = Toolkit.getDefaultToolkit().getImage("res" + File.separator + "img.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}
	
	@Override
	public void collidedWith(Entity arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
