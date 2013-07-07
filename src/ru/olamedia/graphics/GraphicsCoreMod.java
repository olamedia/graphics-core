package ru.olamedia.graphics;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JFrame;

import jogamp.newt.awt.NewtFactoryAWT;

import com.jogamp.newt.Display;
import com.jogamp.newt.Screen;
import com.jogamp.newt.awt.NewtCanvasAWT;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;

import ru.olamedia.graphics.frame.GLEventListener;
import ru.olamedia.graphics.frame.LogoWindow;
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
	private Animator animator;
	private FPSAnimator fpsAnimator;

	public NewtCanvasAWT createDrawable() {
		System.setProperty("jnlp.jogamp.common.utils.locks.Lock.timeout", "60000");
		glProfile = GLProfile.get(GLProfile.GL2ES2);// Default();
		GLCapabilities caps = new GLCapabilities(glProfile);
		caps.setBackgroundOpaque(false); // avoid flickering with 13.1 legacy
											// ATI drivers
		caps.setHardwareAccelerated(true);
		caps.setDoubleBuffered(true); // hardware swap
		caps.setSampleBuffers(false);
		Display display = NewtFactoryAWT.createDisplay(null);
		Screen screen = NewtFactoryAWT.createScreen(display, screenId);
		glWindow = GLWindow.create(screen, caps);
		GraphicsApi.getInstance().setGlWindow(glWindow);
		GraphicsApi.getInstance().setGLEventListener(SystemGLEventListener.getInstance());
		GraphicsApi.getInstance().setGLEventListener(GLEventListener.getInstance());
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
		// fpsAnimator = new FPSAnimator(60);
		animator = new Animator();
		animator.setRunAsFastAsPossible(true);
		animator.setUpdateFPSFrames(1000, System.out);
		animator.add(api.getGlWindow());
		animator.start();
		api.setGLEventListener(LogoWindow.getInstance());
		frame.setTitle("Initializing OpenGL... OK");
		api.fireGLInitEvent();
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
