package net.vectorcomputing.jogl4e.demo;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

public abstract class AbstractShape implements Shape {

	private final List<GLTransformation> transformations = new ArrayList<GLTransformation>();

	@Override
	public List<GLTransformation> getTransformations() {
		return transformations;
	}

	@Override
	public void addTransformation(GLTransformation transformation) {
		transformations.add(transformation);
	}

	@Override
	public void addTransformation(int index, GLTransformation transformation) {
		transformations.add(index, transformation);
	}
	
	protected void applyTransformations(GL2 gl) {
		for (GLTransformation transformation : transformations) {
			transformation.apply(gl);
		}
	}

}
