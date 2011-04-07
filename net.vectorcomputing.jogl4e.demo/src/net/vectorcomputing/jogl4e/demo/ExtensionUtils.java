package net.vectorcomputing.jogl4e.demo;

import javax.media.opengl.GL2;

import com.ibm.arcs.basic.strings.StringUtils;

public class ExtensionUtils {

	public static final String[] getExtensions(GL2 gl) {
		final String glExtensionsString = gl.getContext().getGLExtensionsString();
		String[] extensions = StringUtils.splitAtBreaks(glExtensionsString);
		return extensions;
	}
}
