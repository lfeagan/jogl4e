package net.vectorcomputing.jogl4e.demo;

import java.util.List;

import javax.media.opengl.GL2;

public interface Shape {
	
	public void draw(GL2 gl);
	
	public List<GLTransformation> getTransformations();
	
	public void addTransformation(GLTransformation transformation);
	
	public void addTransformation(int index, GLTransformation transformation);
}
