package insertName.flagHolder.network.client;

import insertName.flagHolder.network.messages.*;

import javax.swing.*;

import simpleEngine.network.*;

public class ClientLobbyListener implements NetworkListener {
	private JTextArea clientList;

	public ClientLobbyListener(JTextArea clientList) {
		this.clientList = clientList;
	}

	@Override
	public void received(long clientID, Object obj) {
		if(obj instanceof ConnectedClientsMessage) {
			ConnectedClientsMessage ccm = (ConnectedClientsMessage) obj;

			clientList.setText("");
			for(long id : ccm.clientIDs) {
				clientList.append(id + "\n");
			}
		}
	}

}
