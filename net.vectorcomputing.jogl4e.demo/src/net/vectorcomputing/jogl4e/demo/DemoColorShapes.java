package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLProfile;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DemoColorShapes {

	private GLResizeListener resizeListener;
	
	private Display display;
	private Shell shell;
	private GLCanvas glCanvas;
	private GLContext glContext;
	
	DemoColorShapesScene scene;
	
	public DemoColorShapes() {
		init();
		scene = new DemoColorShapesScene(display, glCanvas, glContext);
	}
	
	private final void init() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("SWT/JOGL Rotating Colored Shapes Example");
		shell.setLayout(new FillLayout());
		shell.setSize(800, 600);
		
		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		GLData gldata = new GLData();
		gldata.doubleBuffer = true;
		
		glCanvas = new GLCanvas(composite, SWT.NO_BACKGROUND, gldata);
		glCanvas.setCurrent();

		GLProfile glprofile = GLProfile.getDefault();
		
		glContext = GLDrawableFactory.getFactory(glprofile).createExternalGLContext();
		
		resizeListener = new GLResizeListener(glCanvas, glContext, 45.0f, 0.1f, 100.0f);
		glCanvas.addListener(SWT.Resize, resizeListener);

		glContext.makeCurrent();
		GL2 gl = glContext.getGL().getGL2();
		gl.glShadeModel(GL2.GL_SMOOTH); 
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glClearDepth(1.0f);
		gl.glLineWidth(2);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LESS);
		glContext.release();
		
		shell.open();
		
		glCanvas.addKeyListener(new KPListener());
	}
	
	private final void dispose() {
		scene.dispose();
		shell.dispose();
		display.dispose();
	}
	
	class KPListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.character == 'q' || e.character == 'Q') {
				dispose();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	public void draw() {
		display.asyncExec(scene);
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
		
	public static void main(String[] args) {
		DemoColorShapes demo = new DemoColorShapes();
		demo.draw();
	}
}
