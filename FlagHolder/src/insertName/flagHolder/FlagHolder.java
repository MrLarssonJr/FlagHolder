package insertName.flagHolder;

import insertName.flagHolder.entities.*;
import insertName.flagHolder.graphics.*;
import insertName.flagHolder.network.*;

import java.awt.*;
import java.io.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;
import simpleEngine.graphics.Window;
import simpleEngine.standardObjects.tileMap.*;

public class FlagHolder {
	private GameClient gc, gc2;
	private GameServer gs;

	public static void main(String[] args) throws HeadlessException, IOException {
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

	private static void startLocalVersion() throws IOException {
		Engine e = new Engine(new TileMap(new Dimension(3000, 1000), new Dimension(40, 40), null));
		e.add(0 + "",  new Player(0, 0, 30, 30, 0, 1));

		TextureStore textures = new TextureStore();
		String[] res = {"player.png", "bullet.png"};
		textures.preLoadTextures(res);

		Camera c = new AreaCamera(textures, 0);
		Window window = new simpleEngine.graphics.Window(c);
//		window.getCurrentCamera().addKeyListener(new KeyListener() {
//
//			@Override
//			public void keyTyped(KeyEvent arg0) {
//			}
//
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//				map.setKeyStatus(arg0.getKeyCode(), false);
//				InputPacket packet = new InputPacket(map);
//				networkClient.sendUDP(packet);
//			}
//
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//				map.setKeyStatus(arg0.getKeyCode(), true);
//				InputPacket packet = new InputPacket(map);
//				networkClient.sendUDP(packet);
//			}
//		});
		e.add(c);
		e.startGame();
	}

	private static void usage() {
		System.out.println("Usage: java FlagHolder [networked|local]");
	}

}
