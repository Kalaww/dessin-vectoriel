package com.modeles;

import java.util.ArrayList;
import java.util.HashSet;

import com.App;

/**
 * Espace qui gère l'ensemble des figures
 */
public class Espace{

	public static final double BOUND_MARGE = 0.02;
	
	/**
	 * Liste des figures
	 */
	private ArrayList<Figure> figures;

	/**
	 * Liste des figures temporaires
	 */
	private ArrayList<Figure> figuresTmp;

	/**
	 * Figure sélectionné
	 */
	private Figure selectedFigure;

	/**
	 * Ensemble de figures sélectionnées
	 */
	private HashSet<Figure> selectedFigures;

	/**
	 * Constructeur
	 */
	public Espace(){
		figures = new ArrayList<Figure>();
		figuresTmp = new ArrayList<Figure>();
		selectedFigures = new HashSet<Figure>();
		selectedFigure = null;
	}

	/**
	 * Getter liste des figures
	 * @return liste des figures
	 */
	public ArrayList<Figure> getFigures(){
		return figures;
	}

	/**
	 * Getter liste des figures temporaires
	 * @return liste des figures temporaires
	 */
	public ArrayList<Figure> getFiguresTmp(){
		return figuresTmp;
	}

	/**
	 * Récupère la figure correspondant au hashcode
	 * @param  hashcode hashcode de la figure
	 * @return          la figure
	 */
	public Figure getFigureByHashCode(int hashcode){
		for(Figure f : figures){
			if(f.hashCode() == hashcode) return f;
		}
		return null;
	}

	/**
	 * Supprime la figure de l'espace
	 * @param f figure à supprimer
	 */
	public void removeFigure(Figure f){
		f.suppression();
		figures.remove(f);
		if(selectedFigure == f) selectedFigure = null;
	}

	/**
	 * Déselectionne toutes les figures
	 */
	public void unselectAll(){
		for(Figure f : figures){
			f.setSelected(false);
		}
		selectedFigure = null;
	}

	/**
	 * Récupère la figure la plus proche de (a,b) avec pour distance max le pas
	 * @param  a   abscisse
	 * @param  b   ordonnée
	 * @param  pas distance max
	 * @return     la figure la plus proche
	 */
	public Figure getCloserFigure(double a, double b, double pas){
		Figure closer = null;
		double distMin = -1.0;
		double tmp;

		for(Figure f : figures){
			tmp = f.distanceTo(a,b);
			if(tmp <= pas && (distMin < 0 || tmp < distMin)){
				distMin = tmp;
				closer = f;
			}
		}
		return closer;
	}

	/**
	 * Ajoute un point libre
	 * @param a abscisse
	 * @param b ordonnée
	 */
	public void addPointLibre(double a, double b){
		Point p = new PointLibre(a,b);
		figures.add(p);
		App.fenetre.getFiguresOption().addList(p);
	}

	/**
	 * Ajoute un point d'intersection
	 * @param p point d'intersection
	 */
	public void addPointIntersection(PointIntersection p){
		figures.add(p);
		App.fenetre.getFiguresOption().addList(p);
	}

	/**
	 * Ajoute un barycentre
	 * @param p barycentre
	 */
	public void addPointBarycentre(PointBarycentre p ){
		figures.add(p);
		App.fenetre.getFiguresOption().addList(p);
	}

	/**
	 * Ajoute un segment
	 * @param  x1 point 1 abscisse
	 * @param  y1 point 1 ordonnée
	 * @param  x2 point 2 abscisse
	 * @param  y2 point 2 ordonnée
	 * @return le segment
	 */
	public Segment addSegment(double x1, double y1, double x2, double y2){
		Segment s = new Segment(x1, y1, x2, y2);
		figures.add(s);
		App.fenetre.getFiguresOption().addList(s);
		return s;
	}

	/**
	 * Ajoute un segment
	 * @param  a point 1
	 * @param  b point 2
	 * @return   le segment
	 */
	public Segment addSegment(Point a, Point b){
		Segment s = new Segment(a,b);
		figures.add(s);
		App.fenetre.getFiguresOption().addList(s);
		return s;
	}

	/**
	 * Ajoute un segment temporaire
	 * @param  x1 point 1 abscisse
	 * @param  y1 point 1 ordonnée
	 * @param  x2 point 2 abscisse
	 * @param  y2 point 2 ordonnée
	 * @return le segment
	 */
	public Segment addSegmentTmp(double x1, double y1, double x2, double y2){
		Segment s = new Segment(x1, y1, x2, y2);
		figuresTmp.add(s);
		return s;
	}

	/**
	 * Ajoute une droite
	 * @param  x1 point 1 abscisse
	 * @param  y1 point 1 ordonnée
	 * @param  x2 point 2 abscisse
	 * @param  y2 point 2 ordonnée
	 * @return la droite
	 */
	public Droite addDroite(double x1, double y1, double x2, double y2){
		Droite d = new Droite(x1, y1, x2, y2);
		figures.add(d);
		App.fenetre.getFiguresOption().addList(d);
		return d;
	}

	/**
	 * Ajoute droite
	 * @param  a point 1
	 * @param  b point 2
	 * @return   la droite
	 */
	public Droite addDroite(Point a, Point b){
		Droite d = new Droite(a,b);
		figures.add(d);
		App.fenetre.getFiguresOption().addList(d);
		return d;
	}

	/**
	 * Ajoute une droite temporaire
	 * @param  x1 point 1 abscisse
	 * @param  y1 point 1 ordonnée
	 * @param  x2 point 2 abscisse
	 * @param  y2 point 2 ordonnée
	 * @return la droite
	 */
	public Droite addDroiteTmp(double x1, double y1, double x2, double y2){
		Droite d = new Droite(x1, y1, x2, y2);
		figuresTmp.add(d);
		return d;
	}

	/**
	 * Ajoute une droite temporaire
	 * @param d la droite
	 */
	public void addDroiteTmp(Droite d){
		figuresTmp.add(d);
	}

	/**
	 * Ajoute un rectangle
	 * @param r le rectangle
	 */
	public void addRectangle(Rectangle r){
		figures.add(r);
		App.fenetre.getFiguresOption().addList(r);
	}

	/**
	 * Change la figure sélectionnée par celle en argument
	 * @param f nouvelle figure sélectionnée
	 */
	public void setSelectedFigure(Figure f){
		unselectAll();
		selectedFigure = f;
		f.setSelected(true);
		App.fenetre.getFiguresOption().changeSelected(f);
	}

	/**
	 * Getter figure sélectionnée
	 * @return figure sélectionnée
	 */
	public Figure getSelectedFigure(){
		return selectedFigure;
	}

	/**
	 * Getter liste des figures sélectionnées
	 * @return liste des figures sélectionnées
	 */
	public HashSet<Figure> getSelectedFigures(){
		return selectedFigures;
	}

	/**
	 * Ajoute une figure à la liste des figures sélectionnées
	 * @param f figure à ajouter
	 */
	public void addSelectedFigures(Figure f){
		f.setSelected(true);
		selectedFigures.add(f);
	}

	/**
	 * Vide la liste des figures sélectionnées
	 */
	public void cleanSelectedFigures(){
		for(Figure f : selectedFigures) f.setSelected(false);
		selectedFigures.clear();
	}

	/**
	 * Déplace la figure sélectionnée
	 * @param deltaX déplacement abscisse
	 * @param deltaY déplacement ordonnée
	 */
	public void moveSelectedFigure(double deltaX, double deltaY){
		if(selectedFigure != null && selectedFigure instanceof Deplacable)
			((Deplacable)selectedFigure).move(deltaX, deltaY);

		for(Figure k : figures){
			k.resetCanMove();
		}

		for(Figure k : figuresTmp){
			k.resetCanMove();
		}
	}

	/**
	 * Déplace la figure en argument
	 * @param f      figure à déplacer
	 * @param deltaX déplacement en abscisse
	 * @param deltaY déplacement en ordonnée
	 */
	public void moveFigure(Figure f, double deltaX, double deltaY){
		if(f != null && f instanceof Deplacable)
			((Deplacable)f).move(deltaX, deltaY);

		for(Figure k : figures){
			k.resetCanMove();
		}

		for(Figure k : figuresTmp){
			k.resetCanMove();
		}
	}
}