package ru.olamedia.graphics.frame;

import javax.media.opengl.GLAutoDrawable;

import ru.olamedia.graphics.GraphicsApi;

public class SystemGLEventListener implements javax.media.opengl.GLEventListener {
	private static SystemGLEventListener instance = new SystemGLEventListener();

	public static SystemGLEventListener getInstance() {
		return instance;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GraphicsApi api = GraphicsApi.getInstance();
		api.setGlWidth(drawable.getWidth());
		api.setGlHeight(drawable.getHeight());
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GraphicsApi api = GraphicsApi.getInstance();
		api.setGlWidth(drawable.getWidth());
		api.setGlHeight(drawable.getHeight());
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		final GraphicsApi api = GraphicsApi.getInstance();
		api.setGlWidth(width);
		api.setGlHeight(height);
	}

	public void printDrawableInfo(GLAutoDrawable drawable) {
	}
}
