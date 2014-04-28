package insertName.flagHolder;

import insertName.flagHolder.entities.*;

import java.awt.*;
import java.util.*;

import Engine.*;

public class FlagHolder {
	public static void main(String[] args) {
		ArrayList<Scene> scenes = new ArrayList<Scene>();
		Scene s = new Scene(Color.BLACK, new Dimension(400, 400));
		s.add(new Player());
		
		scenes.add(s);
		
		Engine.getInstance(scenes);
		
		Engine.startGame();
	}
	
}
