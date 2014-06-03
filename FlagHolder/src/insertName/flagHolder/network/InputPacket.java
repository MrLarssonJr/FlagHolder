package insertName.flagHolder.network;

import insertName.flagHolder.*;
import insertName.flagHolder.input.*;

public class InputPacket {
	public KeyboardListener map;
	
	public InputPacket() {
		map = null;
	}

	public InputPacket(KeyboardListener map) {
		this.map = map;
	}
}
