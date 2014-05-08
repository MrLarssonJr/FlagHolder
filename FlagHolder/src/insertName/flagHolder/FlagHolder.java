package insertName.flagHolder;

import java.awt.HeadlessException;
import java.io.IOException;

import com.esotericsoftware.minlog.Log;

public class FlagHolder {
	private GameClient gc, gc2;
	private GameServer gs;
	
	public static void main(String[] args) throws HeadlessException, IOException {
		Log.set(Log.LEVEL_INFO);
		new FlagHolder();
	}
	
	
	
	public FlagHolder() throws HeadlessException, IOException {
		gs = new GameServer();
		gc = new GameClient();
	}
	
}
