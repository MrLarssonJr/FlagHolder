package insertName.flagHolder;

import java.awt.Color;
import java.awt.Font;

import insertName.flagHolder.entities.Bullet;
import simpleEngine.core.Engine;
import simpleEngine.core.GameObject;
import simpleEngine.core.Updateable;
import simpleEngine.graphics.Drawable;
import simpleEngine.graphics.GameGraphics;
import simpleEngine.graphics.TextureStore;

public class Weapon extends GameObject implements Drawable, Updateable{

	//Variables
	private double damage, fireRate, playerX, playerY;
	private String name;
	private int clipAmmo, resAmmo, clipSize, bulletVelocity;
	private long timeNextShotAllowed = 0;
	private boolean isReloading;

	public Weapon(double damage, double fireRate, String name, int resAmmo, int clipSize, int bulletVelocity) {
		super(0, 0, 0, 0);
		this.damage = damage;
		this.fireRate = fireRate;
		this.name = name;
		this.clipAmmo = clipSize;
		this.resAmmo = resAmmo;
		this.clipSize = clipSize;
		this.bulletVelocity = bulletVelocity;
		this.playerX = playerX;
		this.playerY = playerY;
		this.isReloading = false;
		Engine.getLastCreatedEngine().add(this);
	}

	//Get and set
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public double getFireRate() {
		return fireRate;
	}
	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClipAmmo() {
		return clipAmmo;
	}
	public void setClipAmmo(int clipAmmo) {
		this.clipAmmo = clipAmmo;
	}
	public int getResAmmo() {
		return resAmmo;
	}
	public void setResAmmo(int resAmmo) {
		this.resAmmo = resAmmo;
	}
	public int getClipSize() {
		return clipSize;
	}
	public void setClipSize(int clipSize) {
		this.clipSize = clipSize;
	}
	public void setPlayerX(double playerX){
		this.playerX = playerX;
	}
	public void setPlayerY(double playerY) {
		this.playerY = playerY;
	}

	@Override
	public void update(long deltaT) {
		long timeUntilAllowedToShot = System.currentTimeMillis() - timeNextShotAllowed;
		if(timeUntilAllowedToShot >= 0){
			isReloading = false;
		}
	}
	
	public void fire(double x, double y, int team, double playerRotation) {
		long timeUntilAllowedToShot = System.currentTimeMillis() - timeNextShotAllowed;

		if(timeUntilAllowedToShot >= 0 && clipAmmo > 0) {
			clipAmmo--;
			Bullet b = new Bullet(x - 10, y - 10, team, 20, 20, this.bulletVelocity, this.damage, playerRotation);
			Engine.getLastCreatedEngine().add(b);
			if(clipAmmo <= 0) {
				timeNextShotAllowed = System.currentTimeMillis() + 1500;
				isReloading = true;
				clipAmmo = clipSize;
				resAmmo -= clipSize;
				if(resAmmo < 0) {
					clipAmmo += resAmmo;
					resAmmo = 0;
				}
			}
			else {
				timeNextShotAllowed = (long) (System.currentTimeMillis() + (1000/fireRate));
			}
		}
	}

	public void reload() {
		timeNextShotAllowed = System.currentTimeMillis() + 1499;
		isReloading = true;
		clipAmmo = clipSize;
		resAmmo -= clipSize;
		if(resAmmo < 0) {
			clipAmmo += resAmmo;
			resAmmo = 0;
		}
	}

	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		g.setColor(Color.WHITE);
		//g.setFont(new Font(arg0, arg1, arg2));
		String ammoString = "" + this.getClipAmmo() + " / ";
		if(resAmmo > 200){
			ammoString += "\u221E";
		}
		else{
			ammoString += ""+this.resAmmo;
		}
		
		if(isReloading){
			g.drawString(ammoString + " RELOADING", (int)this.playerX+100, (int)this.playerY+100);
		}
		else if(!isReloading){
			g.drawString(ammoString, (int)this.playerX+100, (int)this.playerY+100);
		}
		
		
	}
}