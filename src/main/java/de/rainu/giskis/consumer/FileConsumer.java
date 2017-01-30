package de.rainu.giskis.consumer;

import java.nio.file.Path;
import java.util.function.Consumer;

public interface FileConsumer extends Consumer<Path> {

	boolean responsibleFor(Path path);
}
