package com.meli.Mutantes.service;

import com.meli.Mutantes.MutantesApplicationTests;
import com.meli.Mutantes.entities.DnaCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DnaServiceTest extends MutantesApplicationTests {

    @Test
    public void checkAdn() {
        DnaCase adnCase = dnaService.checkDna(new DnaCase(new String[]{"AAAA", "1A11", "22A2", "333A"}));
        assertTrue(adnCase.isMutant());
        assertEquals(2, adnCase.getAmountMutantSequences());
    }

    @Test
    public void getRandomSequence() {
        String[] sequence = dnaService.getRandomAdn();
        assertEquals(sequence.length, sequence[0].length());
    }
}
