package de.rainu.giskis.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class is responsible for watching the scan directory for incoming files.
 */
@Component
public class FileReceiver {
	private final static Logger LOG = LoggerFactory.getLogger(FileReceiver.class);

	private final File scanDir;
	private final File outputDir;

	public FileReceiver(
			  @Value("${SCAN_DIR}") String scanDir,
			  @Value("${OUTPUT_DIR}") String outputDir) {

		this.scanDir = new File(scanDir);
		this.outputDir = new File(outputDir);
	}

	/**
	 * Starts the watching process. This is a blocking call.
	 * @param consumer The consumer of incoming files.
	 */
	public void watch(final Consumer<Path> consumer) {
		LOG.info("Start watching.\n\tScan-Directory: " + scanDir + "\n\tOutput-Directory: " + outputDir);

		while(true) {
			try {
				Files.walkFileTree(scanDir.toPath(), new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path curPath, BasicFileAttributes basicFileAttributes) throws IOException {
						LOG.info("Process file: " + curPath.toString());
						consumer.accept(curPath);

						moveFile(curPath);
						return FileVisitResult.CONTINUE;
					}
				});

				for(String child : listDirectories(scanDir)) {
					removeEmptyDirectories(new File(scanDir, child));
				}

				Thread.sleep(5000);
			} catch (IOException e) {
				LOG.error("IO-Error: " + e.getMessage(), e);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private void removeEmptyDirectories(File dir) {
		String[] directories = listDirectories(dir);

		for(String child : directories) {
			removeEmptyDirectories(new File(dir, child));
		}

		if(dir.list().length == 0){
			dir.delete();
			return;
		}
	}

	private String[] listDirectories(File dir) {
		String[] dirs = dir.list((current, name) -> new File(current, name).isDirectory());

		return dirs != null ? dirs : new String[]{};
	}

	private void moveFile(Path path) {
		final File target;

		if(path.getParent().toAbsolutePath().equals(scanDir.toPath().toAbsolutePath())) {
			//no subdirectory
			target = new File(outputDir, path.getFileName().toString());
		} else {
			String subDirs = path.getParent().toAbsolutePath().toString();
			subDirs = subDirs.substring(scanDir.toPath().toAbsolutePath().toString().length());

			File targetSubDir = new File(outputDir.toPath().toAbsolutePath().toString() + "/" + subDirs);
			targetSubDir.mkdirs();

			target = new File(targetSubDir, path.getFileName().toString());
		}

		LOG.info("Move file to output dir: " + target);
		path.toFile().renameTo(target);
	}
}
