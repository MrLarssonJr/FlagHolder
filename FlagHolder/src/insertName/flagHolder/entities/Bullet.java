package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.collison.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;

public class Bullet extends Entity{



	public Bullet() {
		super();
	}
	public Bullet(double x, double y, double width, double heigth) {
		super(x, y, width, heigth);
	}

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
	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		// TODO Auto-generated method stub

	}
	@Override
	public void update(long deltaT) {
		// TODO Auto-generated method stub

	}
	@Override
	public void collidedWith(Collideable otherObj) {
		// TODO Auto-generated method stub

	}

}
