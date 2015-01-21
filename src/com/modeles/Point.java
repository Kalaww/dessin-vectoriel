package com.modeles;

import java.lang.Math;

import java.util.HashSet;

import com.App;

/**
 * Implementation du point
 */
public abstract class Point extends Figure{

	/**
	 * Coordonnée en abscisse
	 */
	protected double x;
	
	/**
	 * Coordonnée en ordonnée
	 */
	protected double y;

	/**
	 * Barycentres liés au point
	 */
	protected HashSet<PointBarycentre> barycentres;

	/**
	 * Constructeur
	 */
	public Point(){
		nom = "Point";
		barycentres = new HashSet<PointBarycentre>();
	}

	/**
	 * Constructeur
	 * @param  x abscisse
	 * @param  y ordonnée
	 */
	public Point(double x, double y){
		this();
		this.x = x;
		this.y = y;
	}

	public double distanceTo(double a, double b){
		double xx = Math.pow(x-a, 2);
		double yy = Math.pow(y-b, 2);
		return Math.sqrt(xx+yy);
	}

	/**
	 * Ensemble des barycentres liés
	 * @return [description]
	 */
	public HashSet<PointBarycentre> getBarycentres(){
		return barycentres;
	}

	public void suppression(){
		for(PointBarycentre k : barycentres)
			k.supprimerLien(this);
	}

	/**
	 * Getter abscisse
	 * @return abscisse
	 */
	public double getX(){
		return x;
	}

	/**
	 * Getter ordonnée
	 * @return ordonnée
	 */
	public double getY(){
		return y;
	}


	/**
	 * Setter abscisse
	 * @param x abscisse
	 */
	public void setX(double x){
		for(PointBarycentre k : barycentres)
			k.calculCoord();
		this.x = x;
	}

	/**
	 * Setter ordonnée
	 * @param y ordonnée
	 */
	public void setY(double y){
		for(PointBarycentre k : barycentres)
			k.calculCoord();
		this.y = y;
	}

	public String toString(){
		return super.toString()+"("+x+", "+y+")";
	}
}