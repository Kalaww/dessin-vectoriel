package com.modeles;

import java.lang.Math;

public class Triangle extends Figure{
	
	protected Segment s1;

	protected Segment s2;

	protected Segment s3;

	public Triangle(Segment a, Segment b, Segment c){
		s1 = a;
		s2 = b;
		s3 = c;
		nom = "Triangle";
	}

	public Triangle(double ax, double ay, double bx, double by, double cx, double cy){
		this(new Segment(ax, ay, bx, by), 
			new Segment(bx, by, cx, cy), 
			new Segment(cx, cy, ax, ay));
	}

	public double distanceTo(double a, double b){
		double ds1 = s1.distanceTo(a,b);
		double ds2 = s2.distanceTo(a,b);
		double ds3 = s3.distanceTo(a,b);
		
		return Math.min(Math.min(ds1, ds2), ds3);
	}

	public void move(double deltaX, double deltaY){
		s1.move(deltaX, deltaY);
		s2.move(deltaX, deltaY);
		s3.move(deltaX, deltaY);
	}

	public String toString(){
		return "Triangle";
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


	public void setS1(Segment s){
		s1 = s;
	}

	public void setS2(Segment s){
		s2 = s;
	}

	public void setS3(Segment s){
		s3 = s;
	}
}