package droidControllers;


import gameControllers.Game;

import com.example.swings.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends SwingActivity {
	public HomeActivity() {
		// TODO Auto-generated constructor stub
	}
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
	}
	public void onLevelsClick(View v)
	{
		// TODO Auto-generated method stub
		Intent a = new Intent(this, LevelSelectActivity.class);
		startActivity(a);
	}
	public void onAboutClick(View v)
	{
		// TODO Auto-generated method stub
	}
}