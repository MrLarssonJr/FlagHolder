package insertName.flagHolder.entities;

import java.awt.Graphics2D;

import simpleEngine.collison.Collideable;
import simpleEngine.core.Engine;
import simpleEngine.graphics.TextureStore;
import simpleEngine.standardObjects.Entity;

public class Flag extends Entity{
	
	//Variables
	
	public Flag(double x, double y){
		super(x, y,10,10);
		
	}

	@Override
	public void draw(Graphics2D g, TextureStore textures) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long deltaT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collidedWith(Collideable otherObj) {
		if(otherObj instanceof Player){
			Engine.getLastCreatedEngine().remove(this);
		}
		
	}
}
