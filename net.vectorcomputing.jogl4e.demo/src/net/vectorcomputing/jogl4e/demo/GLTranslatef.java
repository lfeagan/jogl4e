package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL2;

public class GLTranslatef implements GLTransformation {
	private float x;
	private float y;
	private float z;
	
	public GLTranslatef() {
		this.x = this.y = this.z = 0.0f;
	}
	
	public GLTranslatef(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;		
	}
	
	public void apply(GL2 gl) {
		gl.glTranslatef(x, y, z);
	}
	
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }

	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setZ(float z) { this.z = z; }
}
