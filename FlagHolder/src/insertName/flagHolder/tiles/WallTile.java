package insertName.flagHolder.tiles;

import java.awt.*;
import java.awt.image.*;

import simpleEngine.graphics.*;
import simpleEngine.standardObjects.tileMap.*;

public class WallTile extends TileType {
	/**
	 *
	 */
	private static final long serialVersionUID = 5828277385462252749L;

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void draw(GameGraphics g, TextureStore textures, Rectangle size) {
		BufferedImage image = textures.getPreLoadedTexture("block.png");
		g.drawImage(image, size.x, size.y, size.width, size.height, null);
	}

}
