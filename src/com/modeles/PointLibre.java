package com.modeles;

/**
 * Implementation d'un point libre
 */
public class PointLibre extends Point implements Deplacable{

	/**
	 * Constructeur
	 * @param  a abscisse
	 * @param  b oordonnée
	 */
	public PointLibre(double a, double b){
		super(a,b);
		nom = "Point libre";
	}

	public void move(double deltaX, double deltaY){
		if(canMove){
			canMove = false;
			x += deltaX;
			y += deltaY;

			for(Object p : barycentres.toArray()){
				if(p instanceof Deplacable) ((Deplacable)p).move(deltaX, deltaY);
				else if(p instanceof Suiveur) ((Suiveur)p).suivre(deltaX, deltaY, this);
			}
		}
	}
}