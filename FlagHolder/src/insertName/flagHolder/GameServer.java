package insertName.flagHolder;

import insertName.flagHolder.entities.*;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

import simpleEngine.core.*;
import simpleEngine.core.Map;
import simpleEngine.network.*;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;

public class GameServer {
	private Server networkServer;
	private Engine e;
	
	public GameServer() throws IOException {
		networkServer = new Server();
		registerPackets(networkServer.getKryo());
		networkServer.start();
		networkServer.bind(54555, 54777);
		
		e = new Engine(new Dimension(3000, 1000));
		e.add("player", new Player(0, 0, 50, 50)); e.add(new Obstacle(100, 100, 50, 50));
		e.add(new Obstacle(500, 475, 2000, 50));
		e.add(new GameStateUpdateSenderTask(networkServer));
		
		e.startGame();
	}
	
	public static void registerPackets(Kryo kryo) {
		kryo.register(GameObject.class);
		kryo.register(Map.class);
		kryo.register(GameState.class);
		kryo.register(Player.class);
		kryo.register(Obstacle.class);
		kryo.register(HashMap.class);
		kryo.register(ArrayList.class);
		kryo.register(Rectangle2D.Double.class);
		kryo.register(Dimension.class);
		kryo.register(String.class);
	}
	
}
