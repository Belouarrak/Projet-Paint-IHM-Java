package model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public interface InterfaceModele {
	Ellipse2D.Float drawBrush(int x1, int y1, int brushStrokeWidth, int brushStrokeHeight);

	Line2D.Float drawLine(int x1, int y1, int x2, int y2);

	Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2);

	Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2);
}
