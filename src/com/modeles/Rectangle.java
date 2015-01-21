package com.modeles;

import java.lang.Math;

import com.App;

/**
 * Implementation d'un rectangle
 */
public class Rectangle extends Figure implements Deplacable{
	
	/**
	 * Segment 1
	 */
	protected Segment s1;

	/**
	 * Segment 2
	 */
	protected Segment s2;

	/**
	 * Segment 3
	 */
	protected Segment s3;

	/**
	 * Segment 4
	 */
	protected Segment s4;

	/**
	 * Si le rectangle peut se déplacer
	 */
	private boolean canMoveVar;

	/**
	 * Constructeur
	 * @param  a Segment 1
	 * @param  b Segment 2
	 * @param  c Segment 3
	 * @param  d Segment 4
	 */
	public Rectangle(Segment a, Segment b, Segment c, Segment d){
		s1 = a;
		s1.setParent(this);
		s2 = b;
		s2.setParent(this);
		s3 = c;
		s3.setParent(this);
		s4 = d;
		s4.setParent(this);
		nom = "Rectangle";
		canMoveVar = true;
	}

	/**
	 * Constructeur
	 * @param  ax segment 1 abscisse
	 * @param  ay segment 1 ordonnée
	 * @param  bx segment 2 abscisse
	 * @param  by segment 2 ordonnée
	 * @param  cx segment 3 abscisse
	 * @param  cy segment 3 ordonnée
	 * @param  dx segment 4 abscisse
	 * @param  dy segment 4 ordonnée
	 */
	public Rectangle(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy){
		this(new Segment(ax, ay, bx, by),
			new Segment(bx, by, cx, cy),
			new Segment(cx, cy, dx, dy),
			new Segment(dx, dy, ax, ay));
	}

	/**
	 * Constructeur
	 * @param  ax segment 1 abscisse
	 * @param  ay segment 1 ordonnée
	 * @param  bx segment 2 abscisse
	 * @param  by segment 2 ordonnée
	 * @param  cx segment 3 abscisse
	 * @param  cy segment 3 ordonnée
	 */
	public Rectangle(double ax, double ay, double bx, double by, double cx, double cy){
		Droite d1 = new Segment(ax, ay, bx, by).perpendiculaire(new PointLibre(ax, ay));
		Droite d2 = new Segment(bx, by, cx, cy).perpendiculaire(new PointLibre(cx, cy));
		Point p = d1.intersection(d2);
		s1 = new Segment(ax, ay, bx, by);
		s1.setParent(this);
		s2 = new Segment(bx, by, cx, cy);
		s2.setParent(this);
		s3 = new Segment(cx, cy, p.getX(), p.getY());
		s3.setParent(this);
		s4 = new Segment(p.getX(), p.getY(), ax, ay);
		s4.setParent(this);
	}

	public double distanceTo(double a, double b){
		double ds1 = s1.distanceTo(a,b);
		double ds2 = s2.distanceTo(a,b);
		double ds3 = s3.distanceTo(a,b);
		double ds4 = s4.distanceTo(a,b);
		
		return Math.min(Math.min(Math.min(ds1, ds2), ds3), ds4);
	}

	/**
	 * Donne le droit au rectangle de se déplacer
	 */
	public void resetCanMove(){
		super.resetCanMove();
		s1.resetCanMove();
		s2.resetCanMove();
		s3.resetCanMove();
		s4.resetCanMove();
	}

	public void move(double deltaX, double deltaY){
		if(canMove){
			canMove = false;
			s1.move(deltaX, deltaY);
			s2.move(deltaX, deltaY);
			s3.move(deltaX, deltaY);
			s4.move(deltaX, deltaY);
		}
	}

	public void suppression(){
		s1.suppression();
		s2.suppression();
		s3.suppression();
		s4.suppression();
	}

	/**
	 * Segment du rectangle le plus proche de (a,b)
	 * @param  a   abscisse
	 * @param  b   ordonnée
	 * @param  pas pas
	 * @return     le segment le plus proche
	 */
	public Segment getCloserSegment(double a, double b, double pas){
		Segment closer = null;
		double distMin = -1.0;
		double tmp;

		Segment[] tab = {s1,s2,s3,s4};
		for(Segment f : tab){
			tmp = f.distanceTo(a,b);
			if(tmp <= pas && (distMin < 0 || tmp < distMin)){
				distMin = tmp;
				closer = f;
			}
		}
		return closer;
	}

	public void setSelected(boolean b){
		super.setSelected(b);
		s1.setSelected(b);
		s2.setSelected(b);
		s3.setSelected(b);
		s4.setSelected(b);
	}


	public Rectangle clone(){
		Rectangle r = null;
		r.s1 = (Segment)s1.clone();
		r.s2 = (Segment)s2.clone();
		r.s3 = (Segment)s3.clone();
		r.s4 = (Segment)s4.clone();
		return r;
	}


	public Segment getS1(){
		return s1;
	}

	public Segment getS2(){
		return s2;
	}

	public Segment getS3(){
		return s3;
	}

	public Segment getS4(){
		return s4;
	}


	public void setS1(Segment s){
		s1 = s;
		s2.setP1(s1.getP2());
		s4.setP2(s1.getP1());
	}

	public void setS2(Segment s){
		s2 = s;
		s3.setP1(s2.getP2());
		s1.setP2(s2.getP1());
	}

	public void setS3(Segment s){
		s3 = s;
		s4.setP1(s3.getP2());
		s2.setP2(s3.getP1());
	}

	public void setS4(Segment s){
		s4 = s;
		s1.setP1(s4.getP2());
		s3.setP2(s4.getP1());
	}

	public Point getP1(){
		return s1.getP1();
	}

	public Point getP2(){
		return s1.getP2();
	}

	public Point getP3(){
		return s2.getP2();
	}

	public Point getP4(){
		return s3.getP2();
	}

	public void setP1(Point p){
		s1.setP1(p);
		s4.setP2(p);
	}

	public void setP2(Point p){
		s1.setP2(p);
		s2.setP1(p);
	}

	public void setP3(Point p){
		s2.setP2(p);
		s3.setP1(p);
	}

	public void setP4(Point p){
		s3.setP2(p);
		s4.setP1(p);
	}

	public String toString(){
		return super.toString()+": "+getP1()+"  "+getP2()+"  "+getP3()+"  "+getP4();
	}

}