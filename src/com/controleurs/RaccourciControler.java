package com.controleurs;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import com.App;
import com.modeles.PointBarycentre;
import com.modeles.Point;

/**
 * Controleur des raccourcis clavier
 */
@SuppressWarnings("serial")
public class RaccourciControler extends AbstractAction{

	/**
	 * Si la touche contrôle est pressé
	 */
	public static boolean ctrlPressed = false;

	/**
	 * Enum de tous les raccourcis pris en charge
	 */
	public enum Key{CTRL_PRESSED, CTRL_RELEASED, ENTER, ZOOMIN, ZOOMOUT, NONE};

	/**
	 * Raccourci courant
	 */
	private Key key = Key.NONE;

	/**
	 * Constructeur
	 * @param  k raccourci
	 */
	public RaccourciControler(Key k){
		super();
		key = k;
	}
	
	public void actionPerformed(ActionEvent e){
		if(key.equals(Key.CTRL_PRESSED)){
			ctrlPressed = true;
		}else if(key.equals(Key.CTRL_RELEASED)){
			ctrlPressed = false;
		}else if(key.equals(Key.ENTER) && App.getCurrentOutils().equals(App.Outils.BARYCENTRE)){
			if(App.espace.getSelectedFigures().size() < 2){
				JOptionPane.showMessageDialog(
					App.fenetre, 
					"Veuillez sélectionner au moins deux points pour la création d'un barycentre.", 
					"Création d'un barycentre de points", 
					JOptionPane.WARNING_MESSAGE);
			}else{
				int choix = JOptionPane.showConfirmDialog(
								App.fenetre, 
								"Veuillez confirmer la création du point barycentre",
								"Création d'un point d'intersection",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if(choix != JOptionPane.NO_OPTION && choix != JOptionPane.CLOSED_OPTION){
					PointBarycentre p = new PointBarycentre();
					for(Object k : App.espace.getSelectedFigures().toArray()){
						p.addPoint((Point)k);
					}
					App.espace.addPointBarycentre(p);
				}
				App.espace.cleanSelectedFigures();
				App.fenetre.repaint();
			}
		}else if(key.equals(Key.ZOOMIN)){
			App.fenetre.getZoneDessin().zoomIn();
			App.fenetre.repaint();
		}else if(key.equals(Key.ZOOMOUT)){
			App.fenetre.getZoneDessin().zoomOut();
			App.fenetre.repaint();
		}
	}
}