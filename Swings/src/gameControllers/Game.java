package gameControllers;

import gameControllers.Levels.Barrel;
import gameControllers.Levels.BunkBedLevel;
import gameControllers.Levels.CondoConundrum;
import gameControllers.Levels.Eff;
import gameControllers.Levels.HiddenDragonLevel;
import gameControllers.Levels.Level;
import gameControllers.Levels.PiggyBack;
import gameControllers.Levels.PingPong;
import gameControllers.Levels.Plinko;
import gameControllers.Levels.RobotDanceLevel;
import gameControllers.Levels.ThrowLevel;
import gameControllers.Levels.ThwompLevel;
import infoHolders.UpdateInfo;

import java.util.Iterator;
import java.util.Vector;

import org.jbox2d.dynamics.Body;

import com.example.swings.R;

import droidControllers.SwingActivity;

import threads.BufferedList;
import threads.DrawThread;
import threads.GameThread;
import Components.AbstractComponent;
import Components.BodyComponent;
import Components.DrawableGameComponent;
import Components.AbstractGameComponent;
import Components.interfaces.IDrawableComponent;
import Drawing.DebugDraw;
import android.app.Activity;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.SurfaceHolder;

public class Game extends SwingActivity {
	private Level level;
	private Simulation simulation;
	private GameThread gameThread;
	private BufferedList<AbstractGameComponent> gameComponentList;
	private BufferedList<BodyComponent> bodyList;
	private MediaPlayer mp;
	private RadialCollisionController radialCollision;
	private int levelIndex;
	
	private GameSoundPool sp;
	
	private DebugDraw debugDraw;

	
	public Game() {
		super();
		
	}
	public GameSoundPool getSoundPool(){
		return sp;
	}
	public Level getLevel() {
		return level;
	}
	public int getLevelIndex() {
		return levelIndex;
	}
	public Simulation getSimulation() {
		return simulation;
	}
	public RadialCollisionController getRadialCollision(){
		return radialCollision;
	}

	public void addGameComponent(AbstractGameComponent gameComponent) {
		if(gameComponentList==null)return;
		gameComponentList.add(gameComponent);
	}

	public void removeGameComponent(AbstractGameComponent gameComponent) {
		if(gameComponentList==null)return;
		gameComponentList.remove(gameComponent);
	}

	public void addBodyComponent(BodyComponent bodyComponent) {
		if(bodyList==null)return;
		bodyList.add(bodyComponent);
	}

	public void removeBodyComponent(BodyComponent bodyComponent) {
		if(bodyList==null)return;
		bodyList.remove(bodyComponent);
	}

	protected void onStart() {
		super.onStart();
		
		//rest of sounds goes here
	}
	protected void onResume(){
		super.onRestart();
		gameThread = new GameThread(drawList, gameComponentList, this);
		gameThread.setRunning(true);
		gameThread.start();
		
		mp.start();
	}
	protected void onPause(){
		super.onPause();
		mp.pause();
		gameThread.setRunning(false);
	}
protected void onRestart(){
	super.onRestart();
}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, new GameView(this));
		if(level==null){
			
			
		Bundle bundle = getIntent().getExtras();
		levelIndex=bundle.getInt("level");
		gameComponentList = new BufferedList<AbstractGameComponent>();
		bodyList = new BodyList<BodyComponent>();
		simulation = new Simulation(this, bodyList);
		debugDraw = new DebugDraw(this, bodyList);
		radialCollision=new RadialCollisionController(this);
		switch(levelIndex)
		{
		case 1:
			level = new PingPong(this);
			break;
		case 2:
			level = new ThwompLevel(this);
			break;
		case 3:
			level = new BunkBedLevel(this);
			break;
		case 4:
			level = new HiddenDragonLevel(this);
			break;
		case 5:
			level = new CondoConundrum(this);
			break;
		case 6:
			level = new PiggyBack(this);
			break;
		case 7:
			level = new Eff(this);
			break;
		case 8:
			level = new RobotDanceLevel(this);
			break;
		case 9:
			level = new Barrel(this);
			break;
			default:
				level  = new Plinko(this); 
		}
		scaler = new Point(simulation.getWorldSize().x,
				simulation.getWorldSize().y);
		}
		
		inputList.clearBuffer();
		
		//start sound systems;
		sp = new GameSoundPool(this, AudioManager.STREAM_MUSIC, 0);
		mp = MediaPlayer.create(this, R.raw.forest);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		super.surfaceCreated(holder);
		gameThread.setView(view);
	}

	protected void onStop() {
		super.onStop();
		mp.stop();
	}

	protected void onDestroy() {
		super.onDestroy();
		level.destroy();
		simulation.destroy();
		radialCollision.destroy();
		mp.release();
		
		radialCollision=null;
		gameThread = null;
		level = null;
		simulation = null;
		mp=null;
	}

}

