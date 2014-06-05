package insertName.flagHolder.launcher;

import insertName.flagHolder.network.client.*;

import java.io.*;
import java.net.*;

import javax.swing.*;

import simpleEngine.network.*;

public class Lobby extends JPanel {
	private Client client;

	public Lobby(InetAddress address, int port) throws IOException {
		client = new Client(address, port);
		client.add(new ClientLobbyListener(clientList))
	}

}
