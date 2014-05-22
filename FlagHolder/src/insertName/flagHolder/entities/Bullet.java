package insertName.flagHolder.entities;

import java.awt.Graphics2D;

import simpleEngine.collison.Collideable;
import simpleEngine.core.Engine;
import simpleEngine.graphics.TextureStore;
import simpleEngine.standardObjects.Entity;

public class Bullet extends Entity{
	
	
	
	//Variables
	private int x,y,team, width, height;
	private double xVelocity, yVelocity, damage;
	public Bullet(int x, int y, int team, int width, int height, double xVelocity, double yVelocity, double damage) {
		super();
		this.x = x;
		this.y = y;
		this.team = team;
		this.width = width;
		this.height = height;
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
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
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
