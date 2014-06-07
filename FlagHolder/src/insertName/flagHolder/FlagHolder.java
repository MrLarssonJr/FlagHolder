package insertName.flagHolder;

import insertName.flagHolder.entities.*;
import insertName.flagHolder.graphics.*;
import insertName.flagHolder.network.client.*;
import insertName.flagHolder.network.server.*;
import insertName.flagHolder.tiles.*;

import java.awt.*;
import java.io.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.graphics.Window;
import simpleEngine.input.*;
import simpleEngine.log.*;
import simpleEngine.standardObjects.tileMap.*;

public class FlagHolder {
	private GameClient gc, gc2;
	private GameServer gs;

	public static void main(String[] args) throws HeadlessException, IOException, AWTException {
		Logger log = new Logger();
		Logger.setStaticLogger(log);
		log.setPrintToSystemOut(true);
		log.setMinSysPrintLevel(LogPriority.DEBUG);
		if(args.length <= 0) {
			System.out.println("Need a flag");
			usage();
		}
		String ver = args[0];
		if(ver.equalsIgnoreCase("networked")) {
			startNetworkVersion();
		}
		else if(ver.equalsIgnoreCase("local")) {
			startLocalVersion();
		}
		else {
			System.out.println("Unrecognized flag");
			usage();
		}
	}

	private static void startNetworkVersion() throws IOException {
		new GameServer();
		new GameClient();
	}

	private static void startLocalVersion() throws IOException, AWTException {
		KeyboardListener keyInput = new KeyboardListener();
		MouseListener mouseInput = new MouseListener();
		MouseCapturer mouseCap = new MouseCapturer();

		TileMap map = new TileMap(new Dimension(3000, 1000), new Dimension(40, 40), null);
		TileType[][] tiles = new TileType[75][25];
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[x].length; y++) {
				tiles[x][y] = new TileType.DefaultTile();
			}
		}
		for(int x = 4; x < (tiles.length - 5); x++) {
			tiles[x][tiles[x].length/2] = new WallTile();
		}

		for(int x = 4; x < (tiles.length - 5); x++) {
			tiles[x][3] = new WallTile();
		}
		for(int y = 3; y < tiles[4].length/2; y++) {
			tiles[4][y] = new WallTile();
		}

		map.setMap(tiles);

		Engine e = new Engine(map);
		Player p = new Player(0, 0, 30, 30, 1, keyInput, mouseInput, 600, 0L);
		e.add(0 + "",  p);
		e.add( new Flag(50,40));
		e.add( new UpgradePack(100,0,1));
		Pointer pointer = new Pointer(10, 10, mouseCap, p);
		p.setPointer(pointer);
		e.add(pointer);

		TextureStore textures = new TextureStore();
		String[] res = {"player.png", "bullet.png", "flag.png", "upgrade.png", "block.png"};
		textures.preLoadTextures(res);

		Camera c = new AreaCamera(textures, 0);
		Window window = new simpleEngine.graphics.Window(c);
		window.getCurrentCamera().addKeyListener(keyInput);
		window.getCurrentCamera().addMouseListener(mouseInput);
		window.getCurrentCamera().addMouseMotionListener(mouseInput);
		e.add(c);
		e.add(mouseCap);
		mouseCap.startCapture(c);
		e.startGame();
	}

	private static void usage() {
		System.out.println("Usage: java FlagHolder [networked|local]");
	}

}
