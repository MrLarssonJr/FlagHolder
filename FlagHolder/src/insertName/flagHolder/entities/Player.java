package insertName.flagHolder.entities;

import insertName.flagHolder.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;
import simpleEngine.standardObjects.tileMap.*;

public class Player extends Entity {
	//Variables
	private double speedXO = 1, speedYO = 1, hp;
	private int id, team;
	private Weapon w;
	private boolean hasFlag;
	private double speedForHowLong; //this variable determines for how much time a speed upgrade has left

	
	public Player() {
		super();
		id = 0;
	}

	public Player(double x, double y, double width, double heigth, int id, int team) {
		super(x, y, width, heigth);
		this.id = id;
		this.hp = 100;
		this.hp = 100;
		this.team = team;
		this.hasFlag = false;
		this.w = new Weapon(5, 5, "Place holder", 5, 5, 5);
	}

	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("player.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}

	@Override
	public void update(long deltaT) {
		Map map = Engine.getLastCreatedEngine().getMap();
		KeyMap kMap = GameServer.getLatestKeyMap(id);

		double speedX = 0;
		double speedY = 0;

		if(kMap.isKeyPressed(KeyEvent.VK_UP) && !kMap.isKeyPressed(KeyEvent.VK_DOWN)) {
			speedY = -speedYO;
		}
		else if(!kMap.isKeyPressed(KeyEvent.VK_UP) && kMap.isKeyPressed(KeyEvent.VK_DOWN)) {
			speedY = speedYO;
		}

		if(kMap.isKeyPressed(KeyEvent.VK_RIGHT) && !kMap.isKeyPressed(KeyEvent.VK_LEFT)) {
			speedX = speedXO;
		}
		else if(!kMap.isKeyPressed(KeyEvent.VK_RIGHT) && kMap.isKeyPressed(KeyEvent.VK_LEFT)) {
			speedX = -speedXO;
		}
		if(kMap.isKeyPressed(KeyEvent.VK_SPACE)) {
			this.fire();
		}

		double dx = speedX * deltaT;
		double dy = speedY * deltaT;

		if(this.getY() <= 0 && speedY < 0) {
			dy = 0;
		}
		else if(((this.getY() + this.getHeight()) >= Engine.getLastCreatedEngine().getMap().getSize().height) && speedY > 0) {
			dy = 0;
		}

		if(this.getX() <= 0 && speedX < 0) {
			dx = 0;
		}
		else if((this.getX() + this.getWidth()) >= Engine.getLastCreatedEngine().getMap().getSize().width && speedX > 0) {
			dx = 0;
		}

		if(map instanceof TileMap) {
			TileMap tMap = (TileMap) map;

			double nx = this.getX() + dx;
			double ny = this.getY() + dy;

			if(tMap.isPosBlocked((int) nx, (int) this.getY())) {
				dx = 0;
				nx = this.getX();
				speedX = 0;
			}
			if(tMap.isPosBlocked((int) (nx + this.getWidth()), (int) this.getY())) {
				dx = 0;
				nx = this.getX();
				speedX = 0;
			}
			if(tMap.isPosBlocked((int) nx, (int) ny)) {
				dy = 0;
				speedY = 0;
			}
			if(tMap.isPosBlocked((int) nx, (int) (ny + this.getHeight()))) {
				dy = 0;
				speedY = 0;
			}
		}

		this.move(dx, dy);
		
		//When player dies
		if(this.hp < 0){
			respawn();
		}
		
	}
	
	public void respawn(){
		this.hp = 100;
		this.setX(100);
		this.setY(100);
		this.w = new Weapon(5, 5, "Place holder", 5, 5, 5);
		this.hasFlag = false;
		Flag f = new Flag(this.getX(), this.getY());
		Engine.getLastCreatedEngine().add(f);
	}
	
	public void fire(){
		this.w.fire(this.getX(), this.getY(), this.team);
	}

	public double getSpeedXO() {
		return speedXO;
	}

	public void setSpeedXO(double speedXO) {
		this.speedXO = speedXO;
	}

	public double getSpeedYO() {
		return speedYO;
	}

	public void setSpeedYO(double speedYO) {
		this.speedYO = speedYO;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public void setWeapon(Weapon w){
		this.w = w;
	}

	@Override
	public void collidedWith(Collideable otherObj) {
		if(otherObj instanceof Bullet){
			Bullet b = (Bullet) otherObj;
			if(b.getTeam() != this.team){
				this.hp -= b.getDamage();
			}
		}
		if(otherObj instanceof Flag){
			hasFlag = true;
		}
		if(otherObj instanceof UpgradePack){
			UpgradePack up = (UpgradePack) otherObj;
			if(up.getType() == 1){
				this.w = up.getWeapon();
			}
			else if(up.getType() == 2){
				//set xVelocity and yVelocity to up.getXVelocityUpgrade() and up.getXVelocityUpgrade()
				//set speedForHowLong = up.getSpeedUpgradeTime();
			}
		}
	}

	@Override
	public void move(double dx, double dy) {
		super.move(dx, dy);
	}

}