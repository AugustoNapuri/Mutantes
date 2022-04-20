package com.meli.Mutantes.service.util;

import com.meli.Mutantes.MutantesApplicationTests;
import org.junit.Test;

import static org.junit.Assert.*;

public class DnaSequenceUtil extends MutantesApplicationTests {

    @Test
    public void getColumnsSequences() {
        String[] columns = dnaSequenceUtil.extractColumns(new String[]{"0123", "1111", "2222", "3333"});
        assertEquals("0123", columns[0]);
        assertEquals("1123", columns[1]);
        assertEquals("2123", columns[2]);
        assertEquals("3123", columns[3]);
    }
    @Test
    public void getDiagonalsSequences() {
        String[] diagonals = dnaSequenceUtil.extractDiagonals(new String[]{"0XX7", "X16X", "X52X", "4XX3"});
        assertEquals("0123", diagonals[0]);
        assertEquals("4567", diagonals[1]);

        String row = "XXXXX";
        diagonals = dnaSequenceUtil.extractDiagonals(new String[]{row, row, row, row, row});
        assertEquals(6, diagonals.length);
    }

    @Test
    public void checkValidityTest() {
        String[] wrongSequence = {"ACGT", "ACGT"};
        String[] validSequence = {"ACGT", "ACGT", "ACGT", "ACGT"};
        assertFalse(dnaSequenceUtil.checkValidity(wrongSequence));
        assertTrue(dnaSequenceUtil.checkValidity(validSequence));
    }
}
