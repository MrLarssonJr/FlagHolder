package insertName.flagHolder;

import insertName.flagHolder.entities.*;

import java.awt.*;

import simpleEngine.core.*;

public class FlagHolder implements Task {
	private static GameClient gc;
	
	public static void main(String[] args) {
		Engine e = new Engine(new Dimension(3000, 1000));
		e.add("player", new Player(0, 0, 50, 50)); e.add(new Obstacle(100, 100, 50, 50));
		e.add(new Obstacle(500, 475, 2000, 50));
		e.add(new FlagHolder());
		
		gc = new GameClient();
		
		e.startGame();
	}
	
	@Override
	public void run(GameState state) {
		gc.setLatestGameState(state);
	}
	
}
