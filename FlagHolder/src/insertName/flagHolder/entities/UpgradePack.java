package insertName.flagHolder.entities;

import insertName.flagHolder.Weapon;

import java.awt.Graphics2D;

import simpleEngine.collison.Collideable;
import simpleEngine.core.Engine;
import simpleEngine.graphics.TextureStore;
import simpleEngine.standardObjects.Entity;

public class UpgradePack extends Entity {
	
	//Variables
	private int type;  //The type of upgrade (1 = weapon, 2 = speed..)
	private Weapon w;
	private double xVelocityUpgrade, yVelocityUpgrade, speedUpgradeTime;
	
	public UpgradePack(){
		
	}
	
	public UpgradePack(double x, double y, int type){
		super(x, y,10,10);
		this.type = type;
		this.xVelocityUpgrade = 10; //a constant
		this.yVelocityUpgrade = 10; //a constant
	}

	public double getSpeedUpgradeTime() {
		return this.speedUpgradeTime;
	}

	public double getXVelocityUpgrade() {
		return this.xVelocityUpgrade;
	}

	public double getYVelocityUpgrade() {
		return this.yVelocityUpgrade;
	}

	public Weapon getWeapon() {
		return this.w;
	}

	public int getType() {
		return this.type;
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
			Engine.getLastCreatedEngine().remove(this);
		}
		
	}
}
