package com.vues;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Graphics;

import java.util.Locale;

import com.modeles.Point;
import com.modeles.Figure;


/**
 * Détail pour un point
 */
@SuppressWarnings("serial")
public class DetailFigurePoint extends DetailFigure{

	protected JLabel labelX;

	protected JLabel labelY;

	protected JTextField coordX;

	protected JTextField coordY;

	/**
	 * Constructeur
	 */
	public DetailFigurePoint(){
		super();

		labelX = new JLabel("X");
		labelY = new JLabel("Y");
		coordX = new JTextField();
		coordY = new JTextField();
	}

	public void init(){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = gbc.gridheight = 1;
		gbc.weighty = 1;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0,10,0,10);
		add(labelNom, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(nom, gbc);

		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.weightx= 0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelX, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordX, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.weightx = 0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelY, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordY, gbc);

		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(valider, gbc);

		gbc.gridy = 4;
		add(supprimer, gbc);
	}

	public void updateData(Figure f){
		if(f == null) return;
		Point p = (Point)f;
		nom.setText(p.getNom());
		coordX.setText(String.format(Locale.ENGLISH, "%.2f", p.getX()));
		coordY.setText(String.format(Locale.ENGLISH, "%.2f", p.getY()));
	}


	public double getCoordX(){
		return Double.parseDouble(coordX.getText());
	}

	public double getCoordY(){
		return Double.parseDouble(coordY.getText());
	}
}