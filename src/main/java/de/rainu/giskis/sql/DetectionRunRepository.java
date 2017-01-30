package de.rainu.giskis.sql;

import de.rainu.giskis.model.DetectionRun;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static de.rainu.giskis.sql.DatabaseConstants.*;

/**
 * This {@link CrudRepository} is responsible for accessing {@link DetectionRun}
 */
public interface DetectionRunRepository extends CrudRepository<DetectionRun, BigInteger> {

	/**
	 * Checks if a {@link DetectionRun} with the start and card-uuid exists already in the database.
	 *
	 * @param start The startdate of the {@link DetectionRun}
	 * @param cardSourceUuid The uuid for the {@link de.rainu.giskis.model.CardSource}
	 * @return Returns true if one {@link DetectionRun} exsists. Otherwise false.
	 */
	@Query("SELECT " +
			  "CASE WHEN COUNT(d) > 0 " +
			  "THEN true " +
			  "ELSE false " +
			  "END " +
		  "FROM detection_run d " +
		  "WHERE d.time = ?1 AND d.cardSource.uuid = ?2")
	boolean exists(LocalDateTime start, String cardSourceUuid);

	/**
	 * Checks if the given {@link DetectionRun} exists already in the database.
	 *
	 * @param run The {@link DetectionRun} to check.
	 * @return Returns true if one {@link DetectionRun} exsists. Otherwise false.
	 */
	default boolean exists(DetectionRun run) {
		return exists(run.getTime(), run.getCardSource().getUuid());
	}

	/**
	 * Gets the date of the last {@link DetectionRun} stored in the database.
	 *
	 * @return Returns the date.
	 */
	@Query(value = "SELECT d." + DETECTION_RUN_START + " FROM " + DETECTION_RUN + " AS d " +
			  "ORDER BY d." + DETECTION_RUN_START + " DESC " +
			  "LIMIT 1",
		nativeQuery = true)
	LocalDateTime getLastRunTime();
}
