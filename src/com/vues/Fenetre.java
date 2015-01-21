package com.vues;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.lang.Math;

/**
 * Gère la fenêtre pour l'interface graphique
 */
@SuppressWarnings("serial")
public class Fenetre extends JFrame{

	/**
	 * Largeur par défaut
	 */
	public static final int WIDTH = 1200;

	/**
	 * Hauteur par défaut
	 */
	public static final int HEIGHT = 800;

	/**
	 * Zone de dessin
	 */
	private ZoneDessin conteneurZoneDessin;

	/**
	 * Zone de la liste des figures et des détails
	 */
	private FiguresOption conteneurFiguresOption;

	/**
	 * Barre des boutons
	 */
	private BarreOutils conteneurBarreOutils;

	/**
	 * Barre de status
	 */
	private StatusBar statusBar;

	/**
	 * Constructeur
	 */
	public Fenetre(){
		super("Dessin Vectoriel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		init();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Initialise et place les élements dans la fenêtre
	 */
	private void init(){
		// conteneur zone de dessin
		conteneurZoneDessin = new ZoneDessin();

		// conteneur zone des options des figures
		conteneurFiguresOption = new FiguresOption();

		// conteneur de la barre d'outils
		conteneurBarreOutils = new BarreOutils();

		statusBar = new StatusBar();
		statusBar.setPreferredSize(new Dimension(getWidth(), 20));

		JPanel container = new JPanel();
		setLayout(new BorderLayout());

		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.0;
		gbc.weighty = 1;
		gbc.insets = new Insets(10,10,10,10);
		container.add(conteneurFiguresOption, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		container.add(conteneurZoneDessin, gbc);

		add(conteneurBarreOutils, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		
		pack();
	}

	/**
	 * Getter zone de dessin
	 * @return zone de dessin
	 */
	public ZoneDessin getZoneDessin(){
		return conteneurZoneDessin;
	}

	/**
	 * Getter option des figures
	 * @return option des figures
	 */
	public FiguresOption getFiguresOption(){
		return conteneurFiguresOption;
	}

	/**
	 * Getter barre des boutons
	 * @return barre des boutons
	 */
	public BarreOutils getBarreOutils(){
		return conteneurBarreOutils;
	}

	/**
	 * Getter barre de status
	 * @return barre de status
	 */
	public StatusBar getStatusBar(){
		return statusBar;
	}

}