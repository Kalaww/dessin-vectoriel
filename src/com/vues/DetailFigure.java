package com.vues;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;

import com.App;
import com.modeles.Figure;
import com.controleurs.DetailFigureControler;

/**
 * Détail de la figure sélectionnée sous forme de formulaire
 */
@SuppressWarnings("serial")
public class DetailFigure extends JPanel{

	
	protected JLabel labelNom;

	protected JTextField nom;

	protected JButton valider;

	protected JButton supprimer;
	
	/**
	 * Constructeur
	 */
	public DetailFigure(){
		super();

		labelNom = new JLabel("Nom");
		nom = new JTextField(10);
		valider = new JButton("Valider");
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(new DetailFigureControler(1));
		valider.addActionListener(new DetailFigureControler(0));
	}

	/**
	 * Initialise les éléments qui constitue le formulaire
	 */
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
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(valider, gbc);

		gbc.gridy = 2;
		add(supprimer, gbc);
	}

	public void paintComponent(Graphics g){
		updateData(App.espace.getSelectedFigure());
		super.paintComponent(g);
	}

	/**
	 * Mise à jour des informations affichées
	 * @param f [description]
	 */
	public void updateData(Figure f){
		if(f == null) return;
		nom.setText(f.getNom());
	}

	/**
	 * Getter nom de la figure entrée dans le formulaire
	 * @return nom
	 */
	public String getNom(){
		return nom.getText();
	}

}