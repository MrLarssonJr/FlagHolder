package insertName.flagHolder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import simpleEngine.core.GameObject;
import simpleEngine.core.GameState;
import simpleEngine.graphics.Camera;
import simpleEngine.standardObjects.tileMap.TileMap;

public class AreaCamera extends Camera {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7539527696177083528L;
	private int cameraView = 0;
	private int cameraViewChangeCooldown = 0;
	
	public AreaCamera() {
		super(new Dimension(1000, 1000));
	}
	
	@Override
	public void draw(Graphics2D g, GameState state) {
		GameObject player = state.indexedObjects.get("player");
		int translationX = -((int)(player.getX() + player.getWidth()/2)) + 500;
		int translationY = -((int)(player.getY() + player.getHeight()/2)) + 500;
		g.translate(translationX, translationY);
		
		g.setColor(Color.BLACK);
		Dimension d = state.map.getSize();
		g.fillRect(-(d.width/2), -(d.height/2), d.width * 2, d.height * 2);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, d.width, d.height);
		
		if(state.map instanceof TileMap) {
			TileMap map = (TileMap) state.map;
			
			map.draw(g);
		}
	}
	
}
