package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL;

public class VBOUtils {

	public static boolean isVBOSupported(GL gl) {
		final boolean VBOsupported = gl.isFunctionAvailable("glGenBuffersARB")
        	&& gl.isFunctionAvailable("glBindBufferARB")
        	&& gl.isFunctionAvailable("glBufferDataARB")
        	&& gl.isFunctionAvailable("glDeleteBuffersARB");
		return VBOsupported;
	}
}
