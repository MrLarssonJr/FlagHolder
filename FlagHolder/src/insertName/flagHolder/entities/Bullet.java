package insertName.flagHolder.entities;

import com.sun.xml.internal.stream.Entity;

public abstract class Bullet {
	
	//Extends Entity, Jesper? /Filip
	
	//Variables
	private int x,y,team, width, height;
	private double xVelocity, yVelocity;
	public Bullet(int x, int y, int team, int width, int height, double xVelocity, double yVelocity) {
		super();
		this.x = x;
		this.y = y;
		this.team = team;
		this.width = width;
		this.height = height;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
}
