package de.rainu.giskis.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.List;

/**
 * This special {@link FileConsumer} compose all other {@link FileConsumer}s inside the Spring application context.
 */
@Component
public class FileConsumerChain implements FileConsumer {
	@Autowired
	private List<FileConsumer> allConsumer;

	@PostConstruct
	public void removeMe(){
		allConsumer.remove(this);
	}

	@Override
	public void accept(final Path path) {
		for(FileConsumer consumer : allConsumer) {
			if(consumer.responsibleFor(path)) {
				consumer.accept(path);
			}
		}
	}

	@Override
	public boolean responsibleFor(Path path) {
		//i am responsible for all files...
		return true;
	}
}
