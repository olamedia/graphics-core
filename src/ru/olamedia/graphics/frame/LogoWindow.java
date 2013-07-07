package ru.olamedia.graphics.frame;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLContext;

import ru.olamedia.graphics.GraphicsApi;
import ru.olamedia.graphics.jogl.joglViewport;
import ru.olamedia.gui.Layer;
import ru.olamedia.gui.Viewport;

public class LogoWindow implements javax.media.opengl.GLEventListener {
	private static LogoWindow instance = new LogoWindow();
	private joglViewport viewport;
	private Viewport guiViewport = new Viewport();
	private Layer layer = new Layer();

	public static LogoWindow getInstance() {
		return instance;
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.setAutoSwapBufferMode(true);
		// viewport = new joglViewport();
		// guiViewport.getSize().setWidth(GraphicsApi.getInstance().getGlWidth());
		// guiViewport.getSize().setHeight(GraphicsApi.getInstance().getGlHeight());
		// layer.getSize().setWidth(640);
		// layer.getSize().setHeight(480);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2ES2 gl = GLContext.getCurrentGL().getGL2ES2();
		gl.glClearColor(1, 1, 1, 1f);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		//viewport.beginRendering();
		//viewport.drawText("Initializing OpenGL... OK", 10, 10);
		//viewport.endRendering();
		//layer.render(guiViewport);
		GraphicsApi.getInstance().fireGLInitEvent(); // will send single event
														// because it holds init
														// status internally
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		//guiViewport.getSize().setWidth(GraphicsApi.getInstance().getGlWidth());
		//guiViewport.getSize().setHeight(GraphicsApi.getInstance().getGlHeight());
	}

}
