package insertName.flagHolder;

import insertName.flagHolder.entities.Player;

import java.util.HashMap;

import simpleEngine.core.Engine;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class InputReciver extends Listener {
	private HashMap<Integer, KeyMap> maps;
	
	public InputReciver(HashMap<Integer, KeyMap> maps) {
		this.maps = maps;
	}

	@Override
	public void received(Connection con, Object obj) {
		if(obj instanceof InputPacket) {
			InputPacket packet = (InputPacket) obj;
			
			maps.put(con.getID(), packet.map);
			
			System.out.println("Recived keys");
		}
	}
	
	@Override
	public void connected(Connection arg0) {
		Engine.getLastCreatedEngine().add(arg0.getID() + "" , new Player(0, 0, 30, 30, arg0.getID()));
	}

}
