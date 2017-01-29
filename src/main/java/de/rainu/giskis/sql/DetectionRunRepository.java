package de.rainu.giskis.sql;

import de.rainu.giskis.model.DetectionRun;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface DetectionRunRepository extends CrudRepository<DetectionRun, Long> {
	@Query("SELECT " +
			  "CASE WHEN COUNT(d) > 0 " +
			  "THEN true " +
			  "ELSE false " +
			  "END " +
		  "FROM detection_run d " +
		  "WHERE d.time = ?1 AND d.cardSource.uuid = ?2")
	boolean exists(LocalDateTime start, String cardSourceUuid);

	default boolean exists(DetectionRun run) {
		return exists(run.getTime(), run.getCardSource().getUuid());
	}
}
