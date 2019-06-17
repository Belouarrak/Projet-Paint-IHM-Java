package model;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class DessinMethodes implements InterfaceModele {

	public Ellipse2D.Float drawBrush(int x1, int y1, int brushStrokeWidth, int brushStrokeHeight) {
		return new Ellipse2D.Float(x1, y1, brushStrokeWidth, brushStrokeHeight);
	}

	public Line2D.Float drawLine(int x1, int y1, int x2, int y2) {
		return new Line2D.Float(x1, y1, x2, y2);
	}

	public Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		return new Ellipse2D.Float(x, y, width, height);
	}

	public Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2) {
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);
		return new Rectangle2D.Float(x, y, width, height);
	}

	public Texte drawString(String text) {
		Font font = new Font("Arial", Font.PLAIN, 35);
		FontRenderContext frc = new FontRenderContext(null, true, true);
	    GlyphVector v = font.createGlyphVector(frc, text);
	    Texte textShape = new Texte(200,200,v.getOutline());
	    return textShape;
	}
	public Shape drawTriangle(int x1, int y1, int x2, int y2) {
		Path2D.Double triangle = new Path2D.Double();
		int x3 = 0;
		if(x1-x2<0) {
			x3 = x1-(x2-x1);
		}
		else {
			x3 = x1+Math.abs(x2-x1);
		}
		int y3 = y2;
	      triangle.moveTo(x1,y1);
	      triangle.lineTo(x2, y2);
	      triangle.lineTo(x3, y3);
	      triangle.closePath();
	      return triangle;
	}
	public void saveImage(String path, Graphics2D g2, DessinBoard drawB) {
		try {
			BufferedImage image = new BufferedImage(drawB.getWidth(), drawB.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = image.getGraphics();
			drawB.printAll(g);
			g.dispose();
			ImageIO.write(image, "png", new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadImage(Graphics2D g2, DessinBoard drawB) {
		try {
			BufferedImage image = new BufferedImage(drawB.getWidth(), drawB.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = image.getGraphics();
			drawB.printAll(g);
			g.dispose();
			ImageIO.write(image, "png", new File("saves/img.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
