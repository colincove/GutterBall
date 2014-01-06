package gameControllers.Levels;

import java.util.ArrayList;
import java.util.List;

import droidControllers.GutterBallApp;

import android.graphics.drawable.BitmapDrawable;

import Components.BodyComponent;
import gameControllers.Game;
import gameObjects.HitSparks;
import gameObjects.Launcher;
import gameObjects.Portal;
import gameObjects.Portal.IObtainedCallback;

public abstract class ThrowLevel extends Level implements IObtainedCallback {
private Launcher launcher;
private List<Portal> portalList;
private HitSparks sparks;
private GutterBallApp app;
	public ThrowLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		// TODO Auto-generated constructor stub
		launcher = new Launcher(game, 15, 46);
		portalList =new ArrayList<Portal>();
		app =(GutterBallApp) game.getApplicationContext();
		
	}
	public ThrowLevel(Game game, BodyComponent geometry, BitmapDrawable bgBitmap) {
		super(game, geometry, bgBitmap);
		// TODO Auto-generated constructor stub
		launcher = new Launcher(game, 15, 46);
		portalList =new ArrayList<Portal>();
		sparks = new HitSparks(game);
	
	}
	@Override
	public void destroy(){
		for(Portal p : portalList)
		{
			if(!p.destroyed())p.destroy();
		}
		launcher.destroy();
		launcher=null;
		super.destroy();
	}
	public void portalObtained(Portal portal){
		portalList.remove(portal);
		if(portalList.size()==0){
			//have completed the level
			app.getLevelManager().completeLevel(game.getLevelIndex()-1);
			game.finish();
		}
	}
	protected void createPortal(float x, float y)
	{
		Portal portal = new Portal(game, x, y);
		portal.setCallback(this);
		portalList.add(portal);
	}

}
