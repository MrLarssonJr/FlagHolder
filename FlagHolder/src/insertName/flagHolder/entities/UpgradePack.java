package insertName.flagHolder.entities;

import insertName.flagHolder.*;

import java.awt.*;

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
		super(x, y,10,10);
		this.type = type;
		this.xVelocityUpgrade = 10; //a constant
		this.yVelocityUpgrade = 10; //a constant
		this.w1 = new Weapon(8, 6, "Rapid Fire", 25, 25, 25, 10);
		this.w2 = new Weapon(80, 0.5, "I hurt you very bad", 3, 3, 3, 400);
		this.yStandard = y;
		this.f = 50;
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
		Image img = textures.getPreLoadedTexture("upgrade.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}

	@Override
	public void update(long deltaT) {

		//Bobbing logic
		if(upDown){
            double dy = yStandard - f;
            this.setY(this.getY() + (this.getY()-dy)/25);
            if(this.getY()-5 < dy){
                upDown = false;
            }
        }
        else if(!upDown){
            double dy = yStandard + f;
            this.setY(this.getY() + (dy-this.getY())/25);
            if(this.getY()+10 > dy){
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
