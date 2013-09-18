package gameControllers;
import infoHolders.UpdateInfo;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import threads.BufferedList;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.IInterface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import Components.AbstractComponent;
import Components.AbstractGameComponent;
import Components.BodyComponent;
import Components.interfaces.IUserInputComponent;

public class Simulation extends AbstractGameComponent implements IUserInputComponent, QueryCallback {
	private World world;
	private Vec2 gravity;
	private float timeStep;
	private int velocityIterations;
	private int positionIterations;
	private Point worldSize;
	private BufferedList<BodyComponent> bodyList;
	private MotionEvent lastEvent;
public Simulation(Game game, BufferedList<BodyComponent> bodyList){
	super(game);
	gravity = new Vec2(0.0f,15.0f);
	world = new World(gravity);
	timeStep = 1.0f/30.0f;
     velocityIterations = 1;
     positionIterations = 1;
     worldSize=new Point(30,48);
     this.bodyList=bodyList;
     game.addInputComponent(this);
}
public Point getWorldSize(){
	return worldSize;
}
public World getWorld(){
	return world;
}
public Vec2 getGravity(){
	return gravity;
}
@Override
public void destroy() {
	// TODO Auto-generated method stub
	super.destroy();
}
@Override
public void update(UpdateInfo updateInfo) {
	// TODO Auto-generated method stub
	world.step(timeStep, velocityIterations, positionIterations);
	//world.step(updateInfo.getDeltaTime()/100, velocityIterations, positionIterations);
	bodyList.clearBuffer();
}
@Override
public void onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	
}
@Override
public void onTouch(View v, MotionEvent event) {
	// TODO Auto-generated method stub
	if(event.getAction()==MotionEvent.ACTION_DOWN){
	lastEvent=event;
	world.queryAABB(this, new AABB(
			new Vec2(gameView.toWorldX(event.getRawX())-2,gameView.toWorldY(event.getRawY())-2),
			new Vec2(gameView.toWorldX(event.getRawX())+2,gameView.toWorldY(event.getRawY())+2)
			));
	lastEvent=event;
	}
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
@Override
public boolean reportFixture(Fixture fix) {
	// TODO Auto-generated method stub
	BodyComponent  userData=(BodyComponent) fix.getBody().getUserData();
	userData.emitBodyTouch(lastEvent);
	return false;
}
}
