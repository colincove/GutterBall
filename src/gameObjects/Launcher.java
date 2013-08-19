package gameObjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import gameControllers.Game;
import infoHolders.DrawInfo;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import droidControllers.SwingActivity;
import Components.DrawableComponent;
import Components.DrawableGameComponent;
import Components.interfaces.IBodyCreationListener;
import Components.interfaces.IUserInputComponent;

public class Launcher extends DrawableGameComponent implements IUserInputComponent{

	private Paint paint;
	private Paint pullPaint;
	private int r=1;
	private boolean isPulling;
	private Point fingerPt;
	private float fingX;
	private float fingY;
	public Launcher(Game activity, float x, float y) {
		super(activity);
		// TODO Auto-generated constructor stub
	setPos(x, y);
	fingerPt=new Point();
	pullPaint=new Paint();
	paint  = new Paint();
	paint.setARGB(255,255, 248, 206);
	pullPaint.setARGB(255, 253, 252, 241);
	activity.addInputComponent(this);
	}
	@Override
	public void draw(DrawInfo info){
		super.draw(info);
		
		//Body
		info.getCanvas().drawCircle(gameView.toScreenX(getX()),
				gameView.toScreenY(getY()),
				gameView.toScreen(r), paint);
		//Pull Line
		if(isPulling){
			info.getCanvas().drawLine(
					fingerPt.x,
					fingerPt.y,
					gameView.toScreenX(getX()),
					gameView.toScreenY(getY()),
					pullPaint);
		}
		
	}
	@Override
	public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
		case MotionEvent.ACTION_UP:
			if(isPulling)stopPull(event);
			
			break;
			case MotionEvent.ACTION_DOWN:
				if(getCircleHit(gameView.toWorldX(event.getRawX()),gameView.toWorldY(event.getRawY()), 3, getX(), getY(), r)){
					doPull(event);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if(isPulling){
				doPull(event);
				}
				break;
		}
	}
	private void stopPull(MotionEvent event){
		//Spawn Actor
		Actor actor = new Actor(game, new Vec2(getX(), getY()));
		//Launch Actor
		float dx  = fingX-gameView.toScreenX(getX());
		float dy  = fingY-gameView.toScreenY(getY());
		double a= Math.atan2(dy,  dx);
		double d= Math.sqrt(dx*dx+dy*dy);
		
		double cos = Math.cos(a);
		double sin = Math.sin(a);
		
		Vec2 force = new Vec2((float)(cos*(d*10)), (float)(sin*(d*10)));
		
		BodyLauncher launcher = new BodyLauncher(force, force);
		
		actor.addBodyCreationListener(launcher);
		
		isPulling=false;
	}
	private void doPull(MotionEvent event)
	{
		isPulling=true;
		fingX=event.getRawX();
		fingY=event.getRawY();
		fingerPt.set((int)event.getRawX(), (int)event.getRawY());
	}

	@Override
	public void onLongClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreateContextMenu() {
		// TODO Auto-generated method stub

	}
	private boolean getCircleHit(float x1, float y1,float r1, float x2, float y2, float r2){
		if(getDist(x1,y1,x2,y2)<r1+r2)return true;
		return false;
	}
	private float getDist(float x1, float y1, float x2, float y2){
		float dx = x2-x1;
		float dy = y2-y1;
		return (float) Math.sqrt(dx*dx+dy*dy);
		
	}
	@Override
	public void destroy(){
		super.destroy();
		paint=null;
		pullPaint=null;
		fingerPt=null;
	}

		private class BodyLauncher implements IBodyCreationListener{
			private Vec2 force;
			private Vec2 point;
			public BodyLauncher(Vec2 force, Vec2 point){
				this.force=force;
				this.point=point;
			}

			@Override
			public void bodyCreated(Body body)
			{
				// TODO Auto-generated method stub
				body.setLinearVelocity(force);
				//body.applyForce(force, point);
			}
		}
		
}
