package insertName.flagHolder.entities;

import insertName.flagHolder.*;

import java.awt.image.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;

public class UpgradePack extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = 541071365970979097L;
	//Variables
	private int type;  //The type of upgrade (1 = rapid fire weapon, 2 = speed, 3 = instant weapon..)
	private Weapon w1, w2;
	private double xVelocityUpgrade, yVelocityUpgrade, speedUpgradeTime;

	//Bobbing image variables
	private double yStandard, f; //yStandard is the initiated coordinate, f is the bobbing distance
	private boolean upDown; //upDown determines if the bobbing is + or -

	public UpgradePack(double x, double y, int type){
		super(x, y,40,40);
		this.type = type;
		this.xVelocityUpgrade = 10; //a constant
		this.yVelocityUpgrade = 10; //a constant
		this.w1 = new Weapon(8, 6, "Rapid Fire", 25, 25, 25, 2);
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
	public void draw(GameGraphics g, TextureStore textures) {
		BufferedImage img = textures.getPreLoadedTexture("upgrade.png");
		g.drawGameObject(this, img);
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
