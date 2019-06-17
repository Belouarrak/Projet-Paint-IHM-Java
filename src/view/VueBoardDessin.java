package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.DessinBoard;

public class VueBoardDessin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel bottomPanel;
	private JTextField texte1;
	private JButton btnText;
	private JButton btnLine;
	private JButton btnEllipse;
	private JButton btnRectangle;
	private JButton btnBrush;
	private JButton btnColor;
	private Box theBox;
	private JButton btnFill;
	private JButton btnRefresh;
	private JButton btnUndo;
	private JButton btnSave;
	private JButton btnLoad;
	public JCheckBox remplir;

	public VueBoardDessin(DessinBoard myDessinBoard) {

		setTitle("Mon Paint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 70, 1200, 700);
		setResizable(true);

		bottomPanel = new JPanel();
		btnLine = new JButton("Ligne");
		
		// contentPane.setLayout(null);
		btnLine.setBounds(236, 492, 70, 70);
		// contentPane.add(btnLine);

		btnEllipse = new JButton("Cerclipse");
		btnEllipse.setBounds(334, 492, 70, 70);
		// contentPane.add(btnEllipse);

		btnRectangle = new JButton("Rectangle");
		btnRectangle.setBounds(442, 492, 70, 70);
		// contentPane.add(btnRectangle);

		btnColor = new JButton("Colour");
		texte1 = new JTextField("Inserez votre Texte");
		texte1.setBounds(50, 100, 200, 30); 

		btnBrush = new JButton("Free");
		this.add(myDessinBoard, BorderLayout.CENTER);
		this.requestFocus();
		theBox = Box.createHorizontalBox();

		btnUndo = new JButton("Prec");
		theBox.add(btnUndo);

		btnRefresh = new JButton("Rafraichir");
		theBox.add(btnRefresh);

		remplir = new JCheckBox("Remplir");
		remplir.setSelected(false);

		theBox.add(btnLine);
		theBox.add(btnEllipse);
		theBox.add(btnRectangle);
		theBox.add(btnBrush);
		theBox.add(btnColor);

		btnFill = new JButton("Remplir");
		theBox.add(btnFill);

		btnSave = new JButton("Sauvegarder");
		theBox.add(btnSave);

		btnLoad = new JButton("Charger");
		theBox.add(btnLoad);
		bottomPanel.add(theBox);
		theBox.add(remplir);
		theBox.add(texte1);
		btnText = new JButton("Send");
		theBox.add(btnText);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
	}

	public JCheckBox getRemplir() {
		return remplir;
	}

	public void setRemplir(JCheckBox remplir) {
		this.remplir = remplir;
	}
	public void setTextFiel(String text) {
		this.texte1.setText(text);
	}
	public JTextField getTextField() {
		return this.texte1;
	}
	public JButton getBtnLine() {
		return btnLine;
	}

	public void setBtnLine(JButton btnLine) {
		this.btnLine = btnLine;
	}

	public JButton getBtnEllipse() {
		return btnEllipse;
	}

	public void setBtnEllipse(JButton btnEllipse) {
		this.btnEllipse = btnEllipse;
	}

	public JButton getBtnRectangle() {
		return btnRectangle;
	}

	public void setBtnRectangle(JButton btnRectangle) {
		this.btnRectangle = btnRectangle;
	}

	public JButton getBtnBrush() {
		return btnBrush;
	}

	public void setBtnBrush(JButton btnBrush) {
		this.btnBrush = btnBrush;
	}

	public JButton getBtnLoad() {
		return btnLoad;
	}

	public void setBtnLoad(JButton btnLoad) {
		this.btnLoad = btnLoad;
	}

	public JButton getBtnFill() {
		return btnFill;
	}

	public void setBtnFill(JButton btnFill) {
		this.btnFill = btnFill;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
	}

	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}
	public void setBtnText(JButton btnText) {
		this.btnText = btnText;
	}
	public JButton getBtnText() {
		return btnText;
	}
	public void addButtonActionListener(ActionListener listener) {
		btnLine.addActionListener(listener);
		btnEllipse.addActionListener(listener);
		btnRectangle.addActionListener(listener);
		btnBrush.addActionListener(listener);
		btnColor.addActionListener(listener);
		btnFill.addActionListener(listener);
		btnRefresh.addActionListener(listener);
		btnUndo.addActionListener(listener);
		btnSave.addActionListener(listener);
		btnLoad.addActionListener(listener);
		remplir.addActionListener(listener);
		btnText.addActionListener(listener);
		
	}

	public void addKeyListener(KeyListener listener) {
		getContentPane().addKeyListener(listener);
	}
}
