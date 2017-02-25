package de.rainu.giskis.nosql;

import de.rainu.giskis.model.DetectionRun;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;
import java.time.LocalDateTime;

public interface DetectionRunRepository extends MongoRepository<DetectionRun, BigInteger> {

	default boolean exists(final DetectionRun run) {
		return count(run.getTime(), run.getCardSource().getUuid()) > 0;
	}

	@Query(value = "{ 'time' : ?0, 'cardSource.uuid' : ?1 }", count = true)
	long count(LocalDateTime start, String cardSourceUuid);

	default LocalDateTime getLastRunTime() {
		return findTopByOrderByTimeDesc().getTime();
	}

	@Query(fields = "{ 'time': 1 }")
	DetectionRun findTopByOrderByTimeDesc();
}
