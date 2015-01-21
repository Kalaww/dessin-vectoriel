package com.controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;
import javax.swing.JOptionPane;

import com.modeles.*;
import com.vues.ZoneDessin;
import com.App;

/**
 * Controleur de la zone de dessin
 */
public class ZoneDessinControler implements MouseInputListener, MouseWheelListener{

	/**
	 * Valeur ordonnée 0 dans l'espace des figures
	 */
	private static int xOld;

	/**
	 * Valeur abscisse 0 dans l'espace des figures
	 */
	private static int yOld;

	/**
	 * Etape dans la création d'un rectangle
	 */
	private static int etapeRectangle = 0;

	/**
	 * Figure courant en cours de tracer
	 */
	private static Figure currentFigure = null;

	/**
	 * Segment temporaire pour certain tracé
	 */
	private static Segment tmpSegment = null;

	/**
	 * Droite temporaire pour certain tracé
	 */
	private static Droite tmpDroite = null;
	

	public void mouseClicked(MouseEvent e){
		double xT = e.getX()*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getXOrigine();
		double yT = e.getY()*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getYOrigine();

		if(App.getCurrentOutils().equals(App.Outils.POINT)
			&& e.getButton() == MouseEvent.BUTTON1){
			App.espace.addPointLibre(xT, yT);
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.MOVE)
			&& e.getButton() == MouseEvent.BUTTON1){
			Figure f = App.espace.getCloserFigure(xT, yT, App.fenetre.getZoneDessin().pas());
			if(f != null) App.espace.setSelectedFigure(f);
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.RECTANGLE)
			&& e.getButton() == MouseEvent.BUTTON1
			&& etapeRectangle == 3){
			App.espace.getFiguresTmp().clear();
			tmpDroite = null;
			currentFigure = null;
			etapeRectangle = 0;
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.SELECT)
			&& e.getButton() == MouseEvent.BUTTON1){
			Figure f = App.espace.getCloserFigure(xT, yT, App.fenetre.getZoneDessin().pas());
			if(f != null){
				if(RaccourciControler.ctrlPressed){
					App.espace.addSelectedFigures(f);
					App.espace.addSelectedFigures(App.espace.getSelectedFigure());
				}else{
					App.espace.cleanSelectedFigures();
					App.espace.setSelectedFigure(f);
				}
			}
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.POINTINTERSECTION)
			&& e.getButton() == MouseEvent.BUTTON1){
			Figure f = App.espace.getCloserFigure(xT, yT, App.fenetre.getZoneDessin().pas());
			if(f != null && f instanceof Droite){
				App.espace.addSelectedFigures(f);
				App.fenetre.repaint();
			}else if(f != null && f instanceof Rectangle){
				Segment s = ((Rectangle)f).getCloserSegment(xT, yT, App.fenetre.getZoneDessin().pas());
				App.espace.addSelectedFigures(s);
				App.fenetre.repaint();
			}else if(f != null){
				JOptionPane.showMessageDialog(
					App.fenetre, 
					"Veuillez sélectionner que des droites ou des segments pour la création du point d'intersection.", 
					"Création d'un point d'intersection", 
					JOptionPane.WARNING_MESSAGE);
			}

			if(App.espace.getSelectedFigures().size() == 2){
				Droite []d = new Droite[2];
				int i = 0;
				for(Object k : App.espace.getSelectedFigures().toArray()){
					d[i++] = (Droite)k;
				}
				if(d[0].coefficientDirecteur() == d[1].coefficientDirecteur()){
					JOptionPane.showMessageDialog(
					App.fenetre, 
					"Les deux éléments sélectionnés sont parallèles.", 
					"Création d'un point d'intersection", 
					JOptionPane.WARNING_MESSAGE);
				}else{
					int choix = JOptionPane.showConfirmDialog(
									App.fenetre, 
									"Veuillez confirmer la création du point d'intersection entre :\n - "+d[0].getNom()+"\n - "+d[1].getNom(),
									"Création d'un point d'intersection",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
					if(choix != JOptionPane.NO_OPTION && choix != JOptionPane.CLOSED_OPTION){
						App.espace.addPointIntersection(new PointIntersection(d[0], d[1]));
					}
				}
				App.espace.cleanSelectedFigures();
			}
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.SEGMENT_POINTS)
			&& e.getButton() == MouseEvent.BUTTON1){
			Figure f = App.espace.getCloserFigure(xT, yT, App.fenetre.getZoneDessin().pas());
			if(f != null && f instanceof Point){
				App.espace.addSelectedFigures(f);
				App.fenetre.repaint();
			}else if(f != null){
				JOptionPane.showMessageDialog(
					App.fenetre, 
					"Veuillez sélectionner que des points.", 
					"Création d'un segment à partir de 2 points", 
					JOptionPane.WARNING_MESSAGE);
			}

			if(App.espace.getSelectedFigures().size() == 2){
				Point []d = new Point[2];
				int i = 0;
				for(Object k : App.espace.getSelectedFigures().toArray()){
					d[i++] = (Point)k;
				}
				if(d[0].getX() == d[1].getX() && d[0].getY() == d[1].getY()){
					JOptionPane.showMessageDialog(
					App.fenetre, 
					"Les deux éléments sélectionnés ont les mêmes coordonnées.", 
					"Création d'un segment à partir de 2 points", 
					JOptionPane.WARNING_MESSAGE);
				}else{
					int choix = JOptionPane.showConfirmDialog(
									App.fenetre, 
									"Veuillez confirmer la création du segment à partir de :\n - "+d[0].getNom()+"\n - "+d[1].getNom(),
									"Création d'un segment à partir de 2 points",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
					if(choix != JOptionPane.NO_OPTION && choix != JOptionPane.CLOSED_OPTION){
						App.espace.addSegment(d[0], d[1]);
					}
				}
				App.espace.cleanSelectedFigures();
			}
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.DROITE_POINTS)
			&& e.getButton() == MouseEvent.BUTTON1){
			Figure f = App.espace.getCloserFigure(xT, yT, App.fenetre.getZoneDessin().pas());
			if(f != null && f instanceof Point){
				App.espace.addSelectedFigures(f);
				App.fenetre.repaint();
			}else if(f != null){
				JOptionPane.showMessageDialog(
					App.fenetre, 
					"Veuillez sélectionner que des points.", 
					"Création d'une droite à partir de 2 points", 
					JOptionPane.WARNING_MESSAGE);
			}

			if(App.espace.getSelectedFigures().size() == 2){
				Point []d = new Point[2];
				int i = 0;
				for(Object k : App.espace.getSelectedFigures().toArray()){
					d[i++] = (Point)k;
				}
				if(d[0].getX() == d[1].getX() && d[0].getY() == d[1].getY()){
					JOptionPane.showMessageDialog(
					App.fenetre, 
					"Les deux éléments sélectionnés ont les mêmes coordonnées.", 
					"Création d'une droite à partir de 2 points", 
					JOptionPane.WARNING_MESSAGE);
				}else{
					int choix = JOptionPane.showConfirmDialog(
									App.fenetre, 
									"Veuillez confirmer la création de la droite à partir de :\n - "+d[0].getNom()+"\n - "+d[1].getNom(),
									"Création d'une droite à partir de 2 points",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);
					if(choix != JOptionPane.NO_OPTION && choix != JOptionPane.CLOSED_OPTION){
						App.espace.addDroite(d[0], d[1]);
					}
				}
				App.espace.cleanSelectedFigures();
			}
			App.fenetre.repaint();
		}

		else if(App.getCurrentOutils().equals(App.Outils.BARYCENTRE)
			&& e.getButton() == MouseEvent.BUTTON1){
			Figure f = App.espace.getCloserFigure(xT, yT, App.fenetre.getZoneDessin().pas());
			if(f != null && f instanceof Point && !(f instanceof PointBarycentre)){
				App.espace.addSelectedFigures(f);
				App.fenetre.repaint();
			}else if(f != null){
				JOptionPane.showMessageDialog(
					App.fenetre, 
					"Veuillez sélectionner que des points (non barycentre) pour la création d'un barycentre.", 
					"Création d'un barycentre de points", 
					JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){
		xOld = e.getX();
		yOld = e.getY();
	}

	public void mouseReleased(MouseEvent e){
		if(App.getCurrentOutils().equals(App.Outils.RECTANGLE) && tmpSegment != null && etapeRectangle == 1){
			tmpDroite = tmpSegment.perpendiculaire(tmpSegment.getP2());
			//App.espace.addDroiteTmp(tmpDroite);
			App.espace.getFiguresTmp().remove(tmpSegment);

			currentFigure = new Rectangle(	tmpSegment.getP1().getX(), 
											tmpSegment.getP1().getY(),
											tmpSegment.getP2().getX(),
											tmpSegment.getP2().getY(),
											tmpSegment.getP2().getX(),
											tmpSegment.getP2().getY(),
											tmpSegment.getP1().getX(),
											tmpSegment.getP1().getY());
			App.espace.addRectangle((Rectangle)currentFigure);
			tmpSegment = null;
			etapeRectangle = 2;
			App.fenetre.repaint();
		}else if(!App.getCurrentOutils().equals(App.Outils.RECTANGLE)){
			currentFigure = null;
		}
	}

	public void mouseDragged(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		double xT = x*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getXOrigine();
		double yT = y*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getYOrigine();
		double xOldT = xOld*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getXOrigine();
		double yOldT = yOld*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getYOrigine();
		double deltaX = xT - xOldT;
		double deltaY = yT - yOldT;

		App.fenetre.getStatusBar().updateCoord(xT, yT);
		
		if(App.getCurrentOutils().equals(App.Outils.MOVE)){
			App.espace.moveSelectedFigure(deltaX, deltaY);
			App.fenetre.repaint();
		}
		else if(App.getCurrentOutils().equals(App.Outils.SEGMENT)){
			if(currentFigure == null){
				currentFigure = App.espace.addSegment(xOldT, yOldT, xT, yT);
			}else{
				Segment s = (Segment) currentFigure;
				App.espace.moveFigure(s.getP2(), deltaX, deltaY);
			}
			App.fenetre.repaint();
		}
		else if(App.getCurrentOutils().equals(App.Outils.DROITE)){
			if(currentFigure == null){
				currentFigure = App.espace.addDroite(xOldT, yOldT, xT, yT);
			}else{
				Droite d = (Droite) currentFigure;
				App.espace.moveFigure(d.getP2(), deltaX, deltaY);
			}
			App.fenetre.repaint();
		}else if(App.getCurrentOutils().equals(App.Outils.RECTANGLE) && (etapeRectangle == 0 || etapeRectangle == 1)){
			if(tmpSegment == null){
				etapeRectangle = 1;
				tmpSegment = App.espace.addSegmentTmp(xOldT, yOldT, xT, yT);
			}else{
				App.espace.moveFigure(tmpSegment.getP2(), deltaX, deltaY);
			}
			App.fenetre.repaint();
		}else if(App.getCurrentOutils().equals(App.Outils.MOVE_CADRE)){
			App.fenetre.getZoneDessin().moveOrigine(deltaX, deltaY);
			App.fenetre.repaint();
		}
		xOld = x;
		yOld = y;
	}

	public void mouseMoved(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		double xT = x*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getXOrigine();
		double yT = y*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getYOrigine();
		double xOldT = xOld*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getXOrigine();
		double yOldT = yOld*App.fenetre.getZoneDessin().getEchelle() - App.fenetre.getZoneDessin().getYOrigine();
		double deltaX = xT - xOldT;
		double deltaY = yT - yOldT;

		App.fenetre.getStatusBar().updateCoord(xT, yT);

		if(App.getCurrentOutils().equals(App.Outils.RECTANGLE) && currentFigure != null){
			Point p1 = tmpDroite.perpendiculaire(new PointLibre(xT, yT)).intersection(tmpDroite);

			Rectangle r = (Rectangle) currentFigure;
			Point p2 = tmpDroite.perpendiculaire(new PointLibre(xT,yT)).intersection(r.getS1().perpendiculaire(r.getS1().getP1()));
			r.setS3(new Segment(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
			etapeRectangle = 3;
			App.fenetre.repaint();
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e){
		int n = e.getWheelRotation();
		int x = e.getX();
		int y = e.getY();
		double xT = x*App.fenetre.getZoneDessin().getEchelle();
		double yT = y*App.fenetre.getZoneDessin().getEchelle();

		if(n < 0){
			App.fenetre.getZoneDessin().moveOrigine(-xT*0.2, -yT*0.2);
			App.fenetre.getZoneDessin().zoomOut();
		}else{
			App.fenetre.getZoneDessin().moveOrigine(0.2*xT, 0.2*yT);
			App.fenetre.getZoneDessin().zoomIn();
		}
		App.fenetre.repaint();
	}
}