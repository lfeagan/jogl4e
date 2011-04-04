/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.vectorcomputing.jogl4e.demo;

/*
 * SWT OpenGL snippet: use JOGL to draw to an SWT GLCanvas
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 *
 * @since 3.2
 */
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLProfile;
import javax.media.opengl.glu.GLU;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class RotatingTorus {
	static void drawTorus(GL2 gl, float r, float R, int nsides, int rings) {
		float ringDelta = 2.0f * (float) Math.PI / rings;
		float sideDelta = 2.0f * (float) Math.PI / nsides;
		float theta = 0.0f, cosTheta = 1.0f, sinTheta = 0.0f;
		for (int i = rings - 1; i >= 0; i--) {
			float theta1 = theta + ringDelta;
			float cosTheta1 = (float) Math.cos(theta1);
			float sinTheta1 = (float) Math.sin(theta1);
			gl.glBegin(GL2.GL_QUAD_STRIP);
			float phi = 0.0f;
			for (int j = nsides; j >= 0; j--) {
				phi += sideDelta;
				float cosPhi = (float) Math.cos(phi);
				float sinPhi = (float) Math.sin(phi);
				float dist = R + r * cosPhi;
				gl.glNormal3f(cosTheta1 * cosPhi, -sinTheta1 * cosPhi, sinPhi);
				gl.glVertex3f(cosTheta1 * dist, -sinTheta1 * dist, r * sinPhi);
				gl.glNormal3f(cosTheta * cosPhi, -sinTheta * cosPhi, sinPhi);
				gl.glVertex3f(cosTheta * dist, -sinTheta * dist, r * sinPhi);
			}
			gl.glEnd();
			theta = theta1;
			cosTheta = cosTheta1;
			sinTheta = sinTheta1;
		}
	}

	public static void main(String[] args) {
		System.out.println("running");
		System.out.flush();
		
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("SWT/JOGL Rotating Torus Example");
		shell.setLayout(new FillLayout());
		shell.setSize(1920, 1200);
		
		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		GLData gldata = new GLData();
		gldata.doubleBuffer = true;
		
		final GLCanvas glcanvas = new GLCanvas(composite, SWT.NO_BACKGROUND, gldata);
		glcanvas.setCurrent();

		GLProfile glprofile = GLProfile.getDefault();
		
		final GLContext context = GLDrawableFactory.getFactory(glprofile).createExternalGLContext();

		glcanvas.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				Rectangle bounds = glcanvas.getBounds();
				float fAspect = (float) bounds.width / (float) bounds.height;
				glcanvas.setCurrent();
				context.makeCurrent();
				GL2 gl = context.getGL().getGL2();
				gl.glViewport(0, 0, bounds.width, bounds.height);
				gl.glMatrixMode(GL2.GL_PROJECTION);
				gl.glLoadIdentity();
				GLU glu = new GLU();
				glu.gluPerspective(45.0f, fAspect, 0.5f, 400.0f);
				gl.glMatrixMode(GL2.GL_MODELVIEW);
				gl.glLoadIdentity();
				context.release();
			}
		});

		context.makeCurrent();
		GL2 gl = context.getGL().getGL2();
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glClearDepth(1.0);
		gl.glLineWidth(2);
		gl.glEnable(GL.GL_DEPTH_TEST);
		context.release();
		
		shell.open();

		display.asyncExec(new Runnable() {
			int rot = 0;

			public void run() {
				if (!glcanvas.isDisposed()) {
					glcanvas.setCurrent();
					context.makeCurrent();
					GL2 gl = context.getGL().getGL2();
					gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
					gl.glClearColor(.3f, .5f, .8f, 1.0f);
					gl.glLoadIdentity();
					gl.glTranslatef(0.0f, 0.0f, -10.0f);
					float frot = rot;
					gl.glRotatef(0.15f * rot, 2.0f * frot, 10.0f * frot, 1.0f);
					gl.glRotatef(0.3f * rot, 3.0f * frot, 1.0f * frot, 1.0f);
					rot++;
					gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
					gl.glColor3f(0.9f, 0.9f, 0.9f);
					drawTorus(gl, 1, 1.9f + ((float) Math.sin((0.004f * frot))), 15, 15);
					glcanvas.swapBuffers();
					context.release();
					display.asyncExec(this);
				}
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}