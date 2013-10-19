package Drawing;

import org.jbox2d.common.Vec2;

import com.example.swings.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class PhotoButton extends Button {
	private BitmapDrawable photo;
	private int paddingBottom;
	private int paddingTop;
	private int paddingRight;
	private int paddingLeft;
	public PhotoButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public PhotoButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		handleAttrs(context,attrs);
	}

	public PhotoButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		handleAttrs(context,attrs);
	}
	private void handleAttrs(Context context,AttributeSet attrs){
		//TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.PhotoButton, 0,0);
		TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.PhotoButton);
		try{
			int photoRes=a.getResourceId(R.styleable.PhotoButton_photo, -1);
			setPhotoRes(photoRes);
		} finally{
			a.recycle();
		}
	}
	public void draw(Canvas c){
		super.draw(c);
		paddingBottom=getCompoundPaddingBottom();
		paddingTop=getCompoundPaddingTop();
		paddingRight=getCompoundPaddingRight();
		paddingLeft=getCompoundPaddingLeft();
		if(photo!=null){
			Rect des = new Rect();
			des.set(paddingLeft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom);
			photo.setBounds(des);
			photo.draw(c);
		}
	}
	public void setPhotoRes(int res){
		if(res==-1)return;
		setPhotoRes(this.getContext().getResources().getDrawable(res));
	}
	public void setPhotoRes(Drawable drawable){
		if(drawable==null)return;
		setPhotoRes((BitmapDrawable) drawable);
	}
	public void setPhotoRes(BitmapDrawable bitmap){
		if(bitmap==null)return;
		photo=bitmap;
		this.invalidate();
	}
}
