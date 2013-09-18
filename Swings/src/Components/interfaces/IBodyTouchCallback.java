package Components.interfaces;

import android.view.MotionEvent;
import Components.BodyComponent;

public interface IBodyTouchCallback {
public void onBodyTouch(BodyComponent bodyComp, MotionEvent event);
}
