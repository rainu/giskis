package de.rainu.giskis.consumer;

import java.nio.file.Path;

public class PathUtils {

	public static boolean isKismetFile(Path path){
		return path.getFileName().toFile().toString().endsWith(".netxml");
	}
}
