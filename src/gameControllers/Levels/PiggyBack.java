package gameControllers.Levels;

import gameControllers.Game;
import gameControllers.Levels.Geom.HiddenDragonGeom;
import gameControllers.Levels.Geom.PiggyBackGeom;
import Components.BodyComponent;

public class PiggyBack extends ThrowLevel {

	public PiggyBack(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(9, 3);
		createPortal(18, 3);
		createPortal(22, 7);
		createPortal(18, 17);
		createPortal(28, 21);
	}
	public PiggyBack(Game game) {
		this(game, new PiggyBackGeom(game));
		// TODO Auto-generated constructor stub
	}
}
