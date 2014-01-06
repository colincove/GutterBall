package gameObjects;

import org.jbox2d.common.Vec2;

import com.example.swings.R;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import gameControllers.Game;
import gameControllers.RadialCollider;
import gameControllers.RadialID;
import infoHolders.DrawInfo;
import infoHolders.UpdateInfo;
import droidControllers.SwingActivity;
import Components.DrawableComponent;
import Components.DrawableGameComponent;
import Components.interfaces.IRadialCollider;

public class Portal extends DrawableGameComponent implements IRadialCollider {
private Paint paint;
private BitmapDrawable sphere;
private RadialCollider collider;
private Floater floater;
private IObtainedCallback obtainedCallback;
	public Portal(Game activity, float x, float y) {
		super(activity);
		setPos(x, y);
		floater=new Floater(activity, 2, 0.2f);
		// TODO Auto-generated constructor stub
		paint = new Paint();

		paint.setARGB(255, 107, 255, 107);
		collider = new RadialCollider(activity,this, 2, RadialID.PORTAL);
		sphere=(BitmapDrawable) game.getResources().getDrawable(R.drawable.green_sphere);
		
	}
	@Override
	public void draw(DrawInfo info){
		super.draw(info);
		float x=getX();
		float y=getY()+floater.x/20;
		float r=1;
		/*info.getCanvas().drawCircle(gameView.toScreenX(getX()),
				gameView.toScreenY(getY()),
				gameView.toScreen(1), paint);*/
		Rect des = new Rect();
		des.set((int)gameView.toScreenX(x-r), 
				(int)gameView.toScreenY(y-r),
				(int)gameView.toScreenX(x+r),
				(int)gameView.toScreenY(y+r));
		
		
		
		sphere.setBounds(des);
		sphere.draw(info.getCanvas());
	}
	@Override
	public void radialCollide(RadialCollider other) {
		// TODO Auto-generated method stub
		if(other.getId()==RadialID.ACTOR)
		{
			obtained();
		}
	}
	@Override
	public void destroy(){
		collider.destroy();
	super.destroy();
	}
	public void obtained(){
		if(obtainedCallback!=null){
			obtainedCallback.portalObtained(this);
		}
		destroy();
	}
	@Override
	public void update(UpdateInfo updateInfo){
	super.update(updateInfo);
	}
	public void setCallback(IObtainedCallback callback){
		this.obtainedCallback=callback;
	}

	public interface IObtainedCallback{
		public void portalObtained(Portal portal);
	}
}
