package insertName.flagHolder;

import insertName.flagHolder.entities.*;
import insertName.flagHolder.graphics.*;
import insertName.flagHolder.network.*;
import insertName.flagHolder.network.client.*;

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
		System.out.println("start");
		KeyboardListener keyInput = new KeyboardListener();
		MouseListener mouseInput = new MouseListener();
		MouseCapturer mouseCap = new MouseCapturer();
		Engine e = new Engine(new TileMap(new Dimension(3000, 1000), new Dimension(40, 40), null));
		Player p = new Player(0, 0, 30, 30, 0, 1, keyInput, mouseInput, 1200);
		e.add(0 + "",  p);
		e.add( new Flag(50,40));
		e.add( new UpgradePack(100,0,1));
		Pointer pointer = new Pointer(10, 10, mouseCap, p);
		p.setPointer(pointer);
		e.add(pointer);

		TextureStore textures = new TextureStore();
		String[] res = {"player.png", "bullet.png", "flag.png", "upgrade.png"};
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
