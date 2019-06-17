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
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class DessinBoard extends JComponent {
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> figures = new ArrayList<Shape>();
	private ArrayList<Color> figureRemplir = new ArrayList<Color>();
	private ArrayList<Color> figurecouleurcont = new ArrayList<Color>();
	private Point drawStart;
	private Point drawEnd;
	private boolean load = false;
	private String savePath;
	private String openPath;
	private Graphics2D graphique;
	private int currentAction = 1;
	private int remplissage = 1;
	@SuppressWarnings("unused")
	private DessinMethodes mesMethodes = new DessinMethodes();

	public DessinBoard() {

	}

	public void paint(Graphics g) {
		super.paint(g);
		graphique = (Graphics2D) g;
		graphique.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphique.setStroke(new BasicStroke(4));
		AffineTransform oldXForm = graphique.getTransform();
		for (int i = 0; i < figures.size(); i++) {
			//graphique.translate(0, 0);
			if(figures.get(i) instanceof Texte) {
				Texte txt = (Texte)figures.get(i);
				graphique.translate(txt.getX()+i*5-150, txt.getY()+i*3-150);
				graphique.setPaint(figurecouleurcont.get(i));
				graphique.draw(txt.getShape());
				graphique.setTransform(oldXForm);
				//graphique.translate(0, 0);
				
				repaint();
			}
			else {
				//graphique.translate(0, 0);
				graphique.setPaint(figurecouleurcont.get(i));
				graphique.draw(figures.get(i));
				graphique.setPaint(figureRemplir.get(i));
				if (remplissage == 2) {
					graphique.fill(figures.get(i));
					//remplissage = 1;
				}
			}
		}

		if (load) {

			graphique.setStroke(new BasicStroke(4));
			for (int i = 0; i < figures.size(); i++) {
				
				graphique.setPaint(figurecouleurcont.get(i));
				graphique.draw(figures.get(i));
				graphique.setPaint(figureRemplir.get(i));
				if (remplissage == 2) {
					graphique.fill(figures.get(i));
					remplissage = 1;
				}
			}
		}

		if (drawStart != null && drawEnd != null) {
			graphique.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));
			graphique.setPaint(Color.LIGHT_GRAY);

			Shape uneFigure = null;
			if (currentAction == 2) {
				uneFigure = mesMethodes.drawLine(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
			} else if (currentAction == 3) {
				uneFigure = mesMethodes.drawEllipse(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
			} else if (currentAction == 4) {
				uneFigure = mesMethodes.drawRectangle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
			} else if (currentAction == 5) {
				uneFigure = mesMethodes.drawTriangle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
			}
			graphique.draw(uneFigure);

		}
	}

	///// GETTERS SETTERS

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

	public ArrayList<Shape> getFigures() {
		return figures;
	}

	public void setFigures(ArrayList<Shape> shapes) {
		this.figures = shapes;
	}

	public ArrayList<Color> getFigureRemplir() {
		return figureRemplir;
	}

	public void setFigureRemplir(ArrayList<Color> shapeFill) {
		this.figureRemplir = shapeFill;
	}

	public ArrayList<Color> getFigurecouleurcont() {
		return figurecouleurcont;
	}

	public void setFigurecouleurcont(ArrayList<Color> shapeStroke) {
		this.figurecouleurcont = shapeStroke;
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

	public Graphics2D getGraphique() {
		return graphique;
	}

	public void setGraphique(Graphics2D graphicSettings) {
		this.graphique = graphicSettings;
	}

	public void addMyMouseActivity(MouseAdapter mouse) {
		this.addMouseListener(mouse);
	}

	public void addMyMotionActivity(MouseMotionAdapter mouseM) {
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
