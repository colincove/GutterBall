package Components;

import java.util.Vector;

import gameControllers.Game;
import gameControllers.Simulation;
import gameObjects.CollisionGroups;
import infoHolders.DrawInfo;
import infoHolders.UpdateInfo;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import android.view.MotionEvent;

import Components.interfaces.IBodyCreationListener;
import Components.interfaces.IBodyTouchCallback;


public class BodyComponent extends DrawableGameComponent {
protected World world;
protected Simulation simulation;
protected Body body;
protected BodyDef bodyDef;
private Vector<CollisionGroups> collisionGroups;
protected boolean drawBody=true;
protected Vector<IBodyCreationListener> bodyCreationCallbackList;
protected Vector<IBodyTouchCallback> touchCallbackList;

	public BodyComponent(Game game) {
		super(game);
		collisionGroups = new Vector<CollisionGroups>();
		touchCallbackList  = new Vector<IBodyTouchCallback>();
		bodyCreationCallbackList = new Vector<IBodyCreationListener>();
		simulation=game.getSimulation();
		world=simulation.getWorld();
		game.addBodyComponent(this);
		bodyDef=new BodyDef();
		bodyDef.position.set(0.0f, 0.0f);
		// TODO Auto-generated constructor stub
	}
	public void addBodyCreationListener(IBodyCreationListener listener)
	{
		bodyCreationCallbackList.add(listener);
	}
	protected void addCollisionGroup(CollisionGroups group){
		collisionGroups.add(group);
	}
	public boolean containsCollisionGroup(CollisionGroups group){
		return collisionGroups.contains(group);
	}
	public void bodyComplete(){
		for (IBodyCreationListener listener : bodyCreationCallbackList)
		{
			listener.bodyCreated(body);
		}
		bodyCreationCallbackList.clear();
	}
public void createBody(){
	createBody(world.createBody(bodyDef));
	}
public void createBody(Body body)
{
	this.body=body;
	body.setUserData(this);
	bodyComplete();
}
	@ Override
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		
	}
	@ Override
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
	}
	public Body getBody(){
		return body;
	}
	public void destroy(){
		game.removeBodyComponent(this);
		if(body!=null){
			world.destroyBody(body);
		}
		super.destroy();
		
	}
	public void addOnBodyTouchCallback(IBodyTouchCallback callback){
		touchCallbackList.add(callback);
	}
public void removeOnBodyTouchCallback(IBodyTouchCallback callback){
	touchCallbackList.remove(callback);
	}
public void emitBodyTouch(MotionEvent event){
	for(IBodyTouchCallback callback : touchCallbackList){
		callback.onBodyTouch(this, event);
	}
}
	public boolean testPoint(Vec2 point){
		if(body!=null){
			Fixture fix = body.getFixtureList();
			while (fix != null) {
				if(fix.testPoint(point))return true;
				fix = fix.getNext();
				}
				
			}
		
		return false;
	}
	@Override
	public float getX(){
		if(body!=null){
			return body.getWorldCenter().x;
		}else{
			return super.getX();
		}
	}
	@Override
	public float getY(){
		if(body!=null){
			return body.getWorldCenter().y;
		}else{
			return super.getX();
		}
	}

	public boolean getDrawBody(){
		return drawBody;
	}

}
