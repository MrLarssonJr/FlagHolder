package insertName.flagHolder;

import simpleEngine.core.Engine;
import insertName.flagHolder.entities.Bullet;

public class Weapon {

	//Variables
	private double damage, fireRate;
	private String name;
	private int clipAmmo, resAmmo, clipSize;
	public Weapon(double damage, double fireRate, String name, int clipAmmo, int resAmmo, int clipSize) {
		this.damage = damage;
		this.fireRate = fireRate;
		this.name = name;
		this.clipAmmo = clipAmmo;
		this.resAmmo = resAmmo;
		this.clipSize = clipSize;
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
		Bullet b = new Bullet(x, y, team, 10, 10, 1.0, 1.0, this.damage);
		Engine.getLastCreatedEngine().add(b);
	}
}