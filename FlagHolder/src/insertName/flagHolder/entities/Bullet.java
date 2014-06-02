package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;
import simpleEngine.standardObjects.tileMap.*;

public class Bullet extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = 8632047927081765186L;
	//Variables
	private int team;
	private double speed, damage;

	public Bullet(double x, double y, int team, double width, double height, double speed, double damage, double playerRotation) {
		super(x, y, width, height);
		this.team = team;
		this.speed = speed;
		this.damage = damage;
		this.setRotation(playerRotation);
	}
	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("bullet.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}
	@Override
	public void update(long deltaT) {
		double dx = this.speed * Math.cos(this.getRotation());
		double dy = this.speed * Math.sin(this.getRotation());
		this.move(dx * deltaT, dy * deltaT);

		Map map = Engine.getLastCreatedEngine().getMap();
		if(map instanceof TileMap) {
			TileMap tMap = (TileMap) map;
			if(tMap.isPosBlocked((int)this.getX(), (int)this.getY())) {
				Engine.getLastCreatedEngine().remove(this);
			}

		}
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
	public double getSpeed() {
		return this.speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

}