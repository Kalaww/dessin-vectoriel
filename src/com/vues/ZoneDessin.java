package com.vues;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import java.lang.Math;

import com.App;
import com.modeles.*;
import com.controleurs.ZoneDessinControler;
import com.controleurs.RaccourciControler;

/**
 * Zone de dessin des figures
 */
@SuppressWarnings("serial")
public class ZoneDessin extends JPanel{

	/**
	 * Couleur pour le tracé de la figure sélectionnée
	 */
	private static Color colorSelect = Color.RED;

	/**
	 * Echelle d'affichage pour le zoom
	 */
	private double echelle;

	/**
	 * Valeur de l'abscisse 0 dans l'espace des figures
	 */
	private double xOrigine;

	/**
	 * Valeur de l'ordonnée 0 dans l'espace des figures
	 */
	private double yOrigine;
	
	/**
	 * Constructeur
	 */
	public ZoneDessin(){
		super();
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		echelle = 1.0;
		xOrigine = 0.0;
		yOrigine = 0.0;
		ZoneDessinControler s = new ZoneDessinControler();
		addMouseListener(s);
		addMouseMotionListener(s);
		addMouseWheelListener(s);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl pressed CONTROL"), "ctrl pressed");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released CONTROL"), "ctrl released");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enter");
		//getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "zoom_in");
		//getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Z"), "zoom_out");
		getActionMap().put("ctrl pressed", new RaccourciControler(RaccourciControler.Key.CTRL_PRESSED));
		getActionMap().put("ctrl released", new RaccourciControler(RaccourciControler.Key.CTRL_RELEASED));
		getActionMap().put("enter", new RaccourciControler(RaccourciControler.Key.ENTER));
		//getActionMap().put("zoom_in", new RaccourciControler(RaccourciControler.Key.ZOOMIN));
		//getActionMap().put("zoom_out", new RaccourciControler(RaccourciControler.Key.ZOOMOUT));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(Figure f : App.espace.getFigures()){
			if(f instanceof PointBarycentre){
				drawPointBarycentre(g, (PointBarycentre)f);
			}else if(f instanceof Point){
				drawPoint(g, (Point)f);
			}else if(f instanceof Rectangle){
				drawRectangle(g, (Rectangle)f);
			}else if(f instanceof Segment){
				drawSegment(g, (Segment)f, true);
			}else if(f instanceof Droite){
				drawDroite(g, (Droite)f);
			}
		}

		for(Figure f : App.espace.getFiguresTmp()){
			if(f instanceof Point){
				drawPoint(g, (Point)f);
			}else if(f instanceof Rectangle){
				drawRectangle(g, (Rectangle)f);
			}else if(f instanceof Segment){
				drawSegment(g, (Segment)f, true);
			}else if(f instanceof Droite){
				drawDroite(g, (Droite)f);
			}
		}
	}

	/**
	 * Dessine un point
	 * @param g graphics
	 * @param p point
	 */
	private void drawPoint(Graphics g, Point p){
		int scale = 10;
		int xP = (int) (p.getX() / echelle + xOrigine);
		int yP = (int) (p.getY() / echelle + yOrigine);
		if(p.isSelected()){
			int scaleS = 4;
			g.setColor(colorSelect);
			g.fillOval(xP - (scaleS+scale)/2, yP - (scaleS+scale)/2, scaleS+scale, scaleS+scale);
		}
		g.setColor(Color.GRAY);
		g.fillOval(xP - scale/2, yP - scale/2, scale, scale);
		
	}

	/**
	 * Dessine un barycentre
	 * @param g graphics
	 * @param p barycentre
	 */
	private void drawPointBarycentre(Graphics g, PointBarycentre p){
		int scale = 10, scaleS = 4;
		int xP = (int) (p.getX() / echelle + xOrigine);
		int yP = (int) (p.getY() / echelle + yOrigine);

		for(Object k : p.getPointsLies().toArray()){
			int xK = (int) (((Point)k).getX() / echelle + xOrigine);
			int yK = (int) (((Point)k).getY() / echelle + yOrigine);
			if(((Point)k).isSelected()){
				g.setColor(colorSelect);
				g.fillOval(xK - (scaleS+scale)/2, yK - (scaleS+scale)/2, scaleS+scale, scaleS+scale);
			}
			g.setColor(new Color(220,220,220));
			g.drawLine(xP, yP, xK, yK);

			g.setColor(Color.GRAY);
			g.fillOval(xK - scale/2, yK - scale/2, scale, scale);
		}

		if(p.isSelected()){
			g.setColor(colorSelect);
			g.fillOval(xP - (scaleS+scale)/2, yP - (scaleS+scale)/2, scaleS+scale, scaleS+scale);
		}
		g.setColor(Color.GRAY);
		g.fillOval(xP - scale/2, yP - scale/2, scale, scale);
	}

	/**
	 * Dessine un segment
	 * @param g            graphics
	 * @param s            segment
	 * @param affichePoint activer l'affichage des points aux extrémités du segment
	 */
	private void drawSegment(Graphics g, Segment s, boolean affichePoint){
		int xp1 = (int) (s.getP1().getX() / echelle + xOrigine);
		int yp1 = (int) (s.getP1().getY() / echelle + yOrigine);
		int xp2 = (int) (s.getP2().getX() / echelle + xOrigine);
		int yp2 = (int) (s.getP2().getY() / echelle + yOrigine);

		g.setColor(Color.GRAY);
		g.drawLine(xp1, yp1, xp2, yp2);
		if(s.isSelected()){
			g.setColor(colorSelect);
			int p = 1;
			g.drawLine(xp1+p, yp1+p, xp2+p, yp2+p);
			g.drawLine(xp1-p, yp1-p, xp2-p, yp2-p);
		}

		if(affichePoint){
			drawPoint(g, s.getP1());
			drawPoint(g, s.getP2());
		}
	}

	/**
	 * Dessine une droite
	 * @param g graphics
	 * @param d droite
	 */
	private void drawDroite(Graphics g, Droite d){
		double xp1 = (d.getP1().getX() / echelle + xOrigine);
		double yp1 = (d.getP1().getY() / echelle + yOrigine);
		double xp2 = (d.getP2().getX() / echelle + xOrigine);
		double yp2 = (d.getP2().getY() / echelle + yOrigine);

		if(xp1 == xp2){
			yp1 = 0;
			yp2 = getHeight();
		}else{
			double coeffD = (yp2 - yp1) / (xp2 - xp1);
			double ordO = (yp1 - coeffD*xp1);
			xp1 = 0;
			xp2 = getWidth();
			yp1 = ordO;
			yp2 = (coeffD*xp2 + ordO);
		}

		g.setColor(Color.GRAY);
		g.drawLine((int)xp1, (int)yp1, (int)xp2, (int)yp2);
		if(d.isSelected()){
			g.setColor(colorSelect);
			int p = 1;
			g.drawLine((int)xp1+p, (int)yp1+p, (int)xp2+p, (int)yp2+p);
			g.drawLine((int)xp1-p, (int)yp1-p, (int)xp2-p, (int)yp2-p);
		}

	}

	/**
	 * Dessine un rectangle
	 * @param g graphics
	 * @param r rectangle
	 */
	public void drawRectangle(Graphics g, Rectangle r){
		drawSegment(g, r.getS1(), false);
		drawSegment(g, r.getS2(), false);
		drawSegment(g, r.getS3(), false);
		drawSegment(g, r.getS4(), false);
	}

	/**
	 * Déplace l'origine
	 * @param deltaX déplacement abscisse
	 * @param deltaY déplacement ordonnée
	 */
	public void moveOrigine(double deltaX, double deltaY){
		xOrigine += echelle*deltaX;
		yOrigine += echelle*deltaY;
	}

	/**
	 * Marge d'erreur pour sélectionner les figures
	 * @return la marge d'erreur
	 */
	public double pas(){
		return Math.min(getWidth()*echelle*0.02, getHeight()*echelle*0.02);
	}

	/**
	 * Zoom in
	 */
	public void zoomIn(){
		echelle *= 1.2;
		App.fenetre.getStatusBar().updateZoom(echelle);
	}

	/**
	 * Zoom out
	 */
	public void zoomOut(){
		echelle *= 0.8;
		App.fenetre.getStatusBar().updateZoom(echelle);
	}

	/**
	 * Getter échelle
	 * @return échelle
	 */
	public double getEchelle(){
		return echelle;
	}

	/**
	 * Getter origine abscisse
	 * @return origine abscisse
	 */
	public double getXOrigine(){
		return xOrigine;
	}

	/**
	 * Getter origine ordonnée
	 * @return origine ordonnée
	 */
	public double getYOrigine(){
		return yOrigine;
	}
}