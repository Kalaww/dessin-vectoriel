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

import com.modeles.Segment;
import com.modeles.Figure;


/**
 * Détail pour un segment
 */
@SuppressWarnings("serial")
public class DetailFigureSegment extends DetailFigure{

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

	
	public DetailFigureSegment(){
		super();

		point1 = new JLabel("Point 1 :");
		labelX1 = new JLabel("X");
		labelY1 = new JLabel("Y");
		coordX1 = new JTextField();
		coordY1 = new JTextField();

		point2 = new JLabel("Point 2 :");
		labelX2 = new JLabel("X");
		labelY2 = new JLabel("Y");
		coordX2 = new JTextField();
		coordY2 = new JTextField();
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

		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(0,10,0,10);
		add(point2, gbc);

		gbc.gridy = 5;
		gbc.weightx = 0.0;
		gbc.gridwidth = 1;
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

		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(valider, gbc);

		gbc.gridy = 8;
		add(supprimer, gbc);
	}

	public void updateData(Figure f){
		if(f == null || !( f instanceof Segment)) return;
		Segment p = (Segment)f;
		nom.setText(p.getNom());
		coordX1.setText(String.format(Locale.ENGLISH, "%.2f", p.getP1().getX()));
		coordY1.setText(String.format(Locale.ENGLISH, "%.2f", p.getP1().getY()));
		coordX2.setText(String.format(Locale.ENGLISH, "%.2f", p.getP2().getX()));
		coordY2.setText(String.format(Locale.ENGLISH, "%.2f", p.getP2().getY()));
	}


	public double getCoordX1(){
		return Double.parseDouble(coordX1.getText());
	}

	public double getCoordY1(){
		return Double.parseDouble(coordY1.getText());
	}

	public double getCoordX2(){
		return Double.parseDouble(coordX2.getText());
	}

	public double getCoordY2(){
		return Double.parseDouble(coordY2.getText());
	}
}