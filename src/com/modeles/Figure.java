package com.modeles;

import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 *  Implementation d'une figure
 */
public abstract class Figure implements Cloneable{

	/**
	 * Hashcode initial
	 */
	private static int HASH_INIT = 1;

	/**
	 * Hashcode
	 */
	protected int hashcode;

	/**
	 * Vrai si la figure est sélectionné
	 */
	protected boolean selected;

	/**
	 * Nom de la figure
	 */
	protected String nom;

	/**
	 * Figure peut se déplacer
	 */
	protected boolean canMove;

	/**
	 * Figure parent
	 */
	protected Figure parent;

	/**
	 * Constructeur
	 */
	public Figure(){
		hashcode = HASH_INIT++;
		selected = false;
		nom = "Figure";
		canMove = true;
		parent = null;
	}

	/**
	 * Distance entre la figure et le point (a,b)
	 * @param  a abscisse
	 * @param  b ordonnée
	 * @return  distance
	 */
	public abstract double distanceTo(double a, double b);

	/**
	 * Redonne le droit à la figure de bouger
	 */
	public void resetCanMove(){
		canMove = true;
	}

	/**
	 * Suppression des liaisons de la figure avec d'autres
	 */
	public void suppression(){}

	/**
	 * Getter nom
	 * @return nom
	 */
	public String getNom(){
		return nom;
	}

	/**
	 * Getter parent
	 * @return parent
	 */
	public Figure getParent(){
		return parent;
	}

	/**
	 * Setter parent
	 * @param f nouveau parent
	 */
	public void setParent(Figure f){
		parent = f;
	}

	/**
	 * Si la figure est sélectionnée
	 * @return vrai si sélectionnée
	 */
	public boolean isSelected(){
		return selected;
	}

	/**
	 * Setter nom
	 * @param n nouveau nom
	 */
	public void setNom(String n){
		nom = n;
	}

	/**
	 * Setter est sélectionné
	 * @param b true si sélectionné, false sinon
	 */
	public void setSelected(boolean b){
		selected = b;
	}

	public String toString(){
		return nom;
	}

	public int hashCode(){
		return hashcode;
	}

	public Figure clone(){
		Figure f = null;
		try{
			f = (Figure) super.clone();
		} catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return f;
	}
	
}