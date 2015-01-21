package com.controleurs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.App;
import com.modeles.*;
import com.vues.*;

/**
 * Controleur de la zone d'affichage des détails de la figure sélectionnée
 */
public class DetailFigureControler implements ActionListener{

	private int etat;

	public DetailFigureControler(int k){
		super();
		etat = k;
	}
	
	public void actionPerformed(ActionEvent e){
		//FiguresOption.FigureEntry fe = App.fenetre.getFiguresOption().getListFigures().getSelectedValue();
		DetailFigure d = App.fenetre.getFiguresOption().getDetailFigure();
		Figure f = App.espace.getSelectedFigure();

		if(etat == 1){
			App.espace.removeFigure(f);
			App.fenetre.getFiguresOption().updateList();
			App.fenetre.repaint();
			return;
		}

		if(f instanceof PointLibre){
			PointLibre p = (PointLibre) f;
			DetailFigurePoint dp = (DetailFigurePoint) d;
			p.setX(dp.getCoordX());
			p.setY(dp.getCoordY());
			p.setNom(dp.getNom());
			App.fenetre.getFiguresOption().changeSelectedName(dp.getNom());
		}else if(f instanceof Segment){
			Segment s = (Segment) f;
			DetailFigureSegment ds = (DetailFigureSegment) d;
			s.getP1().setX(ds.getCoordX1());
			s.getP1().setY(ds.getCoordY1());
			s.getP2().setX(ds.getCoordX2());
			s.getP2().setY(ds.getCoordY2());
			s.setNom(ds.getNom());
			App.fenetre.getFiguresOption().changeSelectedName(ds.getNom());
		}else{
			f.setNom(d.getNom());
			App.fenetre.getFiguresOption().changeSelectedName(d.getNom());
		}
		//App.fenetre.getFiguresOption().changeSelected(App.espace.getSelectedFigure());
		App.fenetre.repaint();
	}

}