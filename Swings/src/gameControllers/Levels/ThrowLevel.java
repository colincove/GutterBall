package gameControllers.Levels;

import java.util.ArrayList;
import java.util.List;

import droidControllers.GutterBallApp;

import android.graphics.drawable.BitmapDrawable;

import Components.BodyComponent;
import gameControllers.Game;
import gameControllers.levelManagment.AppleLevelManager;
import gameObjects.HitSparks;
import gameObjects.Portal;
import gameObjects.Portal.IObtainedCallback;
import gameObjects.launcher.FingerAnimation;
import gameObjects.launcher.Launcher;
import gameObjects.launcher.LauncherAnimation;
import gameObjects.launcher.LauncherTouchCircle;

public abstract class ThrowLevel extends Level implements IObtainedCallback {
	private LauncherTouchCircle launcherCircle;
private Launcher launcher;
private LauncherAnimation launcherAnim;
private List<Portal> portalList;
private HitSparks sparks;
private GutterBallApp app;
private int appleCount=0;
	public ThrowLevel(Game game, BodyComponent geometry) {
		super(game, geometry);
		initialize();
	}
	public ThrowLevel(Game game, BodyComponent geometry, BitmapDrawable bgBitmap) {
		super(game, geometry, bgBitmap);
		initialize();
	}
	private void initialize(){
		launcher = new Launcher(game, 15, 46);
		launcher.setDelay(1000);
		portalList =new ArrayList<Portal>();
		sparks = new HitSparks(game);
		app =(GutterBallApp) game.getApplicationContext();
		launcherCircle = new LauncherTouchCircle(game, launcher);
		launcherAnim = new LauncherAnimation(game, launcher);
		FingerAnimation fa = new FingerAnimation(game, launcher);
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
		game.getSoundPool().play(game.getSoundPool().getPool().squish, 0.99f, 0.99f, 1, 0, 1.0f);
		if(portalList.size()==0){
			((AppleLevelManager)(game.getGutterApp().getLevelManager())).addApple(appleCount);
			//have completed the level
			app.getLevelManager().completeLevel(game.getLevelIndex()-1);
			game.setResult(game.RESULT_OK, null);
			//finish();
			game.finish();
		}
	}
	protected void createPortal(float x, float y)
	{
		appleCount++;
		Portal portal = new Portal(game, x, y);
		portal.setCallback(this);
		portalList.add(portal);
	}

}
