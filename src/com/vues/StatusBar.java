package com.vues;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Barre de status. Affiche des aides pour les boutons, le zoom et les coordonnées
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel{
	
	/**
	 * Label des aides
	 */
	private JLabel infoText;

	/**
	 * Label des coordonnées et zoom
	 */
	private JLabel coordText;

	/**
	 * Coordonnée x à afficher
	 */
	private double coordX;

	/**
	 * Coordonnée y à afficher
	 */
	private double coordY;

	/**
	 * Zoom à afficher
	 */
	private double zoom;

	/**
	 * Constructeur
	 */
	public StatusBar(){
		super();

		infoText = new JLabel();
		coordText = new JLabel();
		JPanel marge = new JPanel();
		marge.setPreferredSize(new Dimension(getWidth(), 5));

		coordX = 0.0;
		coordY = 0.0;
		zoom = 1.0;
		updateTextCoord();

		setLayout(new BorderLayout());
		add(infoText, BorderLayout.WEST);
		add(coordText, BorderLayout.EAST);
		add(marge, BorderLayout.SOUTH);
	}

	/**
	 * Mise à jour du label d'aide
	 * @param s [description]
	 */
	public void updateInfo(String s){
		infoText.setText("  "+s);
	}

	/**
	 * Mise à jour des coordonnées à afficher
	 * @param x abscisse
	 * @param y ordonnée
	 */
	public void updateCoord(double x, double y){
		coordX = x;
		coordY = y;
		updateTextCoord();
	}

	/**
	 * Mise à jour zoom à afficher
	 * @param z [description]
	 */
	public void updateZoom(double z){
		zoom = z;
		updateTextCoord();
	}

	/**
	 * Mise à jour du texte des coordonnées et du zomm
	 */
	private void updateTextCoord(){
		String a = String.format("%.2f", zoom);
		String b = String.format("%.2f", coordX);
		String c = String.format("%.2f", coordY);
		coordText.setText("Zoom : x"+a+" Curseur [ x: "+b+"  y: "+c+" ]  ");
	}

}