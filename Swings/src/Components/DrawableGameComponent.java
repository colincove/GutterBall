package Components;

import android.content.res.Resources;
import gameControllers.Game;
import infoHolders.DrawInfo;
import infoHolders.UpdateInfo;
import Components.interfaces.IDrawableComponent;

public class DrawableGameComponent extends AbstractGameComponent implements
		IDrawableComponent {
	protected Resources resources;
	protected int drawOrder = 0;

	public DrawableGameComponent(Game game) {
		this(game, 0);
	}
	public DrawableGameComponent(Game game, int drawOrder) {
		super(game);
		this.drawOrder=drawOrder;
		activity.addDrawableComponent(this);
		resources = activity.getResources();
	}

	@Override
	public void draw(DrawInfo drawInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UpdateInfo updateInfo) {
		super.update(updateInfo);
	}

	@Override
	public int drawOrder() {
		// TODO Auto-generated method stub
		return drawOrder;
	}
	@Override
public void destroy(){
		game.removeDrawableComponent(this);
		super.destroy();
	}
}
