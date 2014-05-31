package insertName.flagHolder.entities;

import insertName.flagHolder.Weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import simpleEngine.collison.Collideable;
import simpleEngine.core.Engine;
import simpleEngine.graphics.TextureStore;
import simpleEngine.standardObjects.Entity;

public class UpgradePack extends Entity {
	
	//Variables
	private int type;  //The type of upgrade (1 = rapid fire weapon, 2 = speed, 3 = instant weapon..)
	private Weapon w1, w2;
	private double xVelocityUpgrade, yVelocityUpgrade, speedUpgradeTime;
	
	//Bobbing image variables
	private double yStandard, f; //yStandard is the initiated coordinate, f is the bobbing distance
	private boolean upDown; //upDown determines if the bobbing is + or -
	
	public UpgradePack(){
		
	}
	
	public UpgradePack(double x, double y, int type){
		super(x, y,40,40);
		this.type = type;
		this.xVelocityUpgrade = 10; //a constant
		this.yVelocityUpgrade = 10; //a constant
		this.w1 = new Weapon(8, 6, "Rapid Fire", 25, 25, 25, 10);
		this.w2 = new Weapon(80, 0.5, "I hurt you very bad", 3, 3, 3, 400);
		this.yStandard = y;
		this.f = 15;
		this.upDown = true;
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

	public Weapon getWeaponOne() {
		return this.w1;
	}
	
	public Weapon getWeaponTwo() {
		return this.w2;
	}

	public int getType() {
		return this.type;
	}

	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("upgrade.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}

	@Override
	public void update(long deltaT) {
		
		//Bobbing logic
		if(upDown){
            double destination = yStandard - f;
            this.move(0, -(this.getY()-destination)/25);
            if(this.getY()-8 < destination){
                upDown = false;
            }
        }
        else if(!upDown){
            double destination = yStandard + f;
            this.move(0, (destination-this.getY())/25);
            if(this.getY()+8 > destination){
                upDown = true;
            }
        }
	}

	@Override
	public void collidedWith(Collideable otherObj) {
		if(otherObj instanceof Player){
			Engine.getLastCreatedEngine().remove(this);
		}
		
	}
}
