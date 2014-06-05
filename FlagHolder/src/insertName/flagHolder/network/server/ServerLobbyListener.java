package insertName.flagHolder.network.server;

import insertName.flagHolder.entities.Flag;
import insertName.flagHolder.entities.Player;
import insertName.flagHolder.entities.Pointer;
import insertName.flagHolder.entities.UpgradePack;
import insertName.flagHolder.network.messages.ConnectedClientsMessage;
import insertName.flagHolder.network.messages.LobbyNetworkMarkers;

import java.awt.AWTException;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import simpleEngine.core.Engine;
import simpleEngine.input.KeyboardListener;
import simpleEngine.input.MouseCapturer;
import simpleEngine.input.MouseListener;
import simpleEngine.log.Logger;
import simpleEngine.network.ClientHandler;
import simpleEngine.network.GameStateUpdateSenderTask;
import simpleEngine.network.NetworkListener;
import simpleEngine.network.Server;
import simpleEngine.standardObjects.tileMap.TileMap;

public class ServerLobbyListener implements NetworkListener {
	Server server;

	public ServerLobbyListener(Server server) {
		this.server = server;
	}



	@Override
	public void received(long clientID, Object obj) {
		if(obj instanceof LobbyNetworkMarkers) {
			switch ((LobbyNetworkMarkers) obj) {
			case GET_CONNECTED_PLAYERS:
				ArrayList<String> clientStrings = new ArrayList<String>();
				Collection<ClientHandler> clients = server.getClientHandlers();
				Iterator<ClientHandler> i = clients.iterator();
				while(i.hasNext()) {
					clientStrings.add(i.next().toString());
				}
				try {
					server.sendToAll(new ConnectedClientsMessage(clientStrings));
				}
				catch (IOException e) {
					Logger.logToStaticLogger("Couldn't send connected players", e);
				}
				
				if(clientStrings.size() == 4) {
					try {
						server.sendToAll(LobbyNetworkMarkers.START);
						Engine e = new Engine(new TileMap(new Dimension(3000, 1000), new Dimension(40, 40), null));
						
						e.add(new Flag(50,40));
						e.add(new UpgradePack(100,0,1));
						
						KeyboardListener keyInput = new KeyboardListener();
						MouseListener mouseInput = new MouseListener();
						MouseCapturer mouseCap = new MouseCapturer();
						
						for(ClientHandler client : clients) {
							Player player = new Player(0, 0, 30, 30, 1, keyInput, mouseInput, 1200, client.getClientID());
							Pointer pointer = new Pointer(10, 10, mouseCap, player);
							
							e.add(player);
							e.add(pointer);
						}
						
						e.add(new GameStateUpdateSenderTask(server));
					}
					catch (IOException | AWTException e) {
						Logger.logToStaticLogger("Couldn't send start marker", e);
					}
				}
				break;

			default:
				break;
			}
		}
	}

}
