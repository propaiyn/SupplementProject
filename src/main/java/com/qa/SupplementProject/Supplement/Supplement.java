package com.qa.SupplementProject.Supplement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table
public class Supplement {
    @Id //Generate sequential ID
    @SequenceGenerator(
            name = "supplement_sequence",
            sequenceName = "supplement_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Recommended for postgres
            generator = "supplement_sequence" // Sequence that we just created
    )
    @JsonProperty private Long id;

    @NotNull
    @JsonProperty private LocalDate entryDate = LocalDate.now();


    @JsonProperty private String name;

    //Implement regex no letters

    @JsonProperty private Long pubChemId;

    @JsonProperty private String description;


    @JsonProperty private Double lowerBoundaryDoseMG;


    @JsonProperty private Double upperBoundaryDoseMG;

     public Supplement(
                      String name,
                      Long pubChemId,
                      String description,
                      Double lowerBoundaryDoseMG,
                      Double upperBoundaryDoseMG
    ) {
        this.name = name;
        this.pubChemId = pubChemId;
        this.description = description;
        this.lowerBoundaryDoseMG = lowerBoundaryDoseMG;
        this.upperBoundaryDoseMG = upperBoundaryDoseMG;
    }
}
