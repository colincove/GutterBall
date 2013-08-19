package threads;

import gameControllers.Game;
import infoHolders.UpdateInfo;

import java.util.Vector;

import droidControllers.SwingActivity;

import Components.AbstractComponent;
import Components.AbstractGameComponent;
import Components.interfaces.IDrawableComponent;

public class GameThread extends DrawThread {
	public static final String threadName="GameThread";
	private BufferedList<AbstractGameComponent> updateList;
	public GameThread(BufferedList<IDrawableComponent> drawList,BufferedList<AbstractGameComponent> updateList, SwingActivity activity) {
		super(drawList, activity);
		this.updateList=updateList;
	}
	public GameThread(BufferedList<IDrawableComponent> drawList,BufferedList<AbstractGameComponent> updateList, SwingActivity activity,ThreadGroup group) {
		super(drawList,activity,group);
		this.updateList=updateList;
		// TODO Auto-generated constructor stub
	}
	public GameThread(BufferedList<IDrawableComponent> drawList,BufferedList<AbstractGameComponent> updateList, SwingActivity activity,ThreadGroup group, long stackSize) {
		super(drawList, activity,group, stackSize);
		this.updateList=updateList;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void tick(TickInfo tickInfo) {
		UpdateInfo  info = new UpdateInfo(tickInfo);
		for(AbstractGameComponent comp : updateList){
			comp.update(info);
		}
		updateList.clearBuffer();
		super.tick(tickInfo);
		for(AbstractGameComponent comp : updateList){
			if(comp.getMarkDestroy()){
				comp.destroy();
			}
		}
	}
}
