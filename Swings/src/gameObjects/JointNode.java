package gameObjects;

public class JointNode {
	private JointNode attached;
	public float x;
	public float y;
	public float r;
	private float dx;
	private float dy;
	private float d;
	public float a;
	public JointNode( float x, float y,float r) {
		// TODO Auto-generated constructor stub
		this.r=r;
		this.x=x;
		this.y=y;
	}
	public JointNode(JointNode attached, float x, float y ,float r) {
		// TODO Auto-generated constructor stub
		this(x, y, r);
		this.attached=attached;
	}
	public void update(){
		if(attached!=null){
			dx = attached.x-x;
			dy = attached.y-y;
			d  = (float)Math.sqrt(dx*dx + dy*dy);
			if(d>r){
				a=(float)Math.atan2(dy, dx);
				x+=Math.cos(a)*(d-r);
				y+=Math.sin(a)*(d-r);
			}else if(d<r){
				a=(float)Math.atan2(dy, dx);
				x-=Math.cos(a)*(r-d);
				y-=Math.sin(a)*(r-d);
			}
		}
	}

}
