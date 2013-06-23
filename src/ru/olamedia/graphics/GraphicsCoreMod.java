package ru.olamedia.graphics;

import ru.olamedia.graphics.frame.ApplicationFrame;
import ru.olamedia.graphics.frame.RedWindow;
import ru.olamedia.modloader.ModBase;
import ru.olamedia.modloader.ModDependencyException;
import ru.olamedia.modloader.ModInfo;

public class GraphicsCoreMod extends ModBase {

	public GraphicsCoreMod(ModInfo info) {
		super(info);
		info.setName("Graphics Core");
	}

	@Override
	public void register(ModInfo info) throws ModDependencyException {
		showFrame();

	}

	public void showFrame() {
		ApplicationFrame f = ApplicationFrame.getInstance();
		f.setTitle("Graphics Core");
		f.initGL();
		f.setVisible(true);
		GraphicsApi.getInstance().setGLEventListener(new RedWindow());
	}

}
