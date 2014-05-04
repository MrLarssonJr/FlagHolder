package insertName.flagHolder;

import java.awt.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;

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
	
}
