package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.collison.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;

public class Bullet extends Entity {

	//Variables
	private int team;
	private double xVelocity, yVelocity;
	public Bullet(double x, double y, int team, double width, double height, double xVelocity, double yVelocity) {
		super(x, y, width, height);
		this.team = team;
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
