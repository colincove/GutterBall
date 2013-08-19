package Drawing;

import java.util.Vector;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import threads.BufferedList;

import android.graphics.Paint;

import gameControllers.Game;
import infoHolders.DrawInfo;
import droidControllers.SwingActivity;
import Components.BodyComponent;
import Components.DrawableComponent;
import Components.DrawableGameComponent;

public class DebugDraw extends DrawableGameComponent {
	private BufferedList<BodyComponent> list;
	private Paint paint;
private boolean overrideDrawBody;
	public DebugDraw(Game activity, BufferedList<BodyComponent> list) {
		super(activity);
		// TODO Auto-generated constructor stub
		this.list = list;
		paint = new Paint();
		paint.setARGB(255, 255, 0, 0);
		overrideDrawBody=false;
		
	}
	public DebugDraw(Game activity, BufferedList<BodyComponent> list, boolean overrideDrawBody) {
		this(activity, list);
		// TODO Auto-generated constructor stub
		this.overrideDrawBody=overrideDrawBody;
	}

	public void draw(DrawInfo info) {
		super.draw(info);
		for (BodyComponent bodyComp : list) {
			if(bodyComp.getDrawBody()){
			Body body = bodyComp.getBody();
			
			if(body==null)return;
			Fixture fix = body.getFixtureList();
			Vec2 center = body.getWorldCenter();
			while (fix != null) {
				if (fix.getType() == ShapeType.CIRCLE) {
					CircleShape shape = (CircleShape) fix.getShape();
					info.getCanvas().drawCircle(gameView.toScreenX(center.x),
							gameView.toScreenY(center.y),
							gameView.toScreen(shape.m_radius), paint);
				} else if (fix.getType() == ShapeType.POLYGON) {
					info.getCanvas().save();
					PolygonShape shape = (PolygonShape) fix.getShape();
					Vec2[] vecs = shape.getVertices();
					
					info.getCanvas().rotate((float)(
							body.getAngle()/(Math.PI/180)),
							gameView.toScreenX(center.x),
							gameView.toScreenY(center.y));
					
					int count = shape.getVertexCount();
					for (int i = 0; i < count - 1; i++) {
						info.getCanvas().drawLine(
								gameView.toScreenX(vecs[i].x + center.x),
								gameView.toScreenY(vecs[i].y + center.y),
								gameView.toScreenX(vecs[i + 1].x + center.x),
								gameView.toScreenY(vecs[i + 1].y + center.y),
								paint);
					}
					info.getCanvas().drawLine(
							gameView.toScreenX(vecs[0].x + center.x),
							gameView.toScreenY(vecs[0].y + center.y),
							gameView.toScreenX(vecs[count - 1].x + center.x),
							gameView.toScreenY(vecs[count - 1].y + center.y),
							paint);
					info.getCanvas().restore();
				}
				fix = fix.getNext();
			}
		}
		}
	}

}
