import java.awt.*;


public class Ball extends PhysicsObject{

	private boolean hitTwo=false;
	private Point dimensions=new Point(40,50);
	public Ball(){
	}
	public Ball(Point pos, int radius, double mass,int physicsTicksPerSec){
		this((int)pos.getX(),(int)pos.getY(), radius, mass,physicsTicksPerSec);
	}
	public Ball(int x, int y, int radius, double mass,int physicsTicksPerSec) {
		super(x,y,radius,radius,mass,physicsTicksPerSec);
	}
	public void draw(Graphics g){
		g.fillOval((int)pos.getX(),(int)pos.getY(),2*width,2*width);
//		g.drawLine((int)pos.getX(),(int)pos.getY(),(int)(pos.getX()+vel.getX()),(int)(pos.getY()+vel.getY()));
	}
	public boolean collides(Line l){
		int closestPIndex=cen.closestPoint(l.p);
		Point relativeCen=cen.subtract(l.p[closestPIndex]);
		Point lineAxis=l.p[(closestPIndex+1)%2].subtract(l.p[closestPIndex]);
		lineAxis.normalize();
		double currentOverlap;
		Point currentAxis;
		currentAxis=lineAxis.rightPerpendicular();
		currentOverlap=width-relativeCen.dotProduct(currentAxis);
		Point leastAxis=currentAxis;
		double leastOverlap=currentOverlap;
		for(int i=0;i<3;i++){
			switch(i){
			case 0:
				break;
			case 1:
				currentAxis=lineAxis.leftPerpendicular();
				currentOverlap=width-relativeCen.dotProduct(currentAxis);
				break;
			case 2:
				currentAxis=relativeCen.clone();
				currentOverlap=currentAxis.dotProduct(lineAxis)+width;
				break;
			}
			if(currentOverlap<0){
				hitTwo=false;
				return false;
			}else if(currentOverlap<leastOverlap){
				leastOverlap=currentOverlap;
				leastAxis=currentAxis;
			}
		}
		if(hitTwo){
			return false;
		}else{
			hitTwo=true;
		}
		calcCollision(leastAxis);
		return true;
	}
	public void calcCollision(Point axis){
		Point temp=axis.clone();
		if(temp.getX()!=0||temp.getY()!=0){
			temp.normalize();
			double dotProd=vel.dotProduct(temp);
			if(dotProd>3){
				return;
			}
			
			temp.scale(mass*2*Math.abs(dotProd)*0.85);				
			
			force(temp);
		}
	}
}