package infoHolders;

import threads.TickInfo;

public class UpdateInfo {
	private TickInfo tickInfo;
	private long time;
public UpdateInfo(TickInfo tickInfo){
	this.tickInfo=tickInfo;
	time = System.currentTimeMillis();
}
public int getDeltaTime(){
	return tickInfo.getDeltaTime();
}
public int getTargetFps(){
	return tickInfo.getTargetFps();
}
public long getTime(){
	return time;
}
}
