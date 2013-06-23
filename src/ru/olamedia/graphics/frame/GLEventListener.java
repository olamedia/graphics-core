package ru.olamedia.graphics.frame;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLContext;

public class GLEventListener implements javax.media.opengl.GLEventListener {
	private static GLEventListener instance = new GLEventListener();

	public static GLEventListener getInstance() {
		return instance;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		printDrawableInfo(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = GLContext.getCurrentGL().getGL2();
		gl.glClearColor(0, 0, 0, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}

	public void printDrawableInfo(GLAutoDrawable drawable) {
		// GLAutoDrawable drawable
		GL2ES2 gl = drawable.getGL().getGL2ES2();
		System.err.println(Thread.currentThread() + " Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
		System.err.println(Thread.currentThread() + " INIT GL IS: " + gl.getClass().getName());
		System.err.println(Thread.currentThread() + " GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
		System.err.println(Thread.currentThread() + " GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
		System.err.println(Thread.currentThread() + " GL_VERSION: " + gl.glGetString(GL.GL_VERSION));
		System.err.println(Thread.currentThread() + " GL Profile: " + gl.getGLProfile());
		System.err.println(Thread.currentThread() + " GL:" + gl);
		System.err.println(Thread.currentThread() + " GL_VERSION=" + gl.glGetString(GL.GL_VERSION));
	}
}
