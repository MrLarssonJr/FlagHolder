package insertName.flagHolder.entities;

import java.awt.*;
import java.io.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;
import simpleEngine.standardObjects.tileMap.*;

public class Player extends Entity {
	private double speedX = 0.24, speedY = 0.24;



	public Player() {
		super();
	}

	public Player(double x, double y, double width, double heigth) {
		super(x, y, width, heigth);
	}

	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("res" + File.separator + "img.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
	}

	@Override
	public void update(long deltaT) {
		Map map = Engine.getLastCreatedEngine().getMap();

		double dx = speedX * deltaT;
		double dy = speedY * deltaT;

		if(this.getY() <= 0 && speedY < 0) {
			speedY *= -1;
		}
		else if(((this.getY() + this.getHeight()) >= Engine.getLastCreatedEngine().getMap().getSize().height) && speedY > 0) {
			speedY *= -1;
		}

		if(this.getX() <= 0 && speedX < 0) {
			speedX *= -1;
		}
		else if((this.getX() + this.getWidth()) >= Engine.getLastCreatedEngine().getMap().getSize().width && speedX > 0) {
			speedX *= -1;
		}

		if(map instanceof TileMap) {
			TileMap tMap = (TileMap) map;

			double nx = this.getX() + dx;
			double ny = this.getY() + dy;

			if(tMap.isPosBlocked((int) nx, (int) this.getY())) {
				dx = 0;
				nx = this.getX();
				speedX *= -1;
			}
			if(tMap.isPosBlocked((int) (nx + this.getWidth()), (int) this.getY())) {
				dx = 0;
				nx = this.getX();
				speedX *= -1;
			}
			if(tMap.isPosBlocked((int) nx, (int) ny)) {
				dy = 0;
				speedY *= -1;
			}
			if(tMap.isPosBlocked((int) nx, (int) (ny + this.getHeight()))) {
				dy = 0;
				speedY *= -1;
			}
		}

		this.move(dx, dy);

	}

	@Override
	public void collidedWith(Collideable arg0) {

	}

	@Override
	public void move(double dx, double dy) {


		super.move(dx, dy);
	}

}
