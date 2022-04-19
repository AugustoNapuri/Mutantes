package com.meli.Mutantes.entities;

import com.meli.Mutantes.entities.util.ArrayToStringConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdnCase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ArrayToStringConverter.class)
    private String[] adn;
    private boolean isMutant;
    private int mutantSequences;

    public AdnCase(String[] adn) {
        this.isMutant = false;
        this.mutantSequences = 0;
        this.adn = adn;
    }
}
