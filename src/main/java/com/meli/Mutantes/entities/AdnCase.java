package com.meli.Mutantes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AdnCase {

    private String[] adn;
    private boolean isMutant;
}
