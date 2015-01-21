package com.vues;

import javax.swing.ImageIcon;

import java.awt.Image;

import javax.imageio.ImageIO;

import java.lang.Exception;

/**
 * Texture charge toutes les ressources utlisées : essentiellement les images des boutons.
 */
public class Texture{
	
	public static final String RES_PATH = "/com/res/";

	public static ImageIcon IconMove;

	public static ImageIcon IconMoveCadre;

	public static ImageIcon IconPoint;

	public static ImageIcon IconSegment;

	public static ImageIcon IconSegmentD;

	public static ImageIcon IconDroite;

	public static ImageIcon IconDroiteD;

	public static ImageIcon IconRectangle;

	public static ImageIcon IconPointIntersection;

	public static ImageIcon IconBarycentre;

	public static ImageIcon IconZoomIn;

	public static ImageIcon IconZoomOut;

	public static void init(){
		try{
			IconMove = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"move.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"move.png");
		}

		try{
			IconMoveCadre = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"move_cadre.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"move_cadre.png");
		}

		try{
			IconPoint = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"point.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"point.png");
		}

		try{
			IconSegment = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"segment.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"segment.png");
		}

		try{
			IconSegmentD = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"segment_d.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"segment_d.png");
		}

		try{
			IconDroite = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"droite.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"droite.png");
		}

		try{
			IconDroiteD = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"droite_d.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"droite_d.png");
		}

		try{
			IconRectangle = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"rectangle.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"rectangle.png");
		}

		try{
			IconPointIntersection = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"point_inter.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"point_inter.png");
		}

		try{
			IconBarycentre = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"point_bary.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"point_bary.png");
		}

		try{
			IconZoomOut = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"zoom_out.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"zoom_out.png");
		}

		try{
			IconZoomIn = new ImageIcon(ImageIO.read(Texture.class.getResource(RES_PATH+"zoom_in.png")));
		}catch (Exception e){
			System.out.println("Impossible de charger l'image "+RES_PATH+"zoom_in.png");
		}
	}
}