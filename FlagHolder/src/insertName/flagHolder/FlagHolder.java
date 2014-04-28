package insertName.flagHolder;

import java.awt.*;
import java.util.*;

import Engine.*;

public class FlagHolder {
	public static void main(String[] args) {
		ArrayList<Scene> scenes = new ArrayList<Scene>();
		scenes.add(new Scene(Color.BLACK, new Dimension(400, 400)));
		
		Engine.getInstance(scenes);
		
		Engine.startGame();
	}
	
}
