package insertName.flagHolder.network;

import insertName.flagHolder.*;
import insertName.flagHolder.input.*;

public class InputPacket {
	public KeyMap map;
	
	public InputPacket() {
		map = null;
	}

	public InputPacket(KeyMap map) {
		this.map = map;
	}
}
