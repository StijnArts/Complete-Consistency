package drai.dev.complete_consistency.helpers;

import net.minecraft.resources.*;

public class IDHelper {
	public static ResourceLocation createID(String name, String modID){
		return new ResourceLocation(modID, name);
	}
}
