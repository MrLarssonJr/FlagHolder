package insertName.flagHolder.launcher;

import insertName.flagHolder.network.client.ClientLobbyListener;
import insertName.flagHolder.network.messages.LobbyNetworkMarkers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import simpleEngine.network.Client;

public class Lobby extends JPanel {
	private Client client;

	public Lobby(InetAddress address, int port, JFrame frame) throws IOException {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc;
		
		DisconnectFromLobbyAction dcAction = new DisconnectFromLobbyAction(this, frame);
		
		JLabel listLabel = new JLabel("Connected players:");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		this.add(listLabel, gbc);
		
		JTextArea clientList = new JTextArea();
		clientList.setToolTipText("People in this lobby.");
		clientList.setEditable(false);
		
		JScrollPane scpne = new JScrollPane(clientList);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 2;
		this.add(scpne, gbc);
		
		JLabel notice = new JLabel("The game will start shortly");
		notice.setToolTipText("The game will start when there is atleast for people in this lobby.");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(notice, gbc);
		
		JButton dc = new JButton(dcAction);
		dc.setToolTipText("Click to disconnect from this lobby and return to the join screen.");
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(dc, gbc);
		
		client = new Client(address, port);
		client.add(new ClientLobbyListener(clientList));
		client.writeObject(LobbyNetworkMarkers.GET_CONNECTED_PLAYERS);
	}

	public void disconnect() {
		client.disconnect();
	}

}
