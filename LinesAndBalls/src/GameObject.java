import java.awt.*;


public class GameObject {
//=========Variables=============
	protected Point pos=new Point();
	protected Point cen=new Point();
	protected int width;
	protected int height;
	protected Color colour;
	protected Image img;
	
	//=========Constructors=============	
	public GameObject() {
		
	}
	public GameObject(int x,int y,int radius){
		this(x,y,radius,radius);
	}
	public GameObject(int x,int y,int width,int height){
		pos.setX(x);
		pos.setY(y);
		cen.setX(x+width);
		cen.setY(y+height);
		this.width=width;
		this.height=height;
	}
	public GameObject(int x, int y){
		cen.setX(x);
		cen.setY(y);
		pos.setX(x);
		pos.setY(y);
	}
	public GameObject(int x,int y,int width,int height, Image img){
		this(x,y,width,height);
		this.img=img;
	}
	
	//==========METHODS==============
	public void draw(Graphics g){
		g.setColor(colour);
		g.fillRect((int)pos.getX(),(int)pos.getY(),(int)width,(int)height);
	}
	
	
	//===============Getters and Setters================
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public Point getPos(){
		return pos;
	}
	public Point getCen(){
		return cen;
	}
	public void setPos(Point pos){
		this.pos=pos;
		cen.setX(pos.getX()+width/2.0);
		cen.setY(pos.getY()+height/2.0);
	}

}
