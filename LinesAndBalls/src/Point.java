import java.io.*;


public class Point implements Serializable{

	private double x;
	private double y;
	public static double closestDistFrmLastOperation;
	public static final Point X_AXIS=new Point(1,0);
	public static final Point Y_AXIS=new Point(0,1);
	
	public Point(){
		x=0;
		y=0;
	}
	public Point(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public Point clone(){
		return new Point(x,y);
	}
	public Point add(Point b){
		return new Point(x+b.x,y+b.y);
	}
	public Point subtract(Point b){
		return new Point(x-b.x,y-b.y);
	}
	public void normalize(){
		double length=Math.sqrt(x*x+y*y);
		if(length==0){
			return;
		}else if(length<0){
			scale(-1/length);
		}else{
			scale(1/length);
		}
	}
	public void scale(double i){
		x=x*i;
		y=y*i;
	}
	public int closestPoint(Point[] array){
		int closestIndex=0;
		double sqDist;
		double lastSqDist=1000000;
		Point difference;
		for(int i=0; i<array.length;i++){
			difference=array[i].subtract(this);
			sqDist=difference.x*difference.x+difference.y*difference.y;
			if(sqDist<lastSqDist){
				lastSqDist=sqDist;
				closestIndex=i;
			}
		}
		return closestIndex;
	}
	public double component(Point p){
		Point tmp=p.clone();
		tmp.normalize();
		return this.dotProduct(tmp);
	}
	public Point projection(Point p){
		Point tmp=p.clone();
		tmp.normalize();
		tmp.scale(this.dotProduct(tmp));
		return tmp;
	}
	public double dotProduct(Point b){
		return x*b.x+y*b.y;
	}
//	public boolean intersects(Block b){
//		return getX()>b.getPos().getX()&&getX()<b.getPos().getX()+b.getWidth()&&getY()>b.getPos().getY()&&getY()<b.getPos().getY()+b.getHeight();
//	}
//	public boolean intersects(Circle c){
//		return (c.getRadius()*c.getRadius()>this.subtract(c.getCen()).getSqLength());
//	}
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
	public Point rightPerpendicular(){
		return new Point(-y,x);
	}
	public Point leftPerpendicular(){
		return new Point(y,-x);
	}
	public double getSqLength(){
		return x*x+y*y;
	}
	public double getLength(){
		return Math.sqrt(x*x+y*y);
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public String toString(){
		return "<"+x+", "+y+">";
	}
}
