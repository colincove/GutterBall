package droidControllers;

import gameControllers.levelManagment.AppleLevelManager;
import gameControllers.levelManagment.LazyLevelManager;
import gameControllers.levelManagment.LevelManager;
import android.app.Application;

public class GutterBallApp extends Application {
	private LevelManager levelManager;
	public GutterBallApp() {
		
	}
	public void onCreate(){
		super.onCreate();
		if(levelManager==null)
		{
			//levelManager = new LazyLevelManager(this,17);
			levelManager = new AppleLevelManager(this, 17,new int[]{5,5, 5, 5, 5,10,10,10,10,20,20,20,20,20,30,30,30,30});
		}
		
	}
	public LevelManager getLevelManager(){
		return levelManager;
	}
}
