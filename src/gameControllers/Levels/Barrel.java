package gameControllers.Levels;

import gameControllers.Game;
import gameControllers.Levels.Geom.BarrelGeom;
import gameControllers.Levels.Geom.BunkBedGeom;
import Components.BodyComponent;

public class Barrel extends ThrowLevel {

	public Barrel(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		createPortal(3, 3);
		createPortal(27, 3);
		createPortal(22, 30);
		
		createPortal(8, 17);
		createPortal(8, 23);
		createPortal(8, 29);
		createPortal(8, 35);
		createPortal(8, 41);
		createPortal(8, 47);
	}
	public Barrel(Game game) {
		this(game, new BarrelGeom(game));
		// TODO Auto-generated constructor stub
	}

}
