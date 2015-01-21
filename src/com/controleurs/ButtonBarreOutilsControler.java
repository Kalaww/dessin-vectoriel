package com.controleurs;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.App;
import com.vues.Texture;

/**
 * Controleur de la barre de boutons
 */
public class ButtonBarreOutilsControler implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
		JButton b = (JButton) e.getSource();

		if(b == App.fenetre.getBarreOutils().getZoomInButton()){
			App.fenetre.getZoneDessin().zoomIn();
			App.fenetre.repaint();
		}else if(b == App.fenetre.getBarreOutils().getZoomOutButton()){
			App.fenetre.getZoneDessin().zoomOut();
			App.fenetre.repaint();
		}else{
			App.fenetre.getBarreOutils().enableAll();
			b.setEnabled(false);
			App.setCurrentOutils(b);

			if(App.getCurrentOutils().equals(App.Outils.POINTINTERSECTION)){
				App.fenetre.getStatusBar().updateInfo("Cliquez sur deux droites ou segments pour creer leur point d'intersection");
			}else if(App.getCurrentOutils().equals(App.Outils.MOVE)){
				App.fenetre.getStatusBar().updateInfo("Cliquez sur une figure pour la sélectionner puis clic-glisser pour la déplacer");
			}else if(App.getCurrentOutils().equals(App.Outils.MOVE_CADRE)){
				App.fenetre.getStatusBar().updateInfo("Cliquez puis glisser pour déplacer la zone de dessin");
			}else if(App.getCurrentOutils().equals(App.Outils.POINT)){
				App.fenetre.getStatusBar().updateInfo("Cliquez pour ajouter un point");
			}else if(App.getCurrentOutils().equals(App.Outils.SEGMENT)){
				App.fenetre.getStatusBar().updateInfo("Cliquez-glisser pour tracer un segment");
			}else if(App.getCurrentOutils().equals(App.Outils.SEGMENT_POINTS)){
				App.fenetre.getStatusBar().updateInfo("Cliquez sur deux points pour creer un segment");
			}else if(App.getCurrentOutils().equals(App.Outils.DROITE)){
				App.fenetre.getStatusBar().updateInfo("Cliquez-glisser pour tracer une droite");
			}else if(App.getCurrentOutils().equals(App.Outils.DROITE_POINTS)){
				App.fenetre.getStatusBar().updateInfo("Cliquez sur deux point pour creer une droite");
			}else if(App.getCurrentOutils().equals(App.Outils.RECTANGLE)){
				App.fenetre.getStatusBar().updateInfo("Cliquez-glisser pour tracer un segment du rectangle puis re-cliquez pour finaliser le tracer");
			}else if(App.getCurrentOutils().equals(App.Outils.BARYCENTRE)){
				App.fenetre.getStatusBar().updateInfo("Cliquez sur tous les points du futur barycentre et validez avec [ENTRER]");
			}else{
				App.fenetre.getStatusBar().updateInfo("");
			}

			if(App.getCurrentOutils().equals(App.Outils.POINTINTERSECTION)
				|| App.getCurrentOutils().equals(App.Outils.BARYCENTRE)
				|| App.getCurrentOutils().equals(App.Outils.SEGMENT_POINTS)
				|| App.getCurrentOutils().equals(App.Outils.DROITE_POINTS)){
				App.espace.cleanSelectedFigures();
				App.espace.unselectAll();
				App.fenetre.repaint();
			}
		}
	}

}