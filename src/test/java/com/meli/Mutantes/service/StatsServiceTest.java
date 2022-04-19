package com.meli.Mutantes.service;

import com.meli.Mutantes.MutantesApplicationTests;
import com.meli.Mutantes.entities.AdnCase;
import com.meli.Mutantes.view.StatsView;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Optional;

public class StatsServiceTest extends MutantesApplicationTests {

    @Test
    public void getStats() {
        adnRepository.deleteAll();
        adnService.checkAdn(new AdnCase(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}));
        StatsView statsView = statsService.getStats();
        assertEquals(Optional.of(1L).get(), statsView.getCountMutantDna());
        assertTrue(Float.valueOf(1.0f).equals(statsView.getRatio()));
    }
}
