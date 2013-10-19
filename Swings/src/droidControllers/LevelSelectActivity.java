package droidControllers;

import gameControllers.Game;

import com.example.swings.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LevelSelectActivity extends SwingActivity {
private int selectedLevel=0;
	public LevelSelectActivity() {
		// TODO Auto-generated constructor stub
		super();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select);
	}
	public void selectLevel(View view)
	{
		switch(view.getId()){
		case R.id.level1:
			selectedLevel=1;
			break;
		/*case R.id.level2:
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
			break;*/
		}		
		playLevel();
	}
	private void playLevel()
	{
		Intent game = new Intent(this, Game.class);
		game.putExtra("level", selectedLevel);
		startActivity(game);
	}

}
