package insertName.flagHolder;

import insertName.flagHolder.entities.Obstacle;
import insertName.flagHolder.entities.Player;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import simpleEngine.core.Engine;
import simpleEngine.core.GameObject;
import simpleEngine.core.GameState;
import simpleEngine.core.Map;
import simpleEngine.network.GameStateUpdateSenderTask;
import simpleEngine.standardObjects.tileMap.TileMap;
import simpleEngine.standardObjects.tileMap.TileType;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

public class GameServer {
	public static void main(String[] args) throws IOException {
		Log.set(Log.LEVEL_INFO);
		new GameServer();
		System.exit(0);
	}
	
	private Server networkServer;
	private Engine e;
	private static HashMap<Integer, KeyMap> maps;
	
	public GameServer() throws IOException {
		maps = new HashMap<Integer, KeyMap>();
		networkServer = new Server(16384, 4096);
		registerPackets(networkServer.getKryo());
		networkServer.addListener(new InputReciver(maps));
		networkServer.start();
		networkServer.bind(54555, 54777);
		
		e = new Engine(new TileMap(new Dimension(3000, 1000), new Dimension(40, 40), null));
//		e.add("player", new Player(0, 0, 30, 30));
		e.add(new GameStateUpdateSenderTask(networkServer));
		
		e.startGame();
		JOptionPane.showConfirmDialog(null, "Exit", "Exit", JOptionPane.YES_OPTION);
		networkServer.stop();
	}
	
	public static void registerPackets(Kryo kryo) {
		kryo.register(GameObject.class);
		kryo.register(Map.class);
		kryo.register(Player.class);
		kryo.register(Obstacle.class);
		kryo.register(HashMap.class);
		kryo.register(ArrayList.class);
		kryo.register(Rectangle2D.Double.class);
		kryo.register(Dimension.class);
		kryo.register(String.class);
		kryo.register(TileMap.class);
		kryo.register(TileType.class);
		kryo.register(TileType[].class);
		kryo.register(TileType[][].class);
		kryo.register(TileType.DefaultTile.class);
		kryo.register(TileType.DefaultSolidTile.class);
		kryo.register(GameState.class);
		kryo.register(InputPacket.class);
		kryo.register(Integer.class);
		kryo.register(Boolean.class);
		kryo.register(Integer[].class);
		kryo.register(Boolean[].class);
		kryo.register(KeyMap.class);
	}
	
	public static KeyMap getLatestKeyMap(Integer id) {
		KeyMap map = maps.get(id);
		if(map == null) {
			map = new KeyMap();
			maps.put(id, map);
		}
		return map;
	}
	
}
