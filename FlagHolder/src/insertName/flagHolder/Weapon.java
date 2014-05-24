package insertName.flagHolder;

import insertName.flagHolder.entities.*;
import simpleEngine.core.*;

import com.esotericsoftware.minlog.*;

public class Weapon {

	//Variables
	private double damage, fireRate;
	private String name;
	private int clipAmmo, resAmmo, clipSize, bulletVelocity;
	private long timeNextShotAllowed = 0;

	public Weapon(){

	}

	public Weapon(double damage, double fireRate, String name, int clipAmmo, int resAmmo, int clipSize, int bulletVelocity) {
		this();
		this.damage = damage;
		this.fireRate = fireRate;
		this.name = name;
		this.clipAmmo = clipAmmo;
		this.resAmmo = resAmmo;
		this.clipSize = clipSize;
		this.bulletVelocity = bulletVelocity;
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

	public void fire(double x, double y, int team) {
		Log.info("nsa: " + timeNextShotAllowed);
		Log.info("curr time: " + System.currentTimeMillis());

		long timeUntilAllowedToShot = System.currentTimeMillis() - timeNextShotAllowed;

		Log.info("tuals: " + timeUntilAllowedToShot);
		if(timeUntilAllowedToShot >= 0 && clipAmmo > 0) {
			clipAmmo--;
			Bullet b = new Bullet(x, y, team, 10, 10, this.bulletVelocity, this.bulletVelocity, this.damage);
			Engine.getLastCreatedEngine().add(b);
			if(clipAmmo <= 0) {
				timeNextShotAllowed = System.currentTimeMillis() + 1500;
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
		clipAmmo = clipSize;
		resAmmo -= clipSize;
		if(resAmmo < 0) {
			clipAmmo += resAmmo;
			resAmmo = 0;
		}
	}
}