package insertName.flagHolder.network;

import insertName.flagHolder.network.client.*;
import simpleEngine.core.*;

import com.esotericsoftware.kryonet.*;

public class GameStateReciver extends Listener {

	private GameClient gc;

	public GameStateReciver(GameClient gameClient) {
		gc = gameClient;
	}

	@Override
	public void received(Connection con, Object obj) {
		if(obj instanceof GameState) {
			gc.setLatestGameState((GameState) obj);
		}
		else if(obj instanceof String) {
			System.out.println(obj);
		}
	}

	@Override
	public void connected(Connection con) {
		System.out.println("connected to " + con.getRemoteAddressUDP());
	}

	@Override
	public void disconnected(Connection con) {
		System.out.println("disconnected from " + con.getRemoteAddressUDP());
	}

}
