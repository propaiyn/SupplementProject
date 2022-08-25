package com.qa.SupplementProject.Supplement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;
import org.intellij.lang.annotations.Pattern;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

// Lombok use to reduce boilerplate code
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

//Configuring the database table from these columns using bean validation
@Data
@Entity
@Table

public class Supplement {
    @Id //Generate sequential ID
    @SequenceGenerator(
            name = "supplement_sequence",
            sequenceName = "supplement_sequence",
            initialValue = 1,
            allocationSize = 1 //Increment IDs by 1 for each new supplement
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "supplement_sequence"
    )

    @NotNull
    @JsonProperty private Long id;

    @NotNull
    @JsonProperty private LocalDate entryDate = LocalDate.now();

    @JsonProperty private String name;

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
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.pubChemId = Objects.requireNonNull(pubChemId, "PubChemId cannot be null");
        this.description = description;
        this.lowerBoundaryDoseMG = lowerBoundaryDoseMG;
        this.upperBoundaryDoseMG = upperBoundaryDoseMG;
    }

}
