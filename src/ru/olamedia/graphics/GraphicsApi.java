package ru.olamedia.graphics;

import javax.media.opengl.GLEventListener;

import ru.olamedia.graphics.frame.ApplicationFrame;



public class GraphicsApi {
	private static GraphicsApi instance = new GraphicsApi();
	

	public static GraphicsApi getInstance() {
		return instance;
	}
	
	public void setGLEventListener(GLEventListener listener){
		ApplicationFrame.getInstance().setGLEventListener(listener);
	}


}
