package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.collison.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;

public class Bullet extends Entity{

	//Variables
	private int team;
	private double xVelocity, yVelocity;
	public Bullet(int x, int y, int team, int width, int height, double xVelocity, double yVelocity) {
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
