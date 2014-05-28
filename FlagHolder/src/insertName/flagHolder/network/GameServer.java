package insertName.flagHolder.network;

import insertName.flagHolder.*;
import insertName.flagHolder.entities.*;
import insertName.flagHolder.input.*;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import simpleEngine.core.*;
import simpleEngine.core.Map;
import simpleEngine.network.*;
import simpleEngine.standardObjects.tileMap.*;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.*;

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
		kryo.register(KeyMap.class);
		kryo.register(Weapon.class);
		kryo.register(Bullet.class);
		kryo.register(Flag.class);
		kryo.register(UpgradePack.class);
		kryo.register(Hitbox.class);
		kryo.register(boolean[][].class);
		kryo.register(boolean[].class);
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
