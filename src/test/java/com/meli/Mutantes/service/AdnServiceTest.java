package com.meli.Mutantes.service;

import com.meli.Mutantes.MutantesApplicationTests;
import com.meli.Mutantes.entities.AdnCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdnServiceTest extends MutantesApplicationTests {

    @Test
    public void checkAdn() {
        AdnCase adnCase = adnService.checkAdn(new AdnCase(new String[]{"AAAA", "1A11", "22A2", "333A"}));
        assertTrue(adnCase.isMutant());
        assertEquals(2, adnCase.getMutantSequences());
    }

    @Test
    public void getRandomSequence() {
        String[] sequence = adnService.getRandomAdn();
        assertEquals(sequence.length, sequence[0].length());
    }
}
