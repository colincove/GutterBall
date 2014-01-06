package droidControllers;

import gameControllers.Game;
import gameControllers.LazyLevelManager;
import gameControllers.LevelLayoutController;
import gameControllers.LevelManager;

import com.example.swings.R;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelectActivity extends SwingActivity implements android.view.View.OnClickListener {
	private static final String UNLOCKED_STATE="levelsUnlocked"; 
private int selectedLevel=0;
private LevelManager levelManager;
private Button resetLevelsButton;
private LevelLayoutController levelLayoutController;
	public LevelSelectActivity() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select);
		levelManager = ((GutterBallApp)getApplicationContext()).getLevelManager();
		if(levelLayoutController==null){
			levelLayoutController = new LevelLayoutController(this, levelManager);
		}
		
		if(savedInstanceState!=null){
			if(levelManager.getLevelsUnlocked()!=savedInstanceState.getInt(UNLOCKED_STATE)){
				//means that new levels have been unlocked by the level manager
				
			}
		}
		resetLevelsButton = (Button)findViewById(R.id.appOptions);
		resetLevelsButton.setOnClickListener(this);
	}
	protected void onStart(){
		super.onStart();
	}
	protected void onResume(){
		super.onResume();
		levelLayoutController.initialize();
	}
	public void selectLevel(View view)
	{
		switch(view.getId()){
		case R.id.level1:
			selectedLevel=1;
			break;
		case R.id.level2:
			selectedLevel=2;
			break;
		case R.id.level3:
			selectedLevel=3;
			break;
		case R.id.level4:
			selectedLevel=4;
			break;
		case R.id.level5:
			selectedLevel=5;
			break;
		case R.id.level6:
			selectedLevel=6;
			break;
		case R.id.level7:
			selectedLevel=7;
			break;
		case R.id.level8:
			selectedLevel=8;
			break;
		case R.id.level9:
			selectedLevel=9;
			break;
		}	
		playLevel();
	}
	
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putInt(UNLOCKED_STATE, levelManager.getLevelsUnlocked());
	}
	private void playLevel()
	{
		Intent game = new Intent(this, Game.class);
		game.putExtra("level", selectedLevel);
		startActivity(game);
	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(resetLevelsButton==view){
			levelManager.reset();
			levelLayoutController.initialize();
		}
	}

}
