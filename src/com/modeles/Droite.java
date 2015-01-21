package com.modeles;

import java.util.HashSet;

import com.App;

/**
 * Implementation d'une droite
 */
public class Droite extends Figure implements Deplacable{

	/**
	 * Point 1
	 */
	protected Point p1;

	/**
	 * Point 2
	 */
	protected Point p2;

	/**
	 * Ensemble des points d'intersection lié à cette droite
	 */
	protected HashSet<PointIntersection> pointIntersections;


	/**
	 * Constructeur
	 * @param  p1x x du point 1
	 * @param  p1y y du point 1
	 * @param  p2x x du point 2
	 * @param  p2y y du point 2
	 */
	public Droite(double p1x, double p1y, double p2x, double p2y){
		this(new PointLibre(p1x, p1y), new PointLibre(p2x, p2y));
	}

	/**
	 * Constructeur
	 * @param  p1 point 1
	 * @param  p2 point 2
	 */
	public Droite(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
		nom = "Droite";
		pointIntersections = new HashSet<PointIntersection>();
	}

	/**
	 * Distance par rapport au point (a,b)
	 * @param  a [description]
	 * @param  b [description]
	 * @return   [description]
	 */
	public double distanceTo(double a, double b){
		if(p2.getX() == p1.getX()){
			return Math.abs(p1.getX()-a);
		}
		double coeffD = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double ordO = p1.getY() - coeffD*p1.getX();

		double num = Math.abs(coeffD*a - b + ordO);
		double denum = Math.sqrt(coeffD*coeffD +1);
		return num/denum;
	}

	/**
	 * Donne le droit à la droite de se déplacer
	 */
	public void resetCanMove(){
		super.resetCanMove();
		p1.resetCanMove();
		p2.resetCanMove();
	}

	public void move(double deltaX, double deltaY){
		if(canMove){
			canMove = false;
			if(p1 instanceof Deplacable)
				((Deplacable)p1).move(deltaX, deltaY);
			if(p2 instanceof Deplacable)
				((Deplacable)p2).move(deltaX, deltaY);

			for(Object o : pointIntersections.toArray()){
				((PointIntersection)o).suivre(deltaX, deltaY,this);
			}

			if(parent != null) ((Deplacable)parent).move(deltaX, deltaY);
		}
	}

	public void suppression(){
		p1.suppression();
		p2.suppression();
		for(PointIntersection k : pointIntersections)
			App.espace.removeFigure(k);
	}

	/**
	 * Calcul la droite perpendiculaire par rapport à un point
	 * @param  p point par lequel passe la droite perpendiculaire
	 * @return   droite perpendiculaire
	 */
	public Droite perpendiculaire(Point p){
		double coeffD = coefficientDirecteur();
		double ordO = ordonneeAOrigine();

		double coeffDP = -1*(1/coeffD);
		double ordOP = p.getY() - coeffDP*p.getX();

		return new Droite(p.getX(), p.getY(), p.getX()+10, coeffDP*(p.getX()+10) + ordOP);
	}

	/**
	 * Cacul la valeur en ordonnée d'un point selon une abscisse de façon à ce que le point soit sur la droite
	 * @param  x abscisse
	 * @return   ordonnée correspondante
	 */
	public double getYFromX(double x){
		double coeffD = coefficientDirecteur();
		double ordO = ordonneeAOrigine();

		return coeffD*x + ordO;
	}

	/**
	 * Calcul le point d'intersection entre deux droites
	 * @param  s la seconde droite
	 * @return   point d'intersection
	 */
	public Point intersection(Droite s){
		double coeffD = coefficientDirecteur();
		double ordO = ordonneeAOrigine();

		double coeffDS = s.coefficientDirecteur();
		double ordOS = s.ordonneeAOrigine();

		double x = (ordOS - ordO) / (coeffD - coeffDS);
		return new PointLibre(x, coeffD*x + ordO);
	}

	/**
	 * Coefficient directeur de la droite
	 * @return coefficient directeur
	 */
	public double coefficientDirecteur(){
		return (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
	}

	/**
	 * Ordonnée à l'origine de la droite
	 * @return ordonnée à l'origine
	 */
	public double ordonneeAOrigine(){
		return p1.getY() - coefficientDirecteur() * p1.getX();
	}



	public Droite clone(){
		Droite d = null;
		d = (Droite) super.clone();
		d.p1 = (Point)p1.clone();
		d.p2 = (Point)p2.clone();
		return d;
	}



	public Point getP1(){
		for(PointIntersection k : pointIntersections)
			k.calculCoord();
		return p1;
	}

	public Point getP2(){
		for(PointIntersection k : pointIntersections)
			k.calculCoord();
		return p2;
	}



	public void setP1(Point p){
		p1 = p;
		for(PointIntersection k : pointIntersections)
			k.calculCoord();
	}

	public void setP2(Point p){
		p2 = p;
		for(PointIntersection k : pointIntersections)
			k.calculCoord();
	}

	/**
	 * Getter des point d'intersection
	 * @return points d'intersection
	 */
	public HashSet<PointIntersection> getPointIntersections(){
		return pointIntersections;
	}

	public String toString(){
		return super.toString()+": "+p1+"  "+p2;
	}
}