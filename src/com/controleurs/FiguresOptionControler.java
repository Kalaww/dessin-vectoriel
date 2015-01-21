package com.controleurs;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import com.App;
import com.modeles.Figure;
import com.vues.FiguresOption;


/**
 * Controleur de la liste des figures
 */
public class FiguresOptionControler implements ListSelectionListener{

	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e){
		JList<FiguresOption.FigureEntry> source = (JList<FiguresOption.FigureEntry>) e.getSource();
		FiguresOption.FigureEntry fe = source.getSelectedValue();
		if(fe == null) return;
		Figure f = App.espace.getFigureByHashCode(fe.hashCode());
		App.espace.setSelectedFigure(f);
		App.fenetre.repaint();
	}
	

}