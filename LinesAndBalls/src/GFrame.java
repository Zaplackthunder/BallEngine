import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GFrame extends JFrame implements ActionListener{

	//Physics Constants
	public static final int ticksPerSec=60;
	private Timer timi=new Timer(1000/ticksPerSec,this);
	
	public GFrame(){
		LineAndBallField playField=new LineAndBallField();
		ViewPanel playPanel=new ViewPanel(playField);
		timi.start();
		this.add(playPanel);
	}
	

	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==timi){
			repaint();
		}
	}
	
	public static void main(String[] args) {
		GFrame frame=new GFrame();
		frame.setSize(1300,700);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(500,500));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}

}
