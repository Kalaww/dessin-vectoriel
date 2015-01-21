package com;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

//import java.awt.EventQueue;

import java.util.HashMap;

import java.lang.Runnable;

import com.vues.Fenetre;
import com.modeles.Espace;
import com.vues.Texture;

public class App{
	
	public static Fenetre fenetre;

	public static Espace espace;

	private static HashMap<JButton, Outils> mappageBoutons = new HashMap<JButton, Outils>();

	public enum Outils{
		MOVE, 
		MOVE_CADRE, 
		SELECT,
		POINT, 
		SEGMENT,
		SEGMENT_POINTS, 
		DROITE, 
		DROITE_POINTS,
		RECTANGLE, 
		POINTINTERSECTION, 
		BARYCENTRE, 
		NONE};

	private static Outils currentOutils = Outils.NONE;

	public App(){
		Texture.init();
		espace = new Espace();
		fenetre = new Fenetre();
	}

	public static Outils getCurrentOutils(){
		return currentOutils;
	}

	public static HashMap<JButton, Outils> getMappageBoutons(){
		return mappageBoutons;
	}

	public static void setCurrentOutils(Outils a){
		currentOutils = a;
	}

	public static void setCurrentOutils(JButton b){
		currentOutils = mappageBoutons.get(b);
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new App();
			}
		});
	}
}