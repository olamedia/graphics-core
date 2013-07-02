package ru.olamedia.graphics;

import javax.swing.JFrame;

import com.jogamp.newt.opengl.GLWindow;

import ru.olamedia.graphics.frame.SystemGLEventListener;

public class GraphicsApi {
	private static GraphicsApi instance = new GraphicsApi();
	private JFrame frame;
	private GLWindow glWindow;
	private int glWidth = 640;
	private int glHeight = 480;

	public static GraphicsApi getInstance() {
		return instance;
	}

	/**
	 * Switch to another GLEventListener
	 * 
	 * @param listener
	 */
	public void setGLEventListener(javax.media.opengl.GLEventListener listener) {
		for (int i = 0; i < glWindow.getGLEventListenerCount(); i++) {
			if (!(glWindow.getGLEventListener(i) instanceof SystemGLEventListener)) {
				glWindow.removeGLEventListener(glWindow.getGLEventListener(i));
			}
		}
		glWindow.addGLEventListener(listener);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public GLWindow getGlWindow() {
		return glWindow;
	}

	public void setGlWindow(GLWindow glWindow) {
		this.glWindow = glWindow;
	}

	public int getGlWidth() {
		return glWidth;
	}

	public void setGlWidth(int glWidth) {
		this.glWidth = glWidth;
	}

	public int getGlHeight() {
		return glHeight;
	}

	public void setGlHeight(int glHeight) {
		this.glHeight = glHeight;
	}

	public float getGlAspect() {
		return ((float) getGlWidth()) / ((float) getGlHeight());
	}
}
