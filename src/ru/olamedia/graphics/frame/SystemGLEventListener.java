package ru.olamedia.graphics.frame;

import javax.media.opengl.GLAutoDrawable;

public class SystemGLEventListener implements javax.media.opengl.GLEventListener {
	private static SystemGLEventListener instance = new SystemGLEventListener();

	public static SystemGLEventListener getInstance() {
		return instance;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final ApplicationFrame f = ApplicationFrame.getInstance();
		f.setGlWidth(drawable.getWidth());
		f.setGlHeight(drawable.getHeight());
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void display(GLAutoDrawable drawable) {
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final ApplicationFrame f = ApplicationFrame.getInstance();
		f.setGlWidth(width);
		f.setGlHeight(height);
	}

	public void printDrawableInfo(GLAutoDrawable drawable) {
	}
}
