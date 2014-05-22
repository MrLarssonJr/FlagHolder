package insertName.flagHolder;

import java.util.HashMap;

public class KeyMap {
	private HashMap<Integer, Boolean> keys;
	
	public KeyMap() {
		keys = new HashMap<Integer, Boolean>();
	}
	
	public void setKeyStatus(int key, boolean newStatus) {
		keys.put(key, newStatus);
	}
	
	public boolean isKeyPressed(int key) {
		Boolean status = keys.get(key);
		if(status == null || !status.booleanValue()) {
			return false;
		}
		else {
			return true;
		}
	}
}
