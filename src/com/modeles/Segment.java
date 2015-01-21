package com.modeles;

import java.lang.Math;

/**
 * Implementation d'un segment
 */
public class Segment extends Droite{

	/**
	 * Constructeur
	 * @param  p1x x du point 1
	 * @param  p1y y du point 1
	 * @param  p2x x du point 2
	 * @param  p2y y du point 2
	 */
	public Segment(double p1x, double p1y, double p2x, double p2y){
		this(new PointLibre(p1x, p1y), new PointLibre(p2x, p2y));
	}

	/**
	 * Constructeur
	 * @param  p1 point 1
	 * @param  p2 point 2
	 */
	public Segment(Point p1, Point p2){
		super(p1,p2);
		nom = "Segment";
	}

	/*public double distanceTo(double a, double b){
		if(p2.getX() == p1.getX()){
			if(b >= Math.min(p1.getY(), p2.getY()) && b <= Math.max(p1.getY(), p2.getY())) return Math.abs(p1.getX()-a);
			else{
				return Math.min(p2.distanceTo(a,b), p1.distanceTo(a,b));
			}
		}
		double coeffD = (p2.getY() - p1.getY())/(p2.getX() - p1.getX());
		double ordO = p1.getY() - coeffD*p1.getX();

		Droite k = perpendiculaire(new Point(a,b));
		Point j = intersection(k);
		if()


		double num = Math.abs(coeffD*a - b + ordO);
		double denum = Math.sqrt(coeffD*coeffD +1);
		return num/denum;
	}*/
}