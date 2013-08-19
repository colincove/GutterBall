package gameObjects;

import android.graphics.Paint;
import infoHolders.DrawInfo;
import infoHolders.UpdateInfo;
import gameControllers.Game;
import Components.BodyCompFactory;
import Components.BodyComponent;
import Components.DefaultBodyFactory;
import Components.DrawableGameComponent;

public class BodyEmmiter extends DrawableGameComponent {
	private int freq;//milli
	private int counter=0;
	private Paint paint;
	private BodyCompFactory factory;
	public BodyEmmiter(Game activity, float x, float y) {
		this(activity, x, y, new DefaultBodyFactory(activity));
	}
	public BodyEmmiter(Game activity, float x, float y, BodyCompFactory factory) {
		super(activity);
		setPos(x, y);
		// TODO Auto-generated constructor stub
		freq=1000;
		paint  = new Paint();
		paint.setARGB(255,237, 226, 138);
		this.factory=factory;
	}
	@Override
	public void update(UpdateInfo info){
		super.update(info);
		counter+=info.getDeltaTime();
		if(counter>freq){
			counter=0;
			factory.getBodyComp(getX(), getY());
		}
	}
	@Override
	public void draw(DrawInfo info){
		super.draw(info);
		//Body
				info.getCanvas().drawCircle(gameView.toScreenX(getX()),
						gameView.toScreenY(getY()),
						gameView.toScreen(1.5f), paint);
	}

}
