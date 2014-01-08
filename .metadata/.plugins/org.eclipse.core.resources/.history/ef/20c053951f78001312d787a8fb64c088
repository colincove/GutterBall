package gameControllers.Levels;

import gameControllers.Game;
import gameControllers.Levels.Geom.Level1Walls;
import gameObjects.Portal;
import Components.BodyComponent;

public class PingPong extends ThrowLevel {
private Portal p1;
private Portal p2;
	public PingPong(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		p1  = new Portal(game, 3, 8);
		p2 = new Portal(game, 3, 21);
	}
	public PingPong(Game game){
		this(game, new Level1Walls(game));
	}

}
