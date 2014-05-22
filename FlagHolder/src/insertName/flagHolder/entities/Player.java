package insertName.flagHolder.entities;

import insertName.flagHolder.*;

import java.awt.*;
import java.awt.event.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;
import simpleEngine.standardObjects.tileMap.*;

public class Player extends Entity {
	private double speedXO = 1, speedYO = 1;
	private int id;
	private Weapon w;
	private int hp;



	public Player() {
		super();
		id = 0;
	}

	public Player(double x, double y, double width, double heigth, int id) {
		super(x, y, width, heigth);
		this.id = id;
		this.w = w;
		this.hp = 100;
		w = new Weapon(5, 5, "Place holder", 5, 5, 5);
	}

	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("img.png");
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

	}

	public void setWeapon(Weapon w){
		this.w = w;
	}

	@Override
	public void collidedWith(Collideable arg0) {

	}

	@Override
	public void move(double dx, double dy) {


		super.move(dx, dy);
	}

}
