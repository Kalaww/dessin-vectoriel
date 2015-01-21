package com.vues;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.Color;

import java.util.ArrayList;

import com.controleurs.ButtonBarreOutilsControler;
import com.App;

/**
 * Barre d'outils comprenant les boutons
 */
@SuppressWarnings("serial")
public class BarreOutils extends JToolBar{

	private final static int ICON_SIZE = 32;


	/**
	 * Bouton de déplacement des figures
	 */
	private JButton moveFigure;

	/**
	 * Bouton de déplacement de la zone de dessin
	 */
	private JButton moveCadre;

	/**
	 * Bouton de tracer de point
	 */
	private JButton tracerPoint;

	/**
	 * Bouton de tracer de segment
	 */
	private JButton tracerSegment;

	/**
	 * Bouton de tracer de segment à partir de deux points
	 */
	private JButton tracerSegmentD;

	/**
	 * Bouton de tracer de droite
	 */
	private JButton tracerDroite;

	/**
	 * Bouton de tracer de droite à partir de deux points
	 */
	private JButton tracerDroiteD;

	/**
	 * Bouton de tracer de rectangle
	 */
	private JButton tracerRectangle;

	/**
	 * Bouton de tracer de point d'intersection
	 */
	private JButton tracerPointIntersection;

	/**
	 * Bouton de tracer de barycentre
	 */
	private JButton tracerBarycentre;

	/**
	 * Bouton de zoom in
	 */
	private JButton zoomIn;

	/**
	 * Bouton de zoom out
	 */
	private JButton zoomOut;

	/**
	 * Liste des boutons
	 */
	private ArrayList<JButton> boutons;

	/**
	 * Constructeur
	 */
	public BarreOutils(){
		super("Barre d'outils");

		moveFigure = new JButton(Texture.IconMove);
		moveFigure.setToolTipText("Déplacer une figure");
		moveFigure.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		moveFigure.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		moveFigure.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(moveFigure, App.Outils.MOVE);

		moveCadre = new JButton(Texture.IconMoveCadre);
		moveCadre.setToolTipText("Déplacer le cadre de la zone de dessin");
		moveCadre.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		moveCadre.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		moveCadre.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(moveCadre, App.Outils.MOVE_CADRE);

		zoomIn = new JButton(Texture.IconZoomIn);
		zoomIn.setToolTipText("Zoom in dans la zone de dessin");
		zoomIn.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		zoomIn.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		zoomIn.addActionListener(new ButtonBarreOutilsControler());

		zoomOut = new JButton(Texture.IconZoomOut);
		zoomOut.setToolTipText("Zoom out dans la zone de dessin");
		zoomOut.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		zoomOut.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		zoomOut.addActionListener(new ButtonBarreOutilsControler());

		tracerPoint = new JButton(Texture.IconPoint);
		tracerPoint.setToolTipText("Tracer un point");
		tracerPoint.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerPoint.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerPoint.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerPoint, App.Outils.POINT);

		tracerSegment = new JButton(Texture.IconSegment);
		tracerSegment.setToolTipText("Tracer un segment");
		tracerSegment.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerSegment.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerSegment.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerSegment, App.Outils.SEGMENT);

		tracerSegmentD = new JButton(Texture.IconSegmentD);
		tracerSegmentD.setToolTipText("Tracer un segment à partir de 2 points");
		tracerSegmentD.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerSegmentD.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerSegmentD.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerSegmentD, App.Outils.SEGMENT_POINTS);

		tracerDroite = new JButton(Texture.IconDroite);
		tracerDroite.setToolTipText("Tracer une droite");
		tracerDroite.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerDroite.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerDroite.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerDroite, App.Outils.DROITE);

		tracerDroiteD = new JButton(Texture.IconDroiteD);
		tracerDroiteD.setToolTipText("Tracer une droite à partir de 2 points");
		tracerDroiteD.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerDroiteD.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerDroiteD.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerDroiteD, App.Outils.DROITE_POINTS);

		tracerRectangle = new JButton(Texture.IconRectangle);
		tracerRectangle.setToolTipText("Tracer un rectangle");
		tracerRectangle.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerRectangle.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerRectangle.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerRectangle, App.Outils.RECTANGLE);

		tracerPointIntersection = new JButton(Texture.IconPointIntersection);
		tracerPointIntersection.setToolTipText("Ajoute un point à l'intersection de deux droites/segments");
		tracerPointIntersection.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerPointIntersection.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerPointIntersection.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerPointIntersection, App.Outils.POINTINTERSECTION);

		tracerBarycentre = new JButton(Texture.IconBarycentre);
		tracerBarycentre.setToolTipText("Ajoute un point barycentre des points séléctionnés");
		tracerBarycentre.setMaximumSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerBarycentre.setPreferredSize(new Dimension(ICON_SIZE,ICON_SIZE));
		tracerBarycentre.addActionListener(new ButtonBarreOutilsControler());
		App.getMappageBoutons().put(tracerBarycentre, App.Outils.BARYCENTRE);

		JPanel[] espaces = new JPanel[3];
		for(int i = 0; i < espaces.length; i++){
			espaces[i] = new JPanel();
			espaces[i].setPreferredSize(new Dimension(32,32));
			espaces[i].setMaximumSize(new Dimension(32,32));
		}


		add(moveFigure);
		add(moveCadre);
		add(espaces[0]);
		add(zoomIn);
		add(zoomOut);
		add(espaces[2]);
		add(tracerPoint);
		add(tracerSegment);
		add(tracerSegmentD);
		add(tracerDroite);
		add(tracerDroiteD);
		add(tracerRectangle);
		add(espaces[1]);
		add(tracerPointIntersection);
		add(tracerBarycentre);

		boutons = new ArrayList<JButton>();
		boutons.add(tracerPoint);
		boutons.add(moveFigure);
		boutons.add(tracerSegment);
		boutons.add(tracerSegmentD);
		boutons.add(tracerDroite);
		boutons.add(tracerDroiteD);
		boutons.add(tracerRectangle);
		boutons.add(tracerPointIntersection);
		boutons.add(moveCadre);
		boutons.add(tracerBarycentre);

	}

	public ArrayList<JButton> getBoutons(){
		return boutons;
	}

	/**
	 * Active tous les butons
	 */
	public void enableAll(){
		for(JButton b : boutons)
			b.setEnabled(true);
	}

	public JButton getZoomInButton(){
		return zoomIn;
	}

	public JButton getZoomOutButton(){
		return zoomOut;
	}
	
}