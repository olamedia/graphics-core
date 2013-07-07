package ru.olamedia.graphics;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.newt.opengl.GLWindow;

import ru.olamedia.graphics.frame.SystemGLEventListener;

public class GraphicsApi {
	private static GraphicsApi instance = new GraphicsApi();
	private JFrame frame;
	private GLWindow glWindow;
	private int glWidth = 640;
	private int glHeight = 480;
	private ArrayList<GLInitEventListener> listeners = new ArrayList<GLInitEventListener>();
	private boolean isGLInitialized = false;

	public static GraphicsApi getInstance() {
		return instance;
	}

	public void addListener(GLInitEventListener listener) {
		listeners.add(listener);
	}

	public void fireGLInitEvent() {
		if (!isGLInitialized) {
			isGLInitialized = true;
		}
		// for (GLInitEventListener listener : listeners) {
		// listener.onGLInit();
		// }
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).onGLInit();
			listeners.remove(i);
		}
	}

	public void fireGLResizeEvent(int width, int height) {

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
