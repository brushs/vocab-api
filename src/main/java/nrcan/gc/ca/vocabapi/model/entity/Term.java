package nrcan.gc.ca.vocabapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@Entity
public class Term implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameFr;

    @Column
    private String descriptionEn;

    @Column
    private String descriptionFr;

    @Column
    private boolean activeInd;

    @Column
    private int vocabularyId;

    @Column
    private Integer parentTermId;

    @Column(nullable = false)
    private String createdByUserId;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = false)
    private String lastUpdatedUserId;

    @Column(nullable = false)
    private LocalDate lastUpdatedDate;

}
