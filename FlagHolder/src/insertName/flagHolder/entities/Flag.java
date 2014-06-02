package insertName.flagHolder.entities;

import java.awt.image.*;

import simpleEngine.collison.*;
import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.*;

public class Flag extends Entity{

	/**
	 *
	 */
	private static final long serialVersionUID = 4241096700829440586L;
	//Bobbing image variables
	private double yStandard, f; //yStandard is the initiated coordinate, f is the bobbing distance
	private boolean upDown; //upDown determines if the bobbing is + or -

	public Flag(double x, double y){
		super(x, y,40,40);
		this.yStandard = y;
		this.f = 15;
		this.upDown = true;
	}

	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		BufferedImage img = textures.getPreLoadedTexture("flag.png");
		g.drawGameObject(this, img);

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
