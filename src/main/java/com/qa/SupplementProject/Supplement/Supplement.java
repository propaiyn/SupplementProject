package com.qa.SupplementProject.Supplement;

import com.sun.istack.NotNull;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


public class Supplement {
    private Long id;
    @NotNull
    private LocalDate entryDate;
    @NotNull
    private String name;
    private Long pubChemID;
    private String suppDescription;
    @NotNull
    private Double lowerBoundaryDoseMG;
    @NotNull
    private Double upperBoundaryDoseMG;

}
