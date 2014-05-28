package insertName.flagHolder.network;

import insertName.flagHolder.*;
import insertName.flagHolder.graphics.*;
import insertName.flagHolder.input.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import simpleEngine.core.*;
import simpleEngine.graphics.*;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.*;

public class GameClient implements Runnable {
	private Client networkClient;
	private GameState latestGameState;
	private simpleEngine.graphics.Window window;
	private double targetFPS = 30;
	private KeyMap map;

	public static void main(String[] args) throws HeadlessException, IOException {
		Log.set(Log.LEVEL_INFO);
		new GameClient();
	}

	public GameClient() throws HeadlessException, IOException {
		map = new KeyMap();
		TextureStore textures = new TextureStore();
		String[] res = {"player.png", "bullet.png"};
		textures.preLoadTextures(res);

		initNetwork();

		window = new simpleEngine.graphics.Window(new AreaCamera(textures, networkClient.getID()));
		window.getCurrentCamera().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				map.setKeyStatus(arg0.getKeyCode(), false);
				InputPacket packet = new InputPacket(map);
				networkClient.sendUDP(packet);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				map.setKeyStatus(arg0.getKeyCode(), true);
				InputPacket packet = new InputPacket(map);
				networkClient.sendUDP(packet);
			}
		});
		Thread t = new Thread(this);
		t.setName("Render");
		t.start();
	}

	private void initNetwork() throws IOException {
		networkClient = new Client(16384, 4096);
		GameServer.registerPackets(networkClient.getKryo());
		networkClient.start();

		java.util.List<InetAddress> hosts = networkClient.discoverHosts(54777, 500);
		Object[] strings = new Object[hosts.size()];
		for(int i = 0; i < hosts.size(); i++) {
			strings[i] = hosts.get(i).getHostAddress();
		}
		String address = (String) JOptionPane.showInputDialog(null, "Host?", "Choose host", JOptionPane.QUESTION_MESSAGE, null, strings, strings[0]);

		networkClient.addListener(new GameStateReciver(this));
		networkClient.connect(5000, address, 54555, 54777);
	}

	public GameState getLatestGameState() {
		return latestGameState;
	}
	public void setLatestGameState(GameState latestGameState) {
		if(latestGameState == null) {
			throw new NullPointerException("latestGameState can't refer to null");
		}
		this.latestGameState = latestGameState;
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			long startTime = System.currentTimeMillis();

			if(latestGameState != null) {
				window.getCurrentCamera().run(latestGameState);
			}

			long loopDuration = System.currentTimeMillis() - startTime;
			long sleepTime = (long) ((targetFPS / 1000.0) - loopDuration);
			if(sleepTime < 0) {
				sleepTime = 0;
			}
			try {
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException e) {
				break;
			}
		}
	}
}