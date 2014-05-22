package insertName.flagHolder;

import insertName.flagHolder.entities.Bullet;

public class Fire {

	//Variables
	private int x,y,team, width, height;
	private double xVelocity, yVelocity;

	//x and y determines where to spawn the bullet, xVelocity and yVelocity determines which direction and speed the bullet will get
	public Fire(int x, int y, int team, double xVelocity, double yVelocity, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.team = team;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.width = width;
		this.height = height;
	}
	
	//Kan något liknande fungera ?? /Filip
	
	public void fireBullet(){
		Bullet b = new Bullet(this.x, this.y, this.team, this.width, this.height, this.xVelocity, this.yVelocity);
	}
}
