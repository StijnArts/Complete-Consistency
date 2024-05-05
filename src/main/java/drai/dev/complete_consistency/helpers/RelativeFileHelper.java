package drai.dev.complete_consistency.helpers;

import java.io.*;

public class RelativeFileHelper {
	public static String templateLoc = RelativeFileHelper.class.getClassLoader().getResource("templatedata")
			.toString()
			.replaceAll("%20"," ")
			.replaceAll("file:","")
			.replaceAll("build/resources/main","src/main/resources");
	public static String assetLoc = templateLoc.replaceAll("templatedata","assets");
	public static String generatedLoc = templateLoc.replaceAll("templatedata","generated").replaceAll("/resources", "");
	public static File getTemplateData(String filePath){
		return new File(templateLoc+filePath);
	}

	public static File getAssetLocation(String filePath){
		return new File(assetLoc+filePath);
	}

	public static File getGeneratedLocation(String filePath){
		return new File(generatedLoc+filePath);
	}
}
