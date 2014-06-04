package insertName.flagHolder.entities;

import java.awt.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.input.*;
import simpleEngine.log.*;

public class Pointer extends GameObject implements Drawable, Updateable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7817692278049149123L;

	private MouseCapturer mouseInfo;
	private Player player;
	private double lastDeltaX = 0, lastDeltaY = 0;
	private double deltaXToPlayer = 0, deltaYToPlayer = 0;

	public Pointer(double width, double height, MouseCapturer mouse, Player player) {
		super(player.getX(), player.getY(), width, height);
		mouseInfo = mouse;
		this.player = player;
	}

	@Override
	public void update(long deltaT) {
		double mouseTotdeltaX = mouseInfo.getDeltaX();
		double mouseTotDeltaY = mouseInfo.getDeltaY();

		double deltaXSinceLast = mouseTotdeltaX - lastDeltaX;
		double deltaYSinceLast = mouseTotDeltaY - lastDeltaY;

		lastDeltaX = mouseTotdeltaX;
		lastDeltaY = mouseTotDeltaY;

		deltaXToPlayer += deltaXSinceLast;
		deltaYToPlayer += deltaYSinceLast;

		double xOffset = 500 - this.getWidth();
		double yOffset = 500 - this.getHeight();
		if(deltaXToPlayer < -xOffset) {
			deltaXToPlayer = -xOffset;
		}
		else if(deltaXToPlayer > xOffset) {
			deltaXToPlayer = xOffset;
		}
		if(deltaYToPlayer < -yOffset) {
			deltaYToPlayer = -yOffset;
		}
		else if(deltaYToPlayer > yOffset) {
			deltaYToPlayer = yOffset;
		}

		this.setX(player.getX() + deltaXToPlayer);
		this.setY(player.getY() + deltaYToPlayer);

		Logger.logToStaticLogger(LogPriority.DEBUG, "Pointer x: " + this.getX() + " y: " + this.getY());

	}

	@Override
	public void draw(GameGraphics g, TextureStore textures) {
		g.setColor(Color.WHITE);
		g.fillRect((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
	}

}
