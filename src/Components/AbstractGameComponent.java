package Components;

import gameControllers.Game;
import gameControllers.GameView;
import infoHolders.UpdateInfo;
import android.app.Activity;

public abstract class AbstractGameComponent extends AbstractComponent {
	protected Game game;
	protected GameView gameView;

	public AbstractGameComponent(Game game) {
		super(game);
		gameView = (GameView) view;
		this.game=game;
		// TODO Auto-generated constructor stub
		game.addGameComponent(this);
	}

	public abstract void update(UpdateInfo updateInfo);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		game.removeGameComponent(this);
		game = null;
		super.destroy();
		

	}
}
