package com.modeles;

/**
 * Interface des figures qui suivent le déplacement d'autres figures
 */
public interface Suiveur{
	
	/**
	 * Suit le déplacement de la figure source
	 * @param x      delta en abscisse
	 * @param y      delta en ordonnée
	 * @param source figure à suivre
	 */
	public void suivre(double x, double y, Figure source);
}