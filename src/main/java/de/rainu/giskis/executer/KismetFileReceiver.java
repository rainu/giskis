package de.rainu.giskis.executer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class KismetFileReceiver {
	private final static Logger LOG = LoggerFactory.getLogger(KismetFileReceiver.class);

	private final File scanDir;
	private final File outputDir;

	public KismetFileReceiver(
			  @Value("${SCAN_DIR}") String scanDir,
			  @Value("${OUTPUT_DIR}") String outputDir) {

		this.scanDir = new File(scanDir);
		this.outputDir = new File(outputDir);
	}

	public void watch(Consumer<Path> consumer) {
		LOG.info("Start watching.\nScan-Directory: " + scanDir + "\nOutput-Directory: " + outputDir);

		while(true) {
			try {
				for(Path kismetFile : listKismetFiles()) {
					LOG.info("Process file: " + kismetFile.toString());

					consumer.accept(kismetFile);

					LOG.info("Move file to output dir.");
					kismetFile.toFile().renameTo(new File(outputDir, kismetFile.getFileName().toString()));
				}

				Thread.sleep(5000);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private List<Path> listKismetFiles() {
		List<Path> files = new ArrayList<>();

		try {
			Files.walkFileTree(scanDir.toPath(), new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
					if(path.toString().endsWith(".netxml")){
						files.add(path);
					}

					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			LOG.error("Error on walking trough the scan dir!", e);
		}

		return files;
	}
}
