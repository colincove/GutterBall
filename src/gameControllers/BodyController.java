package gameControllers;

import infoHolders.UpdateInfo;
import Components.AbstractGameComponent;
import Components.BodyComponent;

public class BodyController extends AbstractGameComponent {
	protected BodyComponent bodyComp;
	public BodyController(BodyComponent bodyComp, Game game){
		super(game);
		this.bodyComp=bodyComp;
	}
	@Override
	public void update(UpdateInfo updateInfo) {
		// TODO Auto-generated method stub
		
	}
}
