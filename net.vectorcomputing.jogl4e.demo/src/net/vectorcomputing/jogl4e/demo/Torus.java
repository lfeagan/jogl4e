package net.vectorcomputing.jogl4e.demo;

import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import com.jogamp.opengl.util.GLBuffers;

public class Torus {

	private final float innerRadius;
	private final float outerRadius;
	private final int numberSides;
	private final int numberRings;
	private final boolean useVBO;
	
	public Torus(GL gl, float innerRadius, float outerRadius, int numberSides, int numberRings) {
		this.innerRadius = innerRadius;
		this.outerRadius = outerRadius;
		this.numberSides = numberSides;
		this.numberRings = numberRings;
		//this.useVBO = VBOUtils.isVBOSupported(gl);
		this.useVBO = false;
	}
	
	public void render(GL2 gl) {
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY); // Enable pointers
		
		if (useVBO) {
			
		} else {
			drawTorus(gl, innerRadius, outerRadius, numberSides, numberRings);
		}
	}
	
	private static void buildVBO(GL2 gl) {
		
		final int vertexCount;
		final FloatBuffer vertices; 
		
		final int[] VBOVertices = new int[1];
		
//		gl.glGenBuffers(1, VBOVertices, 0)
//		gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertexCount * 3 * GLBuffers.SIZEOF_FLOAT, vertices, GL2.GL_STATIC_DRAW);
//		GLBuffers.SIZEOF_FLOAT;
		
	}
	//drawTorus(gl, 1, 1.9f + ((float) Math.sin((0.004f * frot))), 15, 15);
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
}
