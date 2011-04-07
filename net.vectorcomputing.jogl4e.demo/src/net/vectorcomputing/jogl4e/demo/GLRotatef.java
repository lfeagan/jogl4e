package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL2;

public class GLRotatef implements GLTransformation {
	private float angle;
	private float x;
	private float y;
	private float z;
	
	public GLRotatef() {
		this.x = this.y = this.z = 0.0f;
	}
	
	public GLRotatef(float angle, float x, float y, float z) {
		this.angle = angle;
		this.x = x;
		this.y = y;
		this.z = z;		
	}
	
	public void apply(GL2 gl) {
		gl.glRotatef(angle, x, y, z);
	}
	
	public float getAngle() { return this.angle; }
	public float getX() { return this.x; }
	public float getY() { return this.y; }
	public float getZ() { return this.z; }
	
	public void setAngle(float angle) { this.angle = angle; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void setZ(float z) { this.z = z; }
}
