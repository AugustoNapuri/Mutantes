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
public class DnaCase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ArrayToStringConverter.class)
    private String[] dna;
    private boolean isMutant;
    private int amountMutantSequences;

    public DnaCase(String[] dna) {
        this.isMutant = false;
        this.amountMutantSequences = 0;
        this.dna = dna;
    }
}
