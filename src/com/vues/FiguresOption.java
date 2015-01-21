package com.vues;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.BorderFactory;

import com.controleurs.FiguresOptionControler;
import com.App;
import com.modeles.*;

/**
 * Contient une liste des figures tracées et les détails de la figure sélectionnée
 */
@SuppressWarnings("serial")
public class FiguresOption extends JPanel{

	/**
	 * Liste des figures
	 */
	private JList<FigureEntry> listFigures;

	/**
	 * Model de la liste des figures
	 */
	private DefaultListModel<FigureEntry> figures;

	/**
	 * Conteneur pour les détails
	 */
	private JPanel conteneurDetail;

	/**
	 * Détails de la figure sélectionnée	
	 */
	private DetailFigure detailFigure;

	/**
	 * Figure sélectionnée précédente
	 */
	private Figure oldSelectedFigure;

	/**
	 * Entrée de la liste des figures.
	 * Permet de retenir que le hashcode et nom de chaque figure.
	 */
	public class FigureEntry{

		/**
		 * Nom de la figure
		 */
		private String nom;

		/**
		 * Hashcode de la figure
		 */
		private int hashcode;

		/**
		 * Constructeur
		 * @param  nom      nom de la figure
		 * @param  hashcode hashcode de la figure
		 */
		public FigureEntry(String nom, int hashcode){
			this.nom = nom;
			this.hashcode = hashcode;
		}

		public String toString(){
			return nom;
		}

		public void setNom(String n){
			nom = n;
		}

		public int hashCode(){
			return hashcode;
		}
	}
	
	/**
	 * Constructeur
	 */
	public FiguresOption(){
		super();

		oldSelectedFigure = null;

		setPreferredSize(new Dimension(200, 750));
		setMinimumSize(new Dimension(200, 750));

		figures = new DefaultListModel<FigureEntry>();
		listFigures = new JList<FigureEntry>(figures);
		listFigures.addListSelectionListener(new FiguresOptionControler());
		JScrollPane scrollFigures = new JScrollPane(listFigures);
		scrollFigures.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollFigures.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollFigures.setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), (int) (getPreferredSize().getHeight()*0.6)));
		scrollFigures.setBorder(BorderFactory.createTitledBorder("Figures"));

		detailFigure = new DetailFigure();
		detailFigure.init();

		conteneurDetail = new JPanel();
		conteneurDetail.setLayout(new BorderLayout());
		conteneurDetail.add(detailFigure, BorderLayout.CENTER);
		JScrollPane scrollDetail = new JScrollPane(conteneurDetail);
		scrollDetail.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollDetail.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDetail.setPreferredSize(new Dimension(200, 400));
		scrollDetail.setMinimumSize(new Dimension(200, 400));
		scrollDetail.setBorder(BorderFactory.createTitledBorder("Détail"));

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		add(scrollFigures, gbc);

		gbc.gridy = 1;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(10,0,0,0);
		add(scrollDetail, gbc);
	}

	/**
	 * Mise a jour de la zone de détail en fonction du type de la figure sélectionnée
	 */
	public void updateDetail(){
		Figure f = App.espace.getSelectedFigure();
		if(f == null){
			conteneurDetail.removeAll();
			conteneurDetail.add(new JPanel());
			App.fenetre.revalidate();
			return;
		}
		if(oldSelectedFigure == f) return;
		oldSelectedFigure = f;
		conteneurDetail.removeAll();
		if(f instanceof PointLibre){
			detailFigure = new DetailFigurePoint();
		}else if(f instanceof Segment){
			detailFigure = new DetailFigureSegment();
		}else if(f instanceof Rectangle){
			detailFigure = new DetailFigureRectangle();
		}else{
			detailFigure = new DetailFigure();
		}
		conteneurDetail.add(detailFigure);
		detailFigure.init();
		App.fenetre.revalidate();
	}

	public void paintComponent(Graphics g){
		updateDetail();
		super.paintComponent(g);
	}

	/**
	 * Mise a jout de la liste
	 */
	public void updateList(){
		figures.removeAllElements();
		for(Figure f : App.espace.getFigures()){
			figures.addElement(new FigureEntry(f.getNom(), f.hashCode()));
		}
	}

	/**
	 * Ajoute un élément à la liste
	 * @param f la figure à ajouter
	 */
	public void addList(Figure f){
		figures.addElement(new FigureEntry(f.getNom(), f.hashCode()));
	}

	/**
	 * Change l'élément sélectionné de la liste
	 * @param f [description]
	 */
	public void changeSelected(Figure f){
		for(Object o : figures.toArray()){
			FigureEntry fe = (FigureEntry) o;
			if(f!= null && fe.hashCode() == f.hashCode()){
				listFigures.setSelectedValue(fe, true);
				break;
			}
		}
	}

	/**
	 * Change le nom de l'élément sélectionné
	 * @param nom [description]
	 */
	public void changeSelectedName(String nom){
		FigureEntry fe = listFigures.getSelectedValue();
		fe.setNom(nom);
	}

	/**
	 * Getter détail
	 * @return détail
	 */
	public DetailFigure getDetailFigure(){
		return detailFigure;
	}

	/**
	 * Getter liste
	 * @return liste
	 */
	public JList<FigureEntry> getListFigures(){
		return listFigures;
	}
}