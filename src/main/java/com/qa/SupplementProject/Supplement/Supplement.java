package com.qa.SupplementProject.Supplement;

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
    private Long id;
    private LocalDate entryDate;
    private String name;
    private Long pubChemID;
    private String suppDescription;
    private Double lowerBoundaryDoseMG;
    private Double upperBoundaryDoseMG;

}
