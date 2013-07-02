package ru.olamedia.graphics.frame;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLContext;

public class RedWindow implements javax.media.opengl.GLEventListener {
	private static RedWindow instance = new RedWindow();

	public static RedWindow getInstance() {
		return instance;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.setAutoSwapBufferMode(true);
		// display(drawable);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2ES2 gl = GLContext.getCurrentGL().getGL2ES2();
		gl.glClearColor(1, 0, 0, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		//gl.glFlush();
		//drawable.swapBuffers();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

	}

}
