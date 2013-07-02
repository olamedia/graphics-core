package ru.olamedia.graphics;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;

import jogamp.newt.awt.NewtFactoryAWT;

import com.jogamp.newt.Display;
import com.jogamp.newt.Screen;
import com.jogamp.newt.awt.NewtCanvasAWT;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

import ru.olamedia.graphics.frame.GLEventListener;
import ru.olamedia.graphics.frame.RedWindow;
import ru.olamedia.graphics.frame.SystemGLEventListener;
import ru.olamedia.launcher.IDisposable;
import ru.olamedia.launcher.Preloader;
import ru.olamedia.modloader.ModBase;
import ru.olamedia.modloader.ModDependencyException;
import ru.olamedia.modloader.ModInfo;

public class GraphicsCoreMod extends ModBase implements IDisposable {

	public GraphicsCoreMod(ModInfo info) {
		super(info);
		info.setName("Graphics Core");
	}

	@Override
	public void register(ModInfo info) throws ModDependencyException {
		System.out.println("register gc");
		try {
			@SuppressWarnings("rawtypes")
			Class[] cArg = new Class[1];
			cArg[0] = JFrame.class;
			info.onStart(this, this.getClass().getDeclaredMethod("showFrame", cArg));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	private int screenId;
	private GLProfile glProfile;
	private GLWindow glWindow;
	private FPSAnimator animator;

	public NewtCanvasAWT createDrawable() {
		System.setProperty("jnlp.jogamp.common.utils.locks.Lock.timeout", "60000");
		glProfile = GLProfile.get(GLProfile.GL2ES2);// Default();
		// ES2
		/*
		 * This demo are based on the GL2ES2 GLProfile that allows hardware
		 * acceleration
		 * on both desktop OpenGL 2 and mobile OpenGL ES 2 devices.
		 * JogAmp JOGL will probe all the installed libGL.so, libEGL.so and
		 * libGLESv2.so librarys on
		 * the system to find which one provide hardware acceleration for your
		 * GPU device.
		 * Its common to find more than one version of these librarys installed
		 * on a system.
		 * For example on a ARM Linux system JOGL may find
		 * Hardware accelerated Nvidia tegra GPU drivers in:
		 * /usr/lib/nvidia-tegra/libEGL.so
		 * Software rendered Mesa Gallium driver in:
		 * /usr/lib/arm-linux-gnueabi/mesa-egl/libEGL.so.1
		 * Software rendered Mesa X11 in:
		 * /usr/lib/arm-linux-gnueabi/mesa/libGL.so
		 * Good news!: JOGL does all this probing for you all you have to do are
		 * to ask for
		 * the GLProfile you want to use.
		 */

		GLCapabilities caps = new GLCapabilities(glProfile);
		// We may at this point tweak the caps and request a translucent
		// drawable
		caps.setBackgroundOpaque(false);
		caps.setHardwareAccelerated(true);
		caps.setDoubleBuffered(true); // hardware swap
		// caps.setBackgroundOpaque(true);
		caps.setSampleBuffers(false);

		Display display = NewtFactoryAWT.createDisplay(null);
		Screen screen = NewtFactoryAWT.createScreen(display, screenId);
		glWindow = GLWindow.create(screen, caps);// GLWindow.create(screen,
		// glWindow.addGLEventListener(GLEventListener.getInstance());
		GraphicsApi.getInstance().setGlWindow(glWindow);
		GraphicsApi.getInstance().setGLEventListener(SystemGLEventListener.getInstance());
		GraphicsApi.getInstance().setGLEventListener(GLEventListener.getInstance());
		// caps);
		NewtCanvasAWT newtCanvasAWT = new NewtCanvasAWT(glWindow);
		newtCanvasAWT.setVisible(true);
		return newtCanvasAWT;
	}

	public void showFrame(JFrame frame) throws Exception {

		final GraphicsApi api = GraphicsApi.getInstance();
		api.setFrame(frame);
		frame.setTitle("Initializing OpenGL...");
		NewtCanvasAWT canvas = createDrawable();
		Preloader.addDisposable(this);// stops animator
		canvas.setVisible(true);
		frame.getContentPane().removeAll();
		frame.add(canvas);
		frame.pack();
		// Avoid flickering by forcing resize event:
		// frame.setSize(frame.getWidth(), frame.getHeight() - 1);
		// frame.setSize(frame.getWidth(), frame.getHeight() + 1);
		// canvas.setVisible(true);
		// frame.getContentPane().setSize(frame.getContentPane().getWidth(),
		// frame.getContentPane().getHeight() - 1);
		// frame.getContentPane().doLayout();
		// frame.validate();
		// frame.getContentPane().setSize(frame.getContentPane().getWidth(),
		// frame.getContentPane().getHeight() + 1);
		// frame.getContentPane().doLayout();
		api.setGLEventListener(new RedWindow());
		animator = new FPSAnimator(60);
		animator.add(api.getGlWindow());
		animator.start();
		/*
		 * if (null != frame) {
		 * frame.setTitle("Graphics Core");
		 * } else {
		 * final ApplicationFrame f = ApplicationFrame.getInstance();
		 * f.setTitle("Graphics Core");
		 * f.initGL();
		 * f.setVisible(true);
		 * GraphicsApi.getInstance().setGLEventListener(new RedWindow());
		 * }
		 */
	}

	@Override
	public void dispose() {
		if (null != animator) {
			if (animator.isStarted()) {
				animator.stop();
			}
		}

	}

}
