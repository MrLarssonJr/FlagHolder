package insertName.flagHolder.network.client;

import insertName.flagHolder.network.messages.ConnectedClientsMessage;
import insertName.flagHolder.network.messages.LobbyNetworkMarkers;

import javax.swing.JTextArea;

import simpleEngine.network.NetworkListener;

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
			for(String id : ccm.clients) {
				clientList.append(id + "\n");
			}
		}
		else if(obj instanceof LobbyNetworkMarkers) {
			switch ((LobbyNetworkMarkers) obj) {
			case GET_CONNECTED_PLAYERS:
				
				break;
			case DISCONNECT:
				
				break;
			default:
				break;
			}
		}
	}

}
