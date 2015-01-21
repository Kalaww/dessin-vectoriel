package com.modeles;

/**
 * Implementation d'un point d'intersection
 */
public class PointIntersection extends Point implements Suiveur{
	
	/**
	 * Droite 1
	 */
	private Droite d1;

	/**
	 * Droite 2
	 */
	private Droite d2;

	/**
	 * Constructeur
	 * @param  a droite 1
	 * @param  b droite 2
	 */
	public PointIntersection(Droite a, Droite b){
		super();
		d1 = a;
		d2 = b;
		nom = "Point d'intersection";
		calculCoord();
	}

	public void suivre(double deltaX, double deltaY, Figure source){
		if(canMove){
			canMove = false;
			x += deltaX;
			y += deltaY;

			if(source == d1){
				if(d2 instanceof Deplacable) ((Deplacable)d2).move(deltaX, deltaY);
				else if(d2 instanceof Suiveur) ((Suiveur)d2).suivre(deltaX, deltaY, this);
			}else{
				if(d1 instanceof Deplacable) ((Deplacable)d1).move(deltaX, deltaY);
				else if(d1 instanceof Suiveur) ((Suiveur)d1).suivre(deltaX, deltaY, this);
			}

			for(Object p : barycentres.toArray()){
				if(p != source){
					if(p instanceof Deplacable) ((Deplacable)p).move(deltaX, deltaY);
					else if(p instanceof Suiveur) ((Suiveur)p).suivre(deltaX, deltaY, this);
				}
			}
		}
	}

	/**
	 * Calcul des coordonnées du point d'intersection
	 */
	public void calculCoord(){
		d1.getPointIntersections().add(this);
		d2.getPointIntersections().add(this);
		Point p = d1.intersection(d2);
		x = p.x;
		y = p.y;
	}
}