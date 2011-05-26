package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL2;

import net.vectorcomputing.base.string.StringFormat;

public class ExtensionUtils {

	public static final String[] getExtensions(GL2 gl) {
		final String glExtensionsString = gl.getContext().getGLExtensionsString();
		String[] extensions = StringFormat.splitAtBreaks(glExtensionsString);
		return extensions;
	}
}
