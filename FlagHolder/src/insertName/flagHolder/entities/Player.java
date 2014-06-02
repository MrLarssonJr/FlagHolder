package insertName.flagHolder.entities;

import insertName.flagHolder.*;
import insertName.flagHolder.input.*;

import java.awt.event.*;
import java.awt.image.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;
import simpleEngine.standardObjects.tileMap.*;

public class Player extends Entity {
	/**
	 *
	 */
	private static final long serialVersionUID = -5761530654521685452L;
	//Variables
	private double speed, hp;
	private int id, team;
	private Weapon w;
	private boolean hasFlag;
	private double speedForHowLong; //this variable determines for how much time a speed upgrade has left
	private KeyMap input;

	public Player(double x, double y, double width, double heigth, int id, int team, KeyMap map, int speed) {
		super(x, y, width, heigth);
		this.id = id;
		this.hp = 100;
		this.hp = 100;
		this.team = team;
		this.hasFlag = false;
		this.w = this.getDefaultWeapon();
		this.speed = speed;
		input = map;
		this.setRotation(Math.PI+2*Math.PI/3);
	}

	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		BufferedImage img = textures.getPreLoadedTexture("player.png");
		g.drawGameObject(this, img);
//		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}

	@Override
	public void update(long deltaT) {
		this.setRotation(this.getRotation() + 0.02);
		Map map = Engine.getLastCreatedEngine().getMap();

		double speedX = 0;
		double speedY = 0;

		int vert = 0;
		int hor = 0;

		if(input.isKeyPressed(KeyEvent.VK_W) && !input.isKeyPressed(KeyEvent.VK_S)){
			vert = -1;
		}
		else if(!input.isKeyPressed(KeyEvent.VK_W) && input.isKeyPressed(KeyEvent.VK_S)){
			vert = 1;
		}

		if(!input.isKeyPressed(KeyEvent.VK_D) && input.isKeyPressed(KeyEvent.VK_A)){
			hor = -1;
		}
		else if(input.isKeyPressed(KeyEvent.VK_D) && !input.isKeyPressed(KeyEvent.VK_A)){
			hor = 1;
		}

		if(vert + hor == -2){
			speedX = -(Math.sqrt(Math.pow(this.speed, 2)/2));
			speedY = -(Math.sqrt(Math.pow(this.speed, 2)/2));
		}
		else if(vert + hor == -1){
			if(vert == -1){
				speedY = -speed;
			}
			else{
				speedX = -speed;
			}
		}
		else if(vert + hor == 0){
			if(vert == 1){
				speedX = -(Math.sqrt(Math.pow(this.speed, 2)/2));
				speedY = (Math.sqrt(Math.pow(this.speed, 2)/2));
			}
			else if(hor == 1){
				speedX = (Math.sqrt(Math.pow(this.speed, 2)/2));
				speedY = -(Math.sqrt(Math.pow(this.speed, 2)/2));
			}
		}
		else if(vert + hor == 1){
			if(vert == 1){
				speedY = speed;
			}
			else{
				speedX = speed;
			}
		}
		else if(vert + hor == 2){
			speedX = (Math.sqrt(Math.pow(this.speed, 2)/2));
			speedY = (Math.sqrt(Math.pow(this.speed, 2)/2));
		}

		if(input.isKeyPressed(KeyEvent.VK_SPACE)) {
			this.fire();
		}
		double dx = speedX * deltaT/1000.0;
		double dy = speedY * deltaT/1000.0;

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

	public Weapon getDefaultWeapon(){
		return new Weapon(10, 2, "Default Rifle", 10, Integer.MAX_VALUE, 10, 2);
	}

	public void respawn(){
		this.hp = 100;
		this.setX(100);
		this.setY(100);
		this.w = this.getDefaultWeapon();
		this.hasFlag = false;
		Flag f = new Flag(this.getX(), this.getY());
		Engine.getLastCreatedEngine().add(f);
	}

	public void fire(){
		this.w.fire(this.getX(), this.getY(), this.team, this.getRotation());
		if(this.w.getClipAmmo() == 0 && this.w.getResAmmo() == 0){
			this.w = getDefaultWeapon();
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speedXO) {
		this.speed = speedXO;
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
				this.w = up.getWeaponOne();
			}
			else if(up.getType() == 2){
				//set xVelocity and yVelocity to up.getXVelocityUpgrade() and up.getXVelocityUpgrade()
				//set speedForHowLong = up.getSpeedUpgradeTime();
			}
			else if(up.getType() == 3){
				this.w = up.getWeaponTwo();
			}
		}
	}

	@Override
	public void move(double dx, double dy) {
		super.move(dx, dy);
	}

}