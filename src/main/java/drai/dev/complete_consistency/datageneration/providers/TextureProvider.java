package drai.dev.complete_consistency.datageneration.providers;

import drai.dev.complete_consistency.helpers.*;

public class TextureProvider{
	public static void run(){
		TextureHelper.getRequiredTextures().forEach(runnable -> runnable.run());
	}
}
