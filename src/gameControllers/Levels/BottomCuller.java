package gameControllers.Levels;

import infoHolders.UpdateInfo;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.ContactEdge;

import gameControllers.Game;
import gameObjects.CollisionGroups;
import Components.BodyComponent;

public class BottomCuller extends BodyComponent {

	public BottomCuller(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}
	@Override
public void createBody(){
	createBody(world.createBody(bodyDef));
}
	public void createBody(Body body)
	{
		 PolygonShape groundBox = new PolygonShape();
		 groundBox.setAsBox(game.getSimulation().getWorldSize().x,1, new Vec2(game.getSimulation().getWorldSize().x/2,game.getSimulation().getWorldSize().y+5),0.0f);
		 body.createFixture(groundBox,0.0f);
		 //
		super.createBody(body);
	}
	@Override
	public void update(UpdateInfo info){
		super.update(info);
		ContactEdge contact = body.getContactList();
		while(contact!=null)
		{
			if(((BodyComponent)contact.other.getUserData()).containsCollisionGroup(CollisionGroups.ACTOR)){
				((BodyComponent)contact.other.getUserData()).markDestroy();
				break;
			}
		contact=contact.next;
		
			
		}
	}
}
