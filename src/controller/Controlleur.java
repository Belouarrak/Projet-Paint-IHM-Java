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
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.DessinBoard;
import model.DessinMethodes;
import view.VueBoardDessin;

public class Controlleur {

	private DessinBoard dessinB = new DessinBoard();
	private VueBoardDessin vueBoard = new VueBoardDessin(dessinB);
	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.BLACK;

	public Controlleur() {

		DessinMethodes myFunctions = new DessinMethodes();

		vueBoard.addButtonActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == vueBoard.getBtnBrush()) {
					dessinB.setCurrentAction(1);
				}
				if (e.getSource() == vueBoard.getBtnLine()) {
					dessinB.setCurrentAction(2);
				}
				if (e.getSource() == vueBoard.getBtnEllipse()) {
					dessinB.setCurrentAction(3);
				}
				if (e.getSource() == vueBoard.getBtnRectangle()) {
					dessinB.setCurrentAction(4);
				}
				if (e.getSource() == vueBoard.getRemplir()) {
					if (vueBoard.remplir.isSelected()) {
						dessinB.setRemplissage(2);
					}
				}
				if (e.getSource() == vueBoard.getBtnColor()) {
					strokeColor = JColorChooser.showDialog(null, "Prendre une couleur de contour", Color.BLACK);
				}
				if (e.getSource() == vueBoard.getBtnFill()) {
					fillColor = JColorChooser.showDialog(null, "Prendre de quoi remplir", Color.BLACK);
				}
				if (e.getSource() == vueBoard.getBtnText()) {
					if (!vueBoard.getTextField().getText().isEmpty()) {
						dessinB.getFigures().add(myFunctions.drawString(vueBoard.getTextField().getText()));
						dessinB.getFigureRemplir().add(new Color(0,0,0));
						dessinB.getFigurecouleurcont().add(new Color(60,60,60));
					}
					
				}
				if (e.getSource() == vueBoard.getBtnRefresh()) {
					dessinB.getFigures().clear();
					dessinB.getFigureRemplir().clear();
					dessinB.getFigurecouleurcont().clear();

					dessinB.repaint();
					dessinB.setLoad(false);

				}
				if (e.getSource() == vueBoard.getBtnUndo()) {
					if (dessinB.getFigures().size() > 1 && dessinB.getFigureRemplir().size() > 1
							&& dessinB.getFigurecouleurcont().size() > 1) {
						dessinB.getFigures().remove(dessinB.getFigures().size() - 1);
						dessinB.getFigureRemplir().remove(dessinB.getFigureRemplir().size() - 1);
						dessinB.getFigurecouleurcont().remove(dessinB.getFigurecouleurcont().size() - 1);

					} else {
						dessinB.getFigures().clear();
						dessinB.getFigureRemplir().clear();
						dessinB.getFigurecouleurcont().clear();

					}
					dessinB.repaint();
				}
				if (e.getSource() == vueBoard.getBtnSave()) {

					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showSaveDialog(vueBoard);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println(chooser.getSelectedFile().getPath());

//					       System.out.println("You chose to open this file: " +
//					            chooser.getSelectedFile().getPath());
						dessinB.setSavePath(chooser.getSelectedFile().getPath());
						myFunctions.saveImage(dessinB.getSavePath(), dessinB.getGraphique(), dessinB);
					}

				}
				
				if (e.getSource() == vueBoard.getBtnLoad()) {

					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
					chooser.setFileFilter(filter);
					int returnVal = chooser.showOpenDialog(vueBoard);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						dessinB.setOpenPath(chooser.getSelectedFile().getPath());
						dessinB.setLoad(true);
						dessinB.repaint();
					}
				}

			}
		});

		dessinB.addMyKeyboardActivity(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {

				
			}

			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				if (c == KeyEvent.VK_L) {
					System.out.println("YEAH");
					dessinB.setCurrentAction(3);}
				}
			

			public void keyReleased(KeyEvent e) {
			}
		}
			
		);


		dessinB.addMyMouseActivity(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (dessinB.getCurrentAction() != 1) {
					dessinB.setDrawStart(new Point(e.getX(), e.getY()));
					dessinB.setDrawEnd(dessinB.getDrawStart());
					dessinB.repaint();
					System.out.println("\n APPUI:"+dessinB.getDrawStart());
				}
			}

			public void mouseReleased(MouseEvent e) {

				if (dessinB.getCurrentAction() != 1) {

					Shape aShape = null;
					if (dessinB.getCurrentAction() == 2) {
						aShape = myFunctions.drawLine(dessinB.getDrawStart().x, dessinB.getDrawStart().y, e.getX(),
								e.getY());
					} else if (dessinB.getCurrentAction() == 3) {
						aShape = myFunctions.drawEllipse(dessinB.getDrawStart().x, dessinB.getDrawStart().y, e.getX(),
								e.getY());
					} else if (dessinB.getCurrentAction() == 4) {
						aShape = myFunctions.drawRectangle(dessinB.getDrawStart().x, dessinB.getDrawStart().y, e.getX(),
								e.getY());
					}
					dessinB.getFigures().add(aShape);
					dessinB.getFigureRemplir().add(fillColor);
					dessinB.getFigurecouleurcont().add(strokeColor);

					dessinB.setDrawStart(null);
					dessinB.setDrawEnd(null);

					dessinB.repaint();
				}
			}
		});
		dessinB.addMyMotionActivity(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (dessinB.getCurrentAction() == 1) {
					int x = e.getX();
					int y = e.getY();

					Shape aShape = null;
					strokeColor = fillColor;
					aShape = myFunctions.drawBrush(x, y, 5, 10);

					dessinB.getFigures().add(aShape);
					dessinB.getFigureRemplir().add(fillColor);
					dessinB.getFigurecouleurcont().add(strokeColor);
				}
				dessinB.setDrawEnd(new Point(e.getX(), e.getY()));
				dessinB.repaint();
				
			}
		});
		dessinB.requestFocus();
	}

	public static void main(String[] args) {
		Controlleur c = new Controlleur();
		c.vueBoard.setVisible(true);
		c.dessinB.requestFocus();
	
	}
}
