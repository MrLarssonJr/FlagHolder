package insertName.flagHolder;

import insertName.flagHolder.entities.Flag;
import insertName.flagHolder.entities.Player;
import insertName.flagHolder.entities.Pointer;
import insertName.flagHolder.entities.UpgradePack;
import insertName.flagHolder.graphics.AreaCamera;
import insertName.flagHolder.network.client.GameClient;
import insertName.flagHolder.network.server.GameServer;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.IOException;

import simpleEngine.core.Engine;
import simpleEngine.graphics.Camera;
import simpleEngine.graphics.TextureStore;
import simpleEngine.graphics.Window;
import simpleEngine.input.KeyboardListener;
import simpleEngine.input.MouseCapturer;
import simpleEngine.input.MouseListener;
import simpleEngine.log.LogPriority;
import simpleEngine.log.Logger;
import simpleEngine.standardObjects.tileMap.TileMap;

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
		Engine e = new Engine(new TileMap(new Dimension(3000, 1000), new Dimension(40, 40), null));
<<<<<<< HEAD
		Player p = new Player(0, 0, 30, 30, 1, keyInput, mouseInput, 1200, 0L);
=======
		Player p = new Player(0, 0, 30, 30, 0, 1, keyInput, mouseInput, 600);
>>>>>>> cf83a8bbfd63a8bc7a89cf13b14a124d5065533d
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
