import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.util.*;


public class LineAndBallField implements ActionListener, MouseListener,MouseMotionListener{
	
	private final int defaultBallRad=10;
	private final double defaultBallMass=100;
	private int physicsTicksPerSec=1000;
	private Timer physicsTimi =new Timer(1000/physicsTicksPerSec,this);
	private double start=System.currentTimeMillis();
	private Line tempLine=null;
	
	private ArrayList<Ball> balls=new ArrayList<Ball>();
	private ArrayList<Line> lines=new ArrayList<Line>();
	private ArrayList<PhysicsObject> rots=new ArrayList<PhysicsObject>();
	
	public LineAndBallField(){
		this.addBall(new Point(120-defaultBallRad,100-defaultBallRad));
		this.addLine(new Point(0,0),new Point(1300,0));
		this.addLine(new Point(1300,0),new Point(1300,650));
		this.addLine(new Point(1300,650),new Point(0,650));
		this.addLine(new Point(0,650),new Point(0,0));
		PhysicsObject[] masses={new PhysicsObject(0,0,10,10,100,100),new PhysicsObject(100,100,10,10,100,100)};
		rots.add(new Rotatable(masses));
		physicsTimi.start();
	}

	public void addBall(Point pos){
		balls.add(new Ball(pos,defaultBallRad,defaultBallMass,physicsTicksPerSec));
	}
	public void addLine(Point p1,Point p2){
		addLine(new Line(p1,p2));
	}
	public void addLine(Line l){
		lines.add(l);
	}
	public ArrayList<Ball> getBalls(){
		return balls;
	}
	public void draw(Graphics g){
		for(int i=0;i<balls.size();i++){
			balls.get(i).draw(g);
		}
		for(int i=0;i<lines.size();i++){
			lines.get(i).draw(g);
		}
		for(int i=0;i<rots.size();i++){
			rots.get(i).draw(g);
		}
		if(tempLine!=null){
			tempLine.draw(g);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==physicsTimi){
			applyGravity();
			moveBalls();
		}
		
	}
	private void applyGravity(){
		for(int i=0;i<balls.size();i++){
			balls.get(i).force(new Point(0,10*balls.get(i).getMass()));
			balls.get(i).accelerate();
		}
	}
	private void moveBalls(){
		for(int i=0;i<balls.size();i++){
			for(int b=0;b<lines.size();b++){
				if(balls.get(i).collides(lines.get(b))){					
				}
			}
			balls.get(i).accelerate();
			balls.get(i).move();
		}
	}
	private void startLine(Point p1){
		tempLine=new Line(p1,p1);
	}
	private void updateTmpLine(Point p2){
		if(tempLine!=null){
			tempLine.p[1]=p2;
		}
	}
	private void endLine(){
		if(tempLine!=null){
			addLine(tempLine);
			tempLine=null;
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent evt) {
		if(!SwingUtilities.isRightMouseButton(evt)){
			startLine(new Point(evt.getX(),evt.getY()));			
		}else{
			for(int i=0;i<balls.size();i++){
				Point force=new Point(evt.getX(),evt.getY()).subtract(balls.get(i).getCen());
				force.normalize();
				force.scale(200000);
				balls.get(i).force(force);
				balls.get(i).accelerate();
			}
		}
	}

	public void mouseReleased(MouseEvent evt) {
		endLine();
	}

	public void mouseDragged(MouseEvent evt) {
		updateTmpLine(new Point(evt.getX(),evt.getY()));
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
