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

import com.modeles.Rectangle;
import com.modeles.Figure;


/**
 * Détail pour un rectangle
 */
@SuppressWarnings("serial")
public class DetailFigureRectangle extends DetailFigure{

	protected JLabel point1;

	protected JLabel labelX1;

	protected JLabel labelY1;

	protected JTextField coordX1;

	protected JTextField coordY1;

	protected JLabel point2;

	protected JLabel labelX2;

	protected JLabel labelY2;

	protected JTextField coordX2;

	protected JTextField coordY2;

	protected JLabel point3;

	protected JLabel labelX3;

	protected JLabel labelY3;

	protected JTextField coordX3;

	protected JTextField coordY3;

	protected JLabel point4;

	protected JLabel labelX4;

	protected JLabel labelY4;

	protected JTextField coordX4;

	protected JTextField coordY4;

	
	public DetailFigureRectangle(){
		super();

		point1 = new JLabel("Point 1 :");
		labelX1 = new JLabel("X");
		labelY1 = new JLabel("Y");
		coordX1 = new JTextField();
		coordX1.setEditable(false);
		coordY1 = new JTextField();
		coordY1.setEditable(false);

		point2 = new JLabel("Point 2 :");
		labelX2 = new JLabel("X");
		labelY2 = new JLabel("Y");
		coordX2 = new JTextField();
		coordX2.setEditable(false);
		coordY2 = new JTextField();
		coordY2.setEditable(false);

		point3 = new JLabel("Point 3 :");
		labelX3 = new JLabel("X");
		labelY3 = new JLabel("Y");
		coordX3 = new JTextField();
		coordX3.setEditable(false);
		coordY3 = new JTextField();
		coordY3.setEditable(false);

		point4 = new JLabel("Point 4 :");
		labelX4 = new JLabel("X");
		labelY4 = new JLabel("Y");
		coordX4 = new JTextField();
		coordX4.setEditable(false);
		coordY4 = new JTextField();
		coordY4.setEditable(false);
	}

	public void init(){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = gbc.gridheight = 1;
		gbc.weighty = 1;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0,10,0,10);
		add(labelNom, gbc);

		//p1
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(nom, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(point1, gbc);

		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.weightx= 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelX1, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordX1, gbc);

		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelY1, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordY1, gbc);

		//p2
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(point2, gbc);

		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelX2, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordX2, gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelY2, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordY2, gbc);

		//p3
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(point3, gbc);

		gbc.gridy = 8;
		gbc.gridx = 0;
		gbc.gridwidth = 1;
		gbc.weightx= 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelX3, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordX3, gbc);

		gbc.gridy = 9;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelY3, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordY3, gbc);

		//p4
		gbc.gridy = 10;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(point4, gbc);

		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelX4, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordX4, gbc);

		gbc.gridy = 12;
		gbc.gridx = 0;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(labelY4, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(0,0,0,10);
		add(coordY4, gbc);

		gbc.gridy = 13;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(valider, gbc);

		gbc.gridy = 14;
		add(supprimer, gbc);
	}

	public void updateData(Figure f){
		if(f == null || !( f instanceof Rectangle)) return;
		Rectangle p = (Rectangle)f;
		nom.setText(p.getNom());
		coordX1.setText(String.format(Locale.ENGLISH, "%.2f", p.getP1().getX()));
		coordY1.setText(String.format(Locale.ENGLISH, "%.2f", p.getP1().getY()));
		coordX2.setText(String.format(Locale.ENGLISH, "%.2f", p.getP2().getX()));
		coordY2.setText(String.format(Locale.ENGLISH, "%.2f", p.getP2().getY()));
		coordX3.setText(String.format(Locale.ENGLISH, "%.2f", p.getP3().getX()));
		coordY3.setText(String.format(Locale.ENGLISH, "%.2f", p.getP3().getY()));
		coordX4.setText(String.format(Locale.ENGLISH, "%.2f", p.getP4().getX()));
		coordY4.setText(String.format(Locale.ENGLISH, "%.2f", p.getP4().getY()));
	}
}