package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLContext;

import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.widgets.Display;

public class DemoColorShapesScene implements Runnable {
	
	private Display display;
	private GLCanvas glCanvas;
	private GLContext glContext;
	
	private volatile boolean dispose = false;
	
	private boolean highlightVertices = true;
	private boolean fillPolygons = true;
	
	private Shape pyramid;
	private final GLRotatef pyramidRotation = new GLRotatef(0.0f, 0.0f, 1.0f, 0.0f);
	private final GLTranslatef pyramidTranslation = new GLTranslatef(-1.5f, 0.0f, -6.0f);
	
	private Shape cube;
	private final GLRotatef cubeRotation = new GLRotatef(0.0f, 1.0f, 1.0f, 1.0f);
	private final GLTranslatef cubeTranslation = new GLTranslatef(1.5f, 0.0f, -7.0f);
	
	private long drawStartTime;
	private long drawTime;
	
	private short frameCounter = 0;
	private short frameCounterInterval = 60;
	private long frameCounterStartTime;
	private long frameCounterIntervalTime;
	
	public DemoColorShapesScene(Display display, GLCanvas glCanvas, GLContext glContext) {
		this.display = display;
		this.glCanvas = glCanvas;
		this.glContext = glContext;
		createPyramid();
		createCube();
	}
	
	private final void createPyramid() {
		pyramid = new ColoredTriangularPyramid();
		pyramid.addTransformation(pyramidTranslation);
		pyramid.addTransformation(pyramidRotation);
	}
	
	private final void createCube() {
		cube = new ColoredCube();
		cube.addTransformation(cubeTranslation);
		cube.addTransformation(cubeRotation);
	}
	
	public final void dispose() {
		System.out.println("dispose requested");
		System.out.flush();
		dispose = true;
	}
	
	public final void run() {
		if (frameCounter == 0) {
			frameCounterStartTime = System.currentTimeMillis();
			System.out.println("startTime=" + frameCounterStartTime);
		}

		//drawStartTime = System.currentTimeMillis();
		if (!glCanvas.isDisposed()) {
			glCanvas.setCurrent();
			glContext.makeCurrent();
			GL2 gl = glContext.getGL().getGL2();
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
			// gl.glClearColor(.3f, .5f, .8f, 1.0f);
			// gl.glLoadIdentity();
			// gl.glTranslatef(0.0f, 0.0f, -10.0f);

			// gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			if (highlightVertices) {
				gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_POINT);
				gl.glPointSize(10.0f);

				pyramid.draw(gl);
				cube.draw(gl);
				gl.glPointSize(1.0f);
			}

			if (fillPolygons) {
				gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
			} else {
				gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			}

			pyramid.draw(gl);
			cube.draw(gl);

			gl.glFlush();

			// Update rotation angles
			pyramidRotation.setAngle(pyramidRotation.getAngle() + 0.2f);
			cubeRotation.setAngle(cubeRotation.getAngle() + 0.15f);

			glCanvas.swapBuffers();
			glContext.release();
			if (!dispose) {
				display.asyncExec(this);
			}
		}
		
		++frameCounter;
		
		if (frameCounter % frameCounterInterval == 0) {
			frameCounter = 0;
			final long currentTime = System.currentTimeMillis();
			frameCounterIntervalTime = currentTime - frameCounterStartTime;
			float fps = (float) frameCounterInterval / (frameCounterIntervalTime / 1000.0f);
			System.out.println("fps=" + fps);
			System.out.println(frameCounterInterval + " frames in " + frameCounterIntervalTime + " ms");
		}

		//drawTime = System.currentTimeMillis() - drawStartTime;
		//System.out.println("drawTime=" + drawTime + " ms");
	}
}
