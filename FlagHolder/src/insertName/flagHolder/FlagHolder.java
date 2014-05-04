package insertName.flagHolder;

import java.awt.*;
import java.io.*;

import simpleEngine.core.*;

import com.esotericsoftware.minlog.*;

public class FlagHolder implements Task {
	private GameClient gc;
	private GameServer gs;
	
	public static void main(String[] args) throws HeadlessException, IOException {
		Log.set(Log.LEVEL_INFO);
		new FlagHolder();
	}
	
	@Override
	public void run(GameState state) {
		gc.setLatestGameState(state);
	}
	
	public FlagHolder() throws HeadlessException, IOException {
		gs = new GameServer();
		gc = new GameClient();
	}
	
}
