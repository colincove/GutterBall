package droidControllers;

import gameControllers.LazyLevelManager;
import gameControllers.LevelManager;
import android.app.Application;

public class GutterBallApp extends Application {
	private LevelManager levelManager;
	public GutterBallApp() {
		
	}
	public void onCreate(){
		super.onCreate();
		if(levelManager==null)
		{
			levelManager = new LazyLevelManager(this,17);
		}
		
	}
	public LevelManager getLevelManager(){
		return levelManager;
	}
}
