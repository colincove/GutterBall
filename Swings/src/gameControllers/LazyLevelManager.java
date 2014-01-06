package gameControllers;

import android.content.Context;
import droidControllers.SwingActivity;

public class LazyLevelManager extends LevelManager {

	public LazyLevelManager(Context context, int numLevels) {
		super(context,numLevels);
		if(getLevelsUnlocked()==0){
			unlockLevel(0);
			unlockLevel(1);
			unlockLevel(2);
		}
	}
	public void completeLevel(int levelIndex){
		super.completeLevel(levelIndex);
		if(getLevelsUnlocked()-getLevelsCompleted()==getLevelsUnlocked()/3){
			if(getLevelsUnlocked()<getNumLevels()){
				for(int i=getLevelsUnlocked();i<getLevelsUnlocked()+3;i++){
					if(i<getNumLevels()){
						unlockLevel(i);
					}
				}
			}
		}
	}

	public void reset(){
		super.reset();
		unlockLevel(0);
		unlockLevel(1);
		unlockLevel(2);
	}
}