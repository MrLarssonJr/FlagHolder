package insertName.flagHolder;

import simpleEngine.core.*;
import simpleEngine.graphics.*;

import com.esotericsoftware.kryonet.*;

public class GameClient implements Runnable {
	private Client networkClient;
	private GameState latestGameState;
	private Window window;
	private double targetFPS = 30;
	
	public GameClient() {
		window = new Window(new AreaCamera());
		Thread t = new Thread(this);
		t.setName("Render");
		t.start();
	}
	
	public GameState getLatestGameState() {
		return latestGameState;
	}
	public void setLatestGameState(GameState latestGameState) {
		if(latestGameState == null) {
			throw new NullPointerException("latestGameState can't refer to null");
		}
		this.latestGameState = latestGameState;
	}
	
	@Override
	public void run() {
		while(!Thread.interrupted()) {
			long startTime = System.currentTimeMillis();
			
			if(latestGameState != null) {
				window.getCurrentCamera().run(latestGameState);
			}
			
			long loopDuration = System.currentTimeMillis() - startTime;
			long sleepTime = (long) ((targetFPS / 1000.0) - loopDuration);
			if(sleepTime < 0) {
				sleepTime = 0;
			}
			try {
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e) {
				break;
			}
		}
	}
}
