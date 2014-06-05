package insertName.flagHolder.entities;

import insertName.flagHolder.Weapon;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import simpleEngine.collison.Collideable;
import simpleEngine.core.Engine;
import simpleEngine.core.GameObject;
import simpleEngine.core.Map;
import simpleEngine.graphics.GameGraphics;
import simpleEngine.graphics.TextureStore;
import simpleEngine.input.KeyboardListener;
import simpleEngine.input.MouseListener;
import simpleEngine.standardObjects.Entity;
import simpleEngine.standardObjects.tileMap.TileMap;

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
	private KeyboardListener keyInput;
	private MouseListener mouseInput;
	private GameObject pointer;

	public GameObject getPointer() {
		return pointer;
	}

	public void setPointer(GameObject pointer) {
		this.pointer = pointer;
	}

	public Player(double x, double y, double width, double heigth, int id, int team, KeyboardListener keyInput, MouseListener mouseInput, int speed) {
		super(x, y, width, heigth);
		this.id = id;
		this.hp = 100;
		this.hp = 100;
		this.team = team;
		this.hasFlag = false;
		this.w = this.getDefaultWeapon();
		this.speed = speed;
		this.keyInput = keyInput;
		this.mouseInput = mouseInput;
		this.setRotation(Math.PI+2*Math.PI/3);
	}

	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		BufferedImage img = textures.getPreLoadedTexture("player.png");
		g.drawGameObject(this, img);
	}

	@Override
	public void update(long deltaT) {
		Map map = Engine.getLastCreatedEngine().getMap();
		{
			double pointerX = pointer.getX() + pointer.getWidth()/2;
			double pointerY = pointer.getY() + pointer.getHeight()/2;

			double deltaX = pointerX - (this.getX() + this.getWidth() / 2);
			double deltaY = pointerY - (this.getY() + this.getHeight() / 2);
			double v = Math.atan(deltaY/deltaX);
			
			if(Double.isNaN(v)) {
				v = 0;
			}

			if(deltaX < 0) {
				v += Math.PI;
			}

			this.setRotation(v);

		}

		double speedX = 0;
		double speedY = 0;

		int vert = 0;
		int hor = 0;

		if(keyInput.isKeyPressed(KeyEvent.VK_W) && !keyInput.isKeyPressed(KeyEvent.VK_S)){
			vert = -1;
		}
		else if(!keyInput.isKeyPressed(KeyEvent.VK_W) && keyInput.isKeyPressed(KeyEvent.VK_S)){
			vert = 1;
		}

		if(!keyInput.isKeyPressed(KeyEvent.VK_D) && keyInput.isKeyPressed(KeyEvent.VK_A)){
			hor = -1;
		}
		else if(keyInput.isKeyPressed(KeyEvent.VK_D) && !keyInput.isKeyPressed(KeyEvent.VK_A)){
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

		if(mouseInput.isButtonPressed(MouseEvent.BUTTON1)) {
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
		return new Weapon(10, 4, "Default Rifle", 10, Integer.MAX_VALUE, 10, 2);
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
		this.w.fire(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() / 2, this.team, this.getRotation());
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