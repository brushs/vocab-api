package nrcan.gc.ca.vocabapi.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * A TombstoneComponent represents "tombstone data" for an object.  Tombstone data consists of the creation date,
 * last update date, and the user(s) who created and last updated the object.
 * 
 * A TombstoneComponent represents information about another object, and not used as a standalone object. 
 */
@Embeddable
public class TombstoneComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String createdByUserId;
	@Column(nullable = false)
	private LocalDate createdDate;
	@Column(nullable = false)
	private String lastUpdatedUserId;
	@Column(nullable = false)
	private LocalDate lastUpdatedDate;
}
