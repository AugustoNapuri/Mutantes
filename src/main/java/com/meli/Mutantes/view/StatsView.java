package com.meli.Mutantes.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsView {

    private Long countMutantDna;
    private Long countHumanDna;
    private float ratio;

}
