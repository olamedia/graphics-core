package ru.olamedia.graphics.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;

import jogamp.newt.awt.NewtFactoryAWT;

import com.jogamp.newt.Display;
import com.jogamp.newt.Screen;
import com.jogamp.newt.awt.NewtCanvasAWT;
import com.jogamp.newt.opengl.GLWindow;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = -5854487193149974655L;
	private static ApplicationFrame instance = new ApplicationFrame();
	private int width = 640;
	private int height = 480;
	

	public ApplicationFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		setLayout(new BorderLayout());
		getContentPane().setSize(new Dimension(width, height));
		setMinimumSize(new Dimension(320, 240));
		setPreferredSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		// setVisible(true);
		// pack();
	}

	public void initGL() {
		NewtCanvasAWT canvas = createDrawable();
		add(canvas);
	}

	private int screenId;
	private GLWindow glWindow;

	public NewtCanvasAWT createDrawable() {
		System.setProperty("jnlp.jogamp.common.utils.locks.Lock.timeout", "60000");
		GLProfile glProfile = GLProfile.get(GLProfile.GL2);// Default();
		// ES2
		GLCapabilities caps = new GLCapabilities(glProfile);
		caps.setHardwareAccelerated(true);
		caps.setDoubleBuffered(true); // hardware swap
		caps.setBackgroundOpaque(true);
		caps.setSampleBuffers(false);

		Display display = NewtFactoryAWT.createDisplay(null);
		Screen screen = NewtFactoryAWT.createScreen(display, screenId);
		glWindow = GLWindow.create(screen, caps);// GLWindow.create(screen,
		// glWindow.addGLEventListener(GLEventListener.getInstance());
		setGLEventListener(SystemGLEventListener.getInstance());
		setGLEventListener(GLEventListener.getInstance());
		// caps);
		NewtCanvasAWT newtCanvasAWT = new NewtCanvasAWT(glWindow);
		newtCanvasAWT.setVisible(true);
		return newtCanvasAWT;
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

	public static ApplicationFrame getInstance() {
		return instance;
	}

	

	// public void show() {

	// }

}
