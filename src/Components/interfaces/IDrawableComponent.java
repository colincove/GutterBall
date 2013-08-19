package Components.interfaces;

import java.util.Comparator;

import infoHolders.DrawInfo;

public interface IDrawableComponent {
public void draw(DrawInfo info);
public int drawOrder();
public Comparator drawableComparator=new Comparator<IDrawableComponent>() {
	public int compare(IDrawableComponent one, IDrawableComponent other){
		return one.drawOrder()-other.drawOrder();
	}
};
}
