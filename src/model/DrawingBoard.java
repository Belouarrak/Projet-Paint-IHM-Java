package model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JComponent;

public class DrawingBoard extends JComponent{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape>shapes=new ArrayList<Shape>();
	private ArrayList<Color>shapeFill=new ArrayList<Color>();
	private ArrayList<Color>shapeStroke=new ArrayList<Color>();
	private ArrayList<Float> transPercent = new ArrayList<Float>();
	private Point drawStart; 
	private Point drawEnd;
	private boolean load = false;
	private String savePath;
	private String openPath;
	private Graphics2D graphicSettings;
	private int currentAction=1;
	private int remplissage=1;
	@SuppressWarnings("unused")
	private float transparencyVal = 1.0f;
	private DrawingFunctions myFunctions = new DrawingFunctions();
	public DrawingBoard(){
	
	}
	
	public void paint(Graphics g){
		super.paint(g);
		graphicSettings=(Graphics2D)g;
		graphicSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

         graphicSettings.setStroke(new BasicStroke(4));  
         
         for (int i = 0; i < shapes.size(); i++) {
         	graphicSettings.setPaint(shapeStroke.get(i)); 
         	graphicSettings.draw(shapes.get(i));
        	graphicSettings.setPaint(shapeFill.get(i)); 
        	if (remplissage == 2) {
        		graphicSettings.fill(shapes.get(i));
        	}
        	
		}
         
        if (load)
        {
	     	Image img1 = Toolkit.getDefaultToolkit().getImage(openPath);
	        graphicSettings.drawImage(img1, 0, 0, this);
	        
	        
	        graphicSettings.setStroke(new BasicStroke(4));       
	         for (int i = 0; i < shapes.size(); i++) {
	         	graphicSettings.setPaint(shapeStroke.get(i)); 
	         	graphicSettings.draw(shapes.get(i));
	        	graphicSettings.setPaint(shapeFill.get(i)); 	
	        	if (remplissage == 2) {
	        		graphicSettings.fill(shapes.get(i));
	        	}
			}
        }
         
         
        if (drawStart != null && drawEnd != null){
        	graphicSettings.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.40f));         
        	graphicSettings.setPaint(Color.LIGHT_GRAY);
        	
        	Shape aShape = null;
        	if (currentAction==2)
        	{
        	aShape = myFunctions.drawLine(drawStart.x, drawStart.y,
            		drawEnd.x, drawEnd.y);
        	} else if (currentAction==3)
        	{
        		aShape = myFunctions.drawEllipse(drawStart.x, drawStart.y,
                		drawEnd.x, drawEnd.y);
        	} else if (currentAction==4)
        	{
        		aShape = myFunctions.drawRectangle(drawStart.x, drawStart.y,
                		drawEnd.x, drawEnd.y);
        	}
        	graphicSettings.draw(aShape);
        	
        	
        
        	 
        }
	}
	

	
	public Point getDrawStart() {
		return drawStart;
	}

	public void setDrawStart(Point drawStart) {
		this.drawStart = drawStart;
	}

	public Point getDrawEnd() {
		return drawEnd;
	}

	public void setDrawEnd(Point drawEnd) {
		this.drawEnd = drawEnd;
	}

	
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ArrayList<Color> getShapeFill() {
		return shapeFill;
	}

	public void setShapeFill(ArrayList<Color> shapeFill) {
		this.shapeFill = shapeFill;
	}

	public ArrayList<Color> getShapeStroke() {
		return shapeStroke;
	}

	public void setShapeStroke(ArrayList<Color> shapeStroke) {
		this.shapeStroke = shapeStroke;
	}

	
	
	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}
	
	
	

	public String getOpenPath() {
		return openPath;
	}

	public void setOpenPath(String path) {
		this.openPath = path;
	}

	
	
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
	}

	public Graphics2D getGraphicSettings() {
		return graphicSettings;
	}

	public void setGraphicSettings(Graphics2D graphicSettings) {
		this.graphicSettings = graphicSettings;
	}

	public void addMyMouseActivity(MouseAdapter mouse){
		this.addMouseListener(mouse);
	}
	public void addMyMotionActivity(MouseMotionAdapter mouseM){
		this.addMouseMotionListener(mouseM);
	}
	
	public void addMyKeyboardActivity(KeyListener keyboard) {
		this.addKeyListener(keyboard);
	}

	public int getRemplissage() {
		return remplissage;
	}

	public void setRemplissage(int remplissage) {
		this.remplissage = remplissage;
	}
}
