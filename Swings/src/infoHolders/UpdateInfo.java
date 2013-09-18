package infoHolders;

import threads.TickInfo;

public class UpdateInfo {
	private TickInfo tickInfo;
public UpdateInfo(TickInfo tickInfo){
	this.tickInfo=tickInfo;
}
public int getDeltaTime(){
	return tickInfo.getDeltaTime();
}
}
