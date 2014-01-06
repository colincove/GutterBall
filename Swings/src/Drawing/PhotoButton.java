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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class PhotoButton extends Button {
	private BitmapDrawable photo;
	private BitmapDrawable disabledPhoto;
	private BitmapDrawable disabledIcon;
	private BitmapDrawable unlockedPhoto;
	private int paddingBottom;
	private int paddingTop;
	private int paddingRight;
	private int paddingLeft;
	private float heightRatio;
	private boolean photoOnDisabled=true;
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
		TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.PhotoButton);
		try{
			int photoRes=a.getResourceId(R.styleable.PhotoButton_photo, -1);
			int disabledPhotoRes=a.getResourceId(R.styleable.PhotoButton_disabledPhoto, -1);
			int disabledIconRes=a.getResourceId(R.styleable.PhotoButton_disabledIcon, -1);
			int unlockedIconRes=a.getResourceId(R.styleable.PhotoButton_unlockedPhoto, -1);
			photoOnDisabled=a.getBoolean(R.styleable.PhotoButton_photoOnDisabled, true);
			heightRatio = a.getFloat(R.styleable.PhotoButton_heightRatio, 1.0f);
			setEnabled(!a.getBoolean(R.styleable.PhotoButton_disabled, false));
			setPhotoRes(photoRes);
			setUnlockedRes(unlockedIconRes);
			setDisabledPhotoRes(disabledPhotoRes);
			setDisabledIcon(disabledIconRes);
		} finally{
			a.recycle();
		}
		if(heightRatio!=1.0f){
			//this.setLayoutParams(new LayoutParams(this.getWidth(), (int)(this.getWidth()*heightRatio)));
			//this.setLayoutParams(getLayoutParams().)
			//this.setHeight((int)(this.getWidth()*heightRatio));
		}
	}
	public void draw(Canvas c){
		super.draw(c);
		Rect des = new Rect();
		paddingBottom=getCompoundPaddingBottom();
		paddingTop=getCompoundPaddingTop();
		paddingRight=getCompoundPaddingRight();
		paddingLeft=getCompoundPaddingLeft();
		if(photo!=null){
			if(!this.isEnabled() && !photoOnDisabled){
				
			}else{
				des.set(paddingLeft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom);
				photo.setBounds(des);
				photo.draw(c);	
			}
			
		}
		if(!this.isEnabled()){
			if(disabledPhoto!=null){
				des.set(paddingLeft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom);
				disabledPhoto.setBounds(des);
				disabledPhoto.draw(c);	
			}
			if(this.disabledIcon!=null){
				float scale=1f;
				int left, right, top, bottom;
				
				if(getWidth()<disabledIcon.getIntrinsicWidth()){
					scale=(float)getWidth()/(float)disabledIcon.getIntrinsicWidth();
				}
				if(getHeight()<disabledIcon.getIntrinsicHeight()){
					if(scale>(float)getHeight()/(float)disabledIcon.getIntrinsicHeight()){
						scale=(float)getHeight()/(float)disabledIcon.getIntrinsicHeight();
					}
				}
				left=(int)(getWidth()/2-(disabledIcon.getIntrinsicWidth()*scale)/2);
				right=(int)(getWidth()/2+(disabledIcon.getIntrinsicWidth()*scale)/2);
				top=(int)(getHeight()/2-(disabledIcon.getIntrinsicHeight()*scale)/2);
				bottom=(int)(getHeight()/2+(disabledIcon.getIntrinsicHeight()*scale)/2);
				des.set(left,top,right,bottom);
				disabledIcon.setBounds(des);
				disabledIcon.draw(c);
			}
		}
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 int width = getMeasuredWidth();
		    int height = getMeasuredHeight();
		    int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
		    int heigthWithoutPadding = height - getPaddingTop() - getPaddingBottom();
		 
		    int maxWidth = (int) (heigthWithoutPadding * heightRatio);
		    int maxHeight = (int) (widthWithoutPadding * heightRatio);
		    if (widthWithoutPadding  < maxWidth) {
		        width = maxWidth + getPaddingLeft() + getPaddingRight();
		    } else {
		        height = maxHeight + getPaddingTop() + getPaddingBottom();
		    }
		 
		    setMeasuredDimension(width, height);
	}
	public void setDisabledPhotoRes(int res){
		if(res==-1)return;
		setDisabledPhotoRes(this.getContext().getResources().getDrawable(res));
	}
	public void setDisabledPhotoRes(Drawable drawable){
		if(drawable==null)return;
		setDisabledPhotoRes((BitmapDrawable) drawable);
	}
	public void setDisabledPhotoRes(BitmapDrawable bitmap){
		if(bitmap==null)return;
		disabledPhoto=bitmap;
		this.invalidate();
	}
	public BitmapDrawable getDisabledPhoto(){
		return disabledPhoto;
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
	public BitmapDrawable getPhoto(){
		return photo;
	}
	public void setUnlockedRes(int res){
		if(res==-1)return;
		setUnlockedRes(this.getContext().getResources().getDrawable(res));
	}
	public void setUnlockedRes(Drawable drawable){
		if(drawable==null)return;
		setUnlockedRes((BitmapDrawable) drawable);
	}
	public void setUnlockedRes(BitmapDrawable bitmap){
		if(bitmap==null)return;
		unlockedPhoto=bitmap;
		this.invalidate();
	}
	public BitmapDrawable getUnlockedPhoto(){
		return unlockedPhoto;
	}
	public void setDisabledIcon(int res){
		if(res==-1)return;
		setDisabledIcon(this.getContext().getResources().getDrawable(res));
	}
	public void setDisabledIcon(Drawable drawable){
		if(drawable==null)return;
		setDisabledIcon((BitmapDrawable) drawable);
	}
	public void setDisabledIcon(BitmapDrawable bitmap){
		if(bitmap==null)return;
		disabledIcon=bitmap;
		this.invalidate();
	}
	
}
