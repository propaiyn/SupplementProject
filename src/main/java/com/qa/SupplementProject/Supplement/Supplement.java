package com.qa.SupplementProject.Supplement;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
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

    @NotNull
    private LocalDate entryDate = LocalDate.now();

    @Column
    private String name;

    //Implement regex no letters
    @Column
    private Long pubChemId;

    private String description;

    @Column(name = "supplement_lower_boundary_dose_in_mg")
    private Double lowerBoundaryDoseMG;

    @Column(name = "supplement_upper_boundary_dose_in_mg")
    private Double upperBoundaryDoseMG;

    public Supplement(
                      String name,
                      String description,
                      Long pubChemId,
                      Double lowerBoundaryDoseMG,
                      Double upperBoundaryDoseMG
    ) {
        this.name = name;
        this.description = description;
        this.pubChemId = pubChemId;
        this.lowerBoundaryDoseMG = lowerBoundaryDoseMG;
        this.upperBoundaryDoseMG = upperBoundaryDoseMG;
    }
}
