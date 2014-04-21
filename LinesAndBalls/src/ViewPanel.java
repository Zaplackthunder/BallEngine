
import java.awt.*;

import javax.swing.*;

public class ViewPanel extends JPanel{

	LineAndBallField playSpace;
	public ViewPanel(LineAndBallField playField){
		this.playSpace=playField;
		addMouseListener(playSpace);
		addMouseMotionListener(playSpace);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		playSpace.draw(g);
	}
}
