package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.standardObjects.*;

public class Obstacle extends Scenery {
	
	
	
	public Obstacle() {
		super();
	}
	
	public Obstacle(double x, double y, double width, double heigth) {
		super(x, y, width, heigth);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(140, 102, 89));
		g.fillRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}
	
}
