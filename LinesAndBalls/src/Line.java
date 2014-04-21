import java.awt.Graphics;
import java.io.Serializable;


public class Line implements Serializable{

	public Point p[]=new Point[2];
	public Line(double x1,double y1, double x2,double y2) {
		p[0]=new Point(x1,y1);
		p[1]=new Point(x2,y2);
	}
	public Line(Point one,Point two){
		p[0]=one;
		p[1]=two;
	}
	public void draw(Graphics g){
		g.drawLine((int)p[0].getX(),(int)p[0].getY(),(int)p[1].getX(),(int)p[1].getY());
	}
	public String toString(){
		return p[0].toString()+p[1].toString();
	}

}
