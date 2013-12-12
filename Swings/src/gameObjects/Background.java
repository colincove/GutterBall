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
	private boolean fillCanvas=true;
	public Background(Game activity) {
		this(activity, (BitmapDrawable) activity.getResources().getDrawable(R.drawable.background));
	}
	public Background(Game activity, BitmapDrawable bg) {
		this(activity,bg, -1);
	}
	public Background(Game activity, BitmapDrawable bg, boolean fillCanvas) {
		this(activity,bg, -1, fillCanvas);
	}
	public Background(Game activity, BitmapDrawable bg, int drawOrder) {
		this(activity, bg, drawOrder, true);
	}
	public Background(Game activity, BitmapDrawable bg, int drawOrder, boolean fillCanvas) {
		super(activity, drawOrder);
		// TODO Auto-generated constructor stub
		this.bg=bg;
		paint=new Paint();
		paint.setARGB(255, 255, 0, 0);
		bgBounds=bg.getBounds();
	this.game=game;
	this.fillCanvas=fillCanvas;
	}
	
	
	public void draw(DrawInfo info){
		super.draw(info);
		
		if(fillCanvas)	info.getCanvas().drawARGB(255, 159, 184, 58);
	
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
