package com.qa.SupplementProject.Supplement;

import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Supplement {
    private Long id;
    private LocalDate entryDate;
    private String name;
    private Long pubChemID;
    private String suppDescription;
    private Double lowerBoundaryDoseMG;
    private Double upperBoundaryDoseMG;

}
