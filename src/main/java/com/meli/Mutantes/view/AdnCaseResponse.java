package com.meli.Mutantes.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdnCaseResponse {

    private String[] adn;
    private boolean isMutant;
}
