package insertName.flagHolder.entities;

import java.awt.*;

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
		super(x, y,10,10);
		this.yStandard = y;
		this.f = 50;
		this.upDown = true;
	}

	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		Image img = textures.getPreLoadedTexture("flag.png");
		g.drawImage(img, (int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight(), null);

	}

	@Override
	public void update(long deltaT) {
		//Bobbing logic
		if(upDown){
            double dy = yStandard - f;
            this.setY(this.getY() + (this.getY()-dy)/25);
            if(this.getY()-5 < dy){
                upDown = false;
            }
        }
        else if(!upDown){
            double dy = yStandard + f;
            this.setY(this.getY() + (dy-this.getY())/25);
            if(this.getY()+10 > dy){
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
