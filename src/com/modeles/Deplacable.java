package com.modeles;

/**
 * Interface pour les figures déplaceables
 */
public interface Deplacable{

	/**
	 * Déplace la figure
	 * @param deltaX déplacement en abscisse
	 * @param deltaY déplacement en ordonnée
	 */
	public void move(double deltaX, double deltaY);
}