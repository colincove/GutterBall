package gameControllers.Levels;

import gameControllers.Game;
import gameControllers.Levels.Geom.Level1Walls;
import gameObjects.Portal;
import Components.BodyComponent;

public class PingPong extends ThrowLevel {
	public PingPong(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(3, 8);
		createPortal(3, 21);
	}
	public PingPong(Game game){
		this(game, new Level1Walls(game));
	}

}
