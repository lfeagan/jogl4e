package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL2;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class GLResizeListener implements Listener {

	private final GLCanvas glCanvas;
	private final GLContext glContext;
	
	private float yFov;
	private float zNear;
	private float zFar;

	public GLResizeListener(GLCanvas glCanvas, GLContext glContext, float yFov, float zNear, float zFar) {
		this.glCanvas = glCanvas;
		this.glContext = glContext;
		this.yFov = yFov;
		this.zNear = zNear;
		this.zFar = zFar;
	}

	public synchronized void handleEvent(Event event) {
		Rectangle bounds = glCanvas.getBounds();
		float aspectRatio = (float) bounds.width / (float) bounds.height;
		glCanvas.setCurrent();
		glContext.makeCurrent();
		GL2 gl = glContext.getGL().getGL2();
		gl.glViewport(0, 0, bounds.width, bounds.height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU glu = new GLU();
		glu.gluPerspective(yFov, aspectRatio, zNear, zFar);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		glContext.release();
	}
	
	public synchronized float getYFov() {
		return yFov;
	}
	
	public synchronized float getZNear() {
		return zNear;
	}
	
	public synchronized float getZFar() {
		return zFar;
	}
	
	public synchronized void setYFov(float yFov) {
		this.yFov = yFov;
	}
	
	public synchronized void setZNear(float zNear) {
		this.zNear = zNear;
	}
	
	public synchronized void setZFar(float zFar) {
		this.zFar = zFar;
	}
}
