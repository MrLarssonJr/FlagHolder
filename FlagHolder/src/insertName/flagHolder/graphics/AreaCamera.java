package insertName.flagHolder.graphics;

import java.awt.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.standardObjects.tileMap.*;

public class AreaCamera extends Camera {
	/**
	 *
	 */
	private static final long serialVersionUID = 7539527696177083528L;
	private int id;

	public AreaCamera(TextureStore textures, int id) {
		super(new Dimension(1000, 1000), textures);
		this.id = id;
	}

	@Override
	public void draw(GameGraphics g, GameState state) {
		GameObject player = state.indexedObjects.get(id + "");
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

			map.draw(g, this.textures);
		}
	}

}
