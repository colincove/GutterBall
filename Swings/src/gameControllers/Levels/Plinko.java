package gameControllers.Levels;

import com.example.swings.R;

import gameControllers.Game;
import gameControllers.Levels.Geom.BarrelGeom;
import gameControllers.Levels.Geom.PlinkoGeom;
import Components.BodyComponent;

public class Plinko extends ThrowLevel {

	public Plinko(Game game, BodyComponent geometry) {
		this(game, geometry, R.drawable.background);
		// TODO Auto-generated constructor stub
	}

	public Plinko(Game game, BodyComponent geometry, int bgId) {
		super(game, geometry, bgId);
		// TODO Auto-generated constructor stub
		launcher.setX(4);
		
	}
	public Plinko(Game game) {
		this(game, new PlinkoGeom(game));
		// TODO Auto-generated constructor stub
	}
}
