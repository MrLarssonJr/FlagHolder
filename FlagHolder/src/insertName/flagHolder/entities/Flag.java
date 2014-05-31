package insertName.flagHolder.entities;

import java.awt.Graphics2D;
import java.awt.Image;

import simpleEngine.collison.Collideable;
import simpleEngine.core.Engine;
import simpleEngine.graphics.TextureStore;
import simpleEngine.standardObjects.Entity;

public class Flag extends Entity{
	
	//Bobbing image variables
	private double yStandard, f; //yStandard is the initiated coordinate, f is the bobbing distance
	private boolean upDown; //upDown determines if the bobbing is + or -
	
	public Flag(){
		
	}
	
	public Flag(double x, double y){
		super(x, y,40,40);
		this.yStandard = y;
		this.f = 15;
		this.upDown = true;
	}

	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("flag.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);
		
	}

	@Override
	public void update(long deltaT) {
		//Bobbing logic
		if(upDown){
            double destination = yStandard - f;
            this.move(0, -(this.getY()-destination)/25);
            if(this.getY()-8 < destination){
                upDown = false;
            }
        }
        else if(!upDown){
            double destination = yStandard + f;
            this.move(0, (destination-this.getY())/25);
            if(this.getY()+8 > destination){
                upDown = true;
            }
        }
	}

	@Override
	public void collidedWith(Collideable otherObj) {
		if(otherObj instanceof Player){
			Engine.getLastCreatedEngine().remove(this);
		}
		
	}
}
