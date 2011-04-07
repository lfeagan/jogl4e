package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class ColoredTriangularPyramid extends AbstractShape {

	public void draw(GL2 gl) {
		gl.glLoadIdentity();
		applyTransformations(gl);
		gl.glBegin(GL.GL_TRIANGLES); // Drawing Using Triangles
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f); // Top Of Triangle (Front)
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Left Of Triangle (Front)
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(1.0f, -1.0f, 1.0f); // Right Of Triangle (Front)
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f); // Top Of Triangle (Right)
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(1.0f, -1.0f, 1.0f); // Left Of Triangle (Right)
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(1.0f, -1.0f, -1.0f); // Right Of Triangle (Right)
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f); // Top Of Triangle (Back)
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(1.0f, -1.0f, -1.0f); // Left Of Triangle (Back)
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Right Of Triangle (Back)
		gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		gl.glVertex3f(0.0f, 1.0f, 0.0f); // Top Of Triangle (Left)
		gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Left Of Triangle (Left)
		gl.glColor3f(0.0f, 1.0f, 0.0f); // Green
		gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Right Of Triangle (Left)
		gl.glEnd();
	}
}
