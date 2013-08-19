package gameControllers.Levels;

import gameControllers.Game;
import gameControllers.Levels.Geom.EffGeom;
import gameControllers.Levels.Geom.HiddenDragonGeom;
import gameObjects.ActorFactory;
import gameObjects.BodyEmmiter;
import Components.BodyComponent;

public class Eff extends ThrowLevel {
private BodyEmmiter emitter;
	public Eff(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(27, 5);
		createPortal(21, 15);
		createPortal(24, 3);
		createPortal(9, 15);
		createPortal(14, 20);
		createPortal(9, 25);
		createPortal(21, 28);
		emitter = new BodyEmmiter(game, 3, 3, new ActorFactory(game));
		
	}

	public Eff(Game game) {
		this(game, new EffGeom(game));
		// TODO Auto-generated constructor stub
	}
}
