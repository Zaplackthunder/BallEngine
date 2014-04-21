import java.awt.Graphics;


public class Rotatable extends PhysicsObject{

	PhysicsObject[] pieces;
	Point com;
	public Rotatable(PhysicsObject[] pieces){
		this.pieces=pieces;
		com=findCom(pieces);
	}
	
	
	public static Point findCom(PhysicsObject[] masses){
		double tMass=0;
		double x=0;
		double y=0;
		for(int i=0;i<masses.length;i++){
			tMass=tMass+masses[i].getMass();
			x=x+masses[i].getCen().getX()*masses[i].getMass();
			System.out.println(masses[i].getCen().getX());
			y=y+masses[i].getCen().getY()*masses[i].getMass();
		}
		x=x/tMass;
		y=y/tMass;
		return new Point(x,y);
	}
	public void draw(Graphics g){
		for(int i=0;i<pieces.length;i++){
			pieces[i].draw(g);
		}
	}
//	public static void main(String[] args){
//		PhysicsObject[] masses={new PhysicsObject(0,0,100,100),new PhysicsObject(100,100,100,100)};
//		System.out.println(findCom(masses));
//	}
}
