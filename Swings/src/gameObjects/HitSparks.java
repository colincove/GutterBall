package gameObjects;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.ManifoldPoint;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.contacts.ContactEdge;

import android.graphics.Color;
import android.graphics.Paint;

import infoHolders.DrawInfo;
import infoHolders.UpdateInfo;
import gameControllers.Game;
import Components.BodyComponent;
import Components.DrawableGameComponent;
import Components.interfaces.IContactCallback;

public class HitSparks extends DrawableGameComponent implements IContactCallback {
	private Spark[] sparks;
	private float fric;
	private Body body;
	private ContactEdge edge;
	private Contact contact;
	private Manifold manifold;
	private WorldManifold worldManifold;
	private Paint paint;
	private int sparkLimit=100;
	private Vec2 vel1;
	private Vec2 vel2;
	public HitSparks(Game game) {
		super(game);
		sparks=new Spark[sparkLimit];
		for(int i=0;i<sparkLimit;i++){
			sparks[i] = new Spark();
		}
		game.getSimulation().addPostSolveListener(this);
		fric=0.5f;
		paint=new Paint();
		worldManifold=new WorldManifold();
		paint.setARGB(255, 253, 252, 241);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int drawOrder(){
		return 1;
	}
	@Override
	public void update(UpdateInfo updateInfo){
		super.update(updateInfo);
		for(Spark spark : sparks){
			if(spark.active){
				spark.x+=spark.vx;
				spark.y+=spark.vy;
				float totalVel = (float)Math.abs(spark.vx)+(float)Math.abs(spark.vy);
				spark.vx=spark.vx*fric;
				spark.vy=spark.vy*fric+0.08f*totalVel;
				if(spark.vy<0.005&&spark.vx<0.005){
					spark.active=false;
				}
			}
		}
		
	}
	@Override
	public void draw(DrawInfo drawInfo){
		super.draw(drawInfo);
		for(Spark spark : sparks){
			if(spark.active){
				drawInfo.getCanvas().drawLine(
						gameView.toScreenX(spark.x-spark.vx),
						gameView.toScreenY(spark.y-spark.vy), 
						gameView.toScreenX(spark.x), 
						gameView.toScreenY(spark.y), 
						paint);
			}
		}
		
	}
	@Override
	public void onBeginContact(Contact c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onEndContact(Contact c) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPreSolve(Contact c, Manifold manifold) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPostSolve(Contact c, ContactImpulse cImpulse) {
		// TODO Auto-generated method stub
		//cImpulse.
		if(c.isTouching()){
			manifold=c.getManifold();
			c.getWorldManifold(worldManifold);
				int count=0;
				//pt.localPoint.
				for(Spark spark : sparks){
					if(!spark.active){
						spark.active=true;
						//spark.x=pt.localPoint.x;
						//spark.y=pt.localPoint.y;
						spark.x=worldManifold.points[0].x;
						spark.y=worldManifold.points[0].y;
						//spark.vx=2*worldManifold.normal.x;
						//spark.vy=2*worldManifold.normal.y;
						
						vel1 = c.getFixtureA().getBody().getLinearVelocityFromWorldPoint(worldManifold.points[0]);
						vel2 = c.getFixtureB().getBody().getLinearVelocityFromWorldPoint(worldManifold.points[0]);
						
						
						
						spark.vx=vel2.x/(15+(float)Math.random()*5f);
						spark.vy=vel2.y/(15+(float)Math.random()*5f);
						if(++count>5){
						break;
						}
					}
				}
		}
	}
}
