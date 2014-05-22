package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;

public class Bullet extends Entity {

	//Variables
	private int team;
	private double xVelocity, yVelocity, damage;
	public Bullet(double x, double y, int team, double width, double height, double xVelocity, double yVelocity) {
		super(x, y, width, height);
		this.team = team;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.damage = damage;
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
		if(otherObj instanceof Player){
			Player p = (Player) otherObj;
			if(p.getTeam() != this.team){
				Engine.getLastCreatedEngine().remove(this);
			}
		}

	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public double getxVelocity() {
		return xVelocity;
	}
	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}
	public double getyVelocity() {
		return yVelocity;
	}
	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

}
