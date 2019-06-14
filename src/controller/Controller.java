package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DrawingBoard;
import model.DrawingFunctions;
import view.ViewDrawingBoard;

public class Controller {
	
	
	private DrawingBoard drawB = new DrawingBoard();
	private ViewDrawingBoard viewDrawB = new ViewDrawingBoard(drawB);
	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.BLACK;
	private float transparentVal = 1.0f;
	public Controller(){

		DrawingFunctions myFunctions = new DrawingFunctions();
		
		
		viewDrawB.addButtonActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==viewDrawB.getBtnBrush()){
						drawB.setCurrentAction(1);
				}
				if (e.getSource()==viewDrawB.getBtnLine()){
					drawB.setCurrentAction(2);
				}
				if (e.getSource()==viewDrawB.getBtnEllipse()){
					drawB.setCurrentAction(3);
				}
				if (e.getSource()==viewDrawB.getBtnRectangle()){
					drawB.setCurrentAction(4);
				}
				if (e.getSource()==viewDrawB.getRemplir()) {
					if (viewDrawB.remplir.isSelected()) {
						drawB.setRemplissage(2);
					}
				}
				if(e.getSource()==viewDrawB.getBtnColor()){
						strokeColor = JColorChooser.showDialog(null, "Pick a Stroke", Color.BLACK);
				}
				if(e.getSource()==viewDrawB.getBtnFill()){
					fillColor = JColorChooser.showDialog(null, "Pick a Fill", Color.BLACK);
				}
				if(e.getSource()==viewDrawB.getBtnRefresh()){
					drawB.getShapes().clear();
					drawB.getShapeFill().clear();
					drawB.getShapeStroke().clear();

					drawB.repaint();
					drawB.setLoad(false);
					
				}
				if(e.getSource()==viewDrawB.getBtnUndo()){
					if (drawB.getShapes().size()>1 && 
							drawB.getShapeFill().size()>1 && 
								drawB.getShapeStroke().size()>1)
					{
						drawB.getShapes().remove(drawB.getShapes().size()-1);
						drawB.getShapeFill().remove(drawB.getShapeFill().size()-1);
						drawB.getShapeStroke().remove(drawB.getShapeStroke().size()-1);

					} else 
					{
						drawB.getShapes().clear();
						drawB.getShapeFill().clear();
						drawB.getShapeStroke().clear();
	
					}
					drawB.repaint();
				}
				if(e.getSource()==viewDrawB.getBtnSave()){
					
					
					 JFileChooser chooser = new JFileChooser();
					    FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "PNG Images", "png");
					    chooser.setFileFilter(filter);
					    int returnVal = chooser.showSaveDialog(viewDrawB);
					    if(returnVal == JFileChooser.APPROVE_OPTION) {
					    	System.out.println(chooser.getSelectedFile().getPath());
					    	
//					       System.out.println("You chose to open this file: " +
//					            chooser.getSelectedFile().getPath());
					    	drawB.setSavePath(chooser.getSelectedFile().getPath());
					    	myFunctions.saveImage(drawB.getSavePath(),drawB.getGraphicSettings(), drawB);
					    }
					
					
					
					}
				if(e.getSource()==viewDrawB.getBtnLoad()){
					
					 JFileChooser chooser = new JFileChooser();
					    FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "PNG Images", "png");
					    chooser.setFileFilter(filter);
					    int returnVal = chooser.showOpenDialog(viewDrawB);
					    if(returnVal == JFileChooser.APPROVE_OPTION) {
//					       System.out.println("You chose to open this file: " +
//					            chooser.getSelectedFile().getPath());
					    	drawB.setOpenPath(chooser.getSelectedFile().getPath());
					    	 drawB.setLoad(true);
							 drawB.repaint();
					    }
					
					
					// drawB.setLoad(false);
				}
				
			}
		});

	
		drawB.addMyKeyboardActivity(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				System.out.println(c);
				if (c == 'l' || c == 'L')
				{
					drawB.setCurrentAction(3);
				}
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				System.out.println(c);
				if (c == KeyEvent.VK_UP)
				{
					drawB.setCurrentAction(3);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
			
		
		
		drawB.addMyMouseActivity(new MouseAdapter() {
		      public void mousePressed(MouseEvent e){
		    	  if(drawB.getCurrentAction() != 1)
		    	  {
		    		  drawB.setDrawStart(new Point(e.getX(), e.getY()));
		    		  drawB.setDrawEnd(drawB.getDrawStart());
		    		  drawB.repaint();
		    		  //System.out.println("\nPRESSED:"+drawB.getDrawStart());
		    	  }
		      }
		      public void mouseReleased(MouseEvent e){
		    	  
		    	  if(drawB.getCurrentAction()!=1){
		    	  
		    	  Shape aShape = null; 
		    	  if(drawB.getCurrentAction()==2)
		    	  {
		    		  aShape=myFunctions.drawLine(drawB.getDrawStart().x,
		    				  drawB.getDrawStart().y,
		    				  e.getX(),
		    				  e.getY());
		    	  }
		    	  else if(drawB.getCurrentAction()==3)
		    	  {	    		  
                  		aShape = myFunctions.drawEllipse(drawB.getDrawStart().x,
  		    				  drawB.getDrawStart().y,
                  				e.getX(), e.getY());        	
		    	  }
		    	  else if(drawB.getCurrentAction()==4)
		    	  {	    		  
                  		aShape = myFunctions.drawRectangle(drawB.getDrawStart().x,
  		    				  drawB.getDrawStart().y,
                  				e.getX(), e.getY());        	
		    	  }
		    	  drawB.getShapes().add(aShape);
		    	  drawB.getShapeFill().add(fillColor);
		    	  drawB.getShapeStroke().add(strokeColor);
		    	  
		    	  drawB.setDrawStart(null);
		    	  drawB.setDrawEnd(null);
		    	  
		    	  drawB.repaint();
		    	  }		    	  
		      }
		});
		drawB.addMyMotionActivity(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
			if (drawB.getCurrentAction()==1){	
				int x = e.getX();
				int y = e.getY();
				
				Shape aShape=null; 
				strokeColor=fillColor; 
				aShape = myFunctions.drawBrush(x,y,5,35);
				
				drawB.getShapes().add(aShape);
				drawB.getShapeFill().add(fillColor);
				drawB.getShapeStroke().add(strokeColor);
			}
			drawB.setDrawEnd(new Point(e.getX(),e.getY()));
			drawB.repaint();
			}
		});
	}
	



	public static void main (String[] args){
		Controller c = new Controller();
		c.viewDrawB.setVisible(true);
	}
}
