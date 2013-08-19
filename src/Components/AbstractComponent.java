package Components;

import droidControllers.SwingActivity;
import infoHolders.UpdateInfo;
import android.app.Activity;
import android.graphics.Point;
import android.view.SurfaceView;

public abstract class AbstractComponent {
	private boolean destroyed;
	protected SwingActivity activity;
	protected SurfaceView view;
	private float x=0;
	private float y=0;
	private boolean markedDestroy;
	private Point pt;
public AbstractComponent(SwingActivity activity){
	pt=new Point();
	this.activity=activity;
	view=activity.getView();
	activity.addComponent(this);
}
public SwingActivity getActivity(){
	return activity;
}
public void destroy() {
	activity.removeComponent(this);
	activity=null;
	destroyed=true;
}
public void setX(float value){
	x=value;
	pt.x=(int)value;
}
public void setY(float value){
	y=value;
	pt.y=(int)value;
}
public void setPos(float x, float y){
	setX(x);
	setY(y);
}
public float getX(){
	return x;
}
		public float getY()
		{
			return y;
		}
		public Point getPt(){
			return pt;
		}
	
public boolean destroyed(){
	return destroyed;
	
}
public void markDestroy(){
	markedDestroy=true;
}
public boolean getMarkDestroy(){
	return markedDestroy;
}
}
