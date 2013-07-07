package ru.olamedia.graphics.jogl;

import java.awt.Font;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.GLU;

import ru.olamedia.graphics.GraphicsApi;

import com.jogamp.opengl.util.awt.TextRenderer;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.nulldevice.NullSoundDevice;
import de.lessvoid.nifty.renderer.jogl.input.JoglInputSystem;
import de.lessvoid.nifty.renderer.jogl.render.JoglRenderDevice;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.spi.time.impl.AccurateTimeProvider;
import de.lessvoid.nifty.tools.Color;
import de.lessvoid.nifty.tools.resourceloader.FileSystemLocation;

public class joglViewport {
	// private GLAutoDrawable drawable;
	// private TextRenderer sans11Black;
	private TextRenderer sans11White;
	private JoglRenderDevice niftyRenderDevice;
	private JoglInputSystem niftyInputSystem;
	private AccurateTimeProvider niftyTimeProvider;
	private Nifty nifty;
	private Screen niftyScreen;

	// private TextRenderer sans12;

	public joglViewport() {
		niftyRenderDevice = new JoglRenderDevice();
		niftyInputSystem = new JoglInputSystem();
		niftyTimeProvider = new AccurateTimeProvider();
		nifty = new Nifty(niftyRenderDevice, new NullSoundDevice(), niftyInputSystem, niftyTimeProvider);
		nifty.getResourceLoader().addResourceLocation(new FileSystemLocation(new java.io.File("res")));
		niftyScreen = new ScreenBuilder("start") {
			{
				layer(new LayerBuilder("background") {
					{
						backgroundColor(new Color(255f / 255f, 0f / 255f, 0f / 255f, 0.5f));
						alignCenter();
						childLayoutCenter();
					}
				});
			}
		}.build(nifty);
		nifty.gotoScreen("start");
		// sans11Black = new TextRenderer(new Font("SansSerif", Font.PLAIN,
		// 11));
		// sans11Black.setColor(0, 0, 0, 1f);
		sans11White = new TextRenderer(new Font("SansSerif", Font.PLAIN, 11));
		sans11White.setColor(0, 0, 0, 0.9f);
		// sans12 = new TextRenderer(new Font("SansSerif", Font.PLAIN, 11));
	}

	public void beginRendering() {
		final int width = 640;
		final int height = 480;
		GL gl = GLContext.getCurrentGL();
		gl.glViewport(0, 0, width, height);
		gl.glEnable(GL.GL_BLEND);

		if (gl.isGL2()) {
			GL2 gl2 = gl.getGL2();
			gl2.glMatrixMode(GL2.GL_PROJECTION);
			gl2.glLoadIdentity();
			gl2.glOrtho(0, width, height, 0, -9999, 9999);

			gl2.glMatrixMode(GL2.GL_MODELVIEW);
			gl2.glLoadIdentity();
		}
		// sans11White.beginRendering(GraphicsApi.getInstance().getGlWidth(),
		// GraphicsApi.getInstance().getGlHeight());
		nifty.update();
		nifty.render(false);
		// nifty.render(true);
		// sans11Black.beginRendering(drawable.getWidth(),
		// drawable.getHeight());
	}

	public void endRendering() {
		// sans11White.flush();
		// nifty.update();

		
		// niftyScreen.renderLayers(nifty.getRenderEngine());
		// sans11White.endRendering();
		// sans11Black.endRendering();
	}

	public void drawText(String text, int x, int y) {
		// sans12.beginRendering(drawable.getWidth(), drawable.getHeight());
		// sans12.setColor(1, 1, 1, 0.9f);
		// sans12.draw(text, x, y);
		// sans12.endRendering();
		// sans11White.draw(text, x - 1, y);
		// sans11White.draw(text, x + 1, y);
		// sans11White.draw(text, x, y - 1);
		// sans11White.draw(text, x, y + 1);
		// sans11White.draw(text, x, y);

	}
}
