
/*
 * This is the basis of the physics engine. 
 * Current acceleration and velocity are values which can be retrieved. Frequency of updates increases physics accuracy.
 */
public class PhysicsObject extends GameObject{

	//===========Variables=============
	protected Point vel=new Point();
	protected Point acc=new Point();
	protected double mass;
	protected Point realPos;
	protected Point viewPos1;
	protected Point lastAccel=new Point();
	protected int tps;
	
	//==============Constructors============
	public PhysicsObject() {
		
	}
	
	public PhysicsObject(int x, int y, int width, int height, double mass,int physicsTicksPerSec) {
		super(x,y,width,height);
		this.tps=physicsTicksPerSec;
		this.mass=mass;
	}
	public PhysicsObject(int x, int y, double mass, int physicsTicksPerSec){
		super(x,y);
		this.tps=physicsTicksPerSec;
		this.mass=mass;
	}
	
	//================Methods=============
	
	public void move(){
		
		setPos(new Point(pos.getX()+vel.getX()/tps,pos.getY()+vel.getY()/tps));
	}
	/*
	 * This method takes all the accelerations accumulated from differnt forces and adds them to the 
	 */
	public void accelerate(){
		vel=vel.add(acc);
		acc=new Point();
	}
	public void force(Point frc){
		frc.scale(1/mass);
		acc=acc.add(frc);
	}
	public double getMass(){
		return mass;
	}
	public Point getViewPos1(){
		return viewPos1;
	}
	public void setViewPos1(Point p){
		viewPos1=p;
	}
	public Point getVel(){
		return vel;
	}
	public Point getAcc(){
		return acc;
	}
}
