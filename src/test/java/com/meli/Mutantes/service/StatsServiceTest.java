package com.meli.Mutantes.service;

import com.meli.Mutantes.MutantesApplicationTests;
import com.meli.Mutantes.entities.DnaCase;
import com.meli.Mutantes.view.StatsView;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatsServiceTest extends MutantesApplicationTests {

    @Test
    public void getStats() {
        dnaRepository.deleteAll();
        dnaService.checkDna(new DnaCase(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}));
        StatsView statsView = statsService.getStats();
        assertEquals(Optional.of(1L).get(), statsView.getCountMutantDna());
        assertTrue(Float.valueOf(1.0f).equals(statsView.getRatio()));
    }
}
