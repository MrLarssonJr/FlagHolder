package insertName.flagHolder;

import java.awt.*;
import java.awt.event.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.input.*;

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
		int translation = ((int)((player.getX() + player.getWidth()/2)/1000)) * -1000;
		g.translate(translation, 0);
		g.setColor(Color.BLACK);
		Dimension d = state.map.getSize();
		g.fillRect(0, 0, d.width, d.height);
	}
	
	@Override
	public void run(GameState state) {
		super.run(state);
		if(cameraViewChangeCooldown > 0) {
			cameraViewChangeCooldown--;
		}
		if(KeyboardListener.isKeyPressed(KeyEvent.VK_C) && cameraViewChangeCooldown == 0) {
			if(++cameraView >= 3) {
				cameraView = 0;
			}
			cameraViewChangeCooldown = 10;
		}
	}
	
}
