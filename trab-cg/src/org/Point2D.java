package org;

public class Point2D {
	
	public float x;
	public float y;
	public float w;
	
	public Point2D(){
		this.x = 0;
		this.y = 0;
		this.w = 1;
	}
	
	public Point2D(float[][] mat){
		this.x = mat[0][0];
		this.y = mat[0][1];
		this.w = mat[0][2];
	}
	
	public Point2D(float x, float y){
		this.x = x;
		this.y = y;
		this.w = 1;
	}
	
	public Point2D(float x, float y, float w){
		this.x = x;
		this.y = y;
		this.w = w;
	}
	
	public float[][] getMat() {
		float[][] out = new float[1][3];
		out[0][0] = x;
		out[0][1] = y;
		out[0][2] = w;
		return out;
	}
}
