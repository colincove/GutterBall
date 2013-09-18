package gameObjects;

import gameControllers.Game;
import infoHolders.DrawInfo;

import com.example.swings.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import droidControllers.SwingActivity;
import Components.DrawableComponent;
import Components.DrawableGameComponent;

public class Background extends DrawableGameComponent {
	private BitmapDrawable bg;
	private Rect bgBounds;
	private Paint paint;
	private Game game;
	public Background(Game activity) {
		this(activity, (BitmapDrawable) activity.getResources().getDrawable(R.drawable.background));
	}
	public Background(Game activity, BitmapDrawable bg) {
		super(activity);
		// TODO Auto-generated constructor stub
		this.bg=bg;
		paint=new Paint();
		paint.setARGB(255, 255, 0, 0);
		bgBounds=bg.getBounds();
	this.game=game;
	}
	public void draw(DrawInfo info){
		super.draw(info);
		Rect des = new Rect();
		des.set((int)gameView.toScreenX(0.0f), 
				(int)gameView.toScreenY(0.0f),
				(int)gameView.toScreenX(0.0f)+(int)gameView.toScreen(30.0f),
				(int)gameView.toScreenY(0.0f)+(int)gameView.toScreen(50.0f));
		
		
		
		bg.setBounds(des);
		bg.draw(info.getCanvas());
	}
	@Override
	public int drawOrder() {
		// TODO Auto-generated method stub
		return -1;
	}
	public void destroy(){
		super.destroy();
		paint=null;
		bg=null;
	}
}