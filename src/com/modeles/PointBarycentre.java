package com.modeles;

import java.util.HashSet;

import com.App;

/**
 * Implementation d'un barycentre
 */
public class PointBarycentre extends Point implements Suiveur{
	
	/**
	 * Ensemble des points liés à ce barycentre
	 */
	private HashSet<Point> pointsLies;

	/**
	 * Constructeur
	 */
	public PointBarycentre(){
		super();
		nom = "Point barycentre";
		pointsLies = new HashSet<Point>();
	}

	/**
	 * Ajoute un point au barycentre
	 * @param p point à ajouter
	 */
	public void addPoint(Point p){
		pointsLies.add(p);
		p.getBarycentres().add(this);
		calculCoord();
	}

	/**
	 * Getter ensemble des points liés
	 * @return ensemble des points liés
	 */
	public HashSet<Point> getPointsLies(){
		return pointsLies;
	}

	public void suivre(double deltaX, double deltaY, Figure source){
		calculCoord();
	}

	/**
	 * Supprime le point p du barycentre
	 * @param p point à supprimer
	 */
	public void supprimerLien(Point p){
		pointsLies.remove(p);
		if(pointsLies.size() < 2) App.espace.removeFigure(this);
		calculCoord();
	}

	/**
	 * Calcul les coordonnées du barycentre
	 */
	public void calculCoord(){
		double sommeX = 0.0;
		double sommeY = 0.0;

		for(Object p : pointsLies){
			sommeX += ((Point)p).getX();
			sommeY += ((Point)p).getY();
		}

		x = sommeX/pointsLies.size();
		y = sommeY/pointsLies.size();
	}
}