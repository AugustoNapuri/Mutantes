package com.meli.Mutantes.service;


import com.meli.Mutantes.entities.AdnCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdnService {

    private static final String[] MUTANT_DNA_SEQUENCES = {"AAAA", "CCCC", "GGGG", "TTTT"};

    public AdnCase checkAdn(AdnCase adnCase) {
        int matchesCounter = 0;
        String[] dnaRows = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        String[] dnaColumns = extractColumns(dnaRows);
        String[] diagonales = extractDiagonals(dnaRows);

        matchesCounter += analizeAdnSequences(dnaRows);
        matchesCounter += analizeAdnSequences(dnaColumns);
        matchesCounter += analizeAdnSequences(diagonales);

        if (matchesCounter >= 2) {
            return new AdnCase(dnaRows, true);
        }
        return new AdnCase(adnCase.getAdn(), false);
    }

    private int analizeAdnSequences(String[] dnaSequences) {
        //Check Mutant ADN
        int matchesCounter = 0;
        for (String row: dnaSequences) {
            for (String mutantDnaSequence: MUTANT_DNA_SEQUENCES) {
                if (row.contains(mutantDnaSequence)) {
                    matchesCounter++;
                }
            }
        }
        return matchesCounter;
    }

    private String[] extractColumns(String[] dnaRows) {
        String[] dnaColumns = new String[dnaRows.length];
        for (String row: dnaRows) {
            for (int i = 0; i < dnaRows.length; i++) {
                dnaColumns[i] = dnaColumns[i] == null ?
                        "".concat(Character.toString(row.charAt(i))) :
                        dnaColumns[i].concat(Character.toString(row.charAt(i)));
            }
        }
        System.out.println(Arrays.toString(dnaColumns));
        return dnaColumns;
    }

    private String[] extractDiagonals(String[] dnaRows) {
        List<String> diagonales = new ArrayList<>();
        //Uphill diagonals

        for(int horizontalMovement = dnaRows.length - 1; horizontalMovement >= 0; horizontalMovement--) {
            int horizontalCoord = horizontalMovement;
            int verticalCoord = 0;
            String diagonal = "";
            while (horizontalCoord < dnaRows.length
                   && verticalCoord < dnaRows.length) {
                diagonal = diagonal.concat(Character.toString(
                        dnaRows[verticalCoord].charAt(horizontalCoord)));
                horizontalCoord++;
                verticalCoord++;
            }
            diagonales.add(diagonal);
        }

        for(int verticalMovement = 0; verticalMovement < dnaRows.length - 1; verticalMovement++) {
            int horizontalCoord = 0;
            int verticalCoord = verticalMovement + 1; // Avoid corner repetition, +1 position
            String diagonal = "";
            while (horizontalCoord < dnaRows.length
                    && verticalCoord < dnaRows.length) {
                diagonal = diagonal.concat(Character.toString(
                        dnaRows[verticalCoord].charAt(horizontalCoord)));
                horizontalCoord++;
                verticalCoord++;
            }
            diagonales.add(diagonal);
        }

        //Downhill diagonals

        for(int horizontalMovement = dnaRows.length - 1; horizontalMovement >= 0; horizontalMovement--) {
            int horizontalCoord = horizontalMovement;
            int verticalCoord = dnaRows.length - 1;
            String diagonal = "";
            while (horizontalCoord < dnaRows.length
                    && verticalCoord < dnaRows.length) {
                diagonal = diagonal.concat(Character.toString(
                        dnaRows[verticalCoord].charAt(horizontalCoord)));
                horizontalCoord++;
                verticalCoord--;
            }
            diagonales.add(diagonal);
        }

        for(int verticalMovement = dnaRows.length -1; verticalMovement > 0; verticalMovement--) {
            int horizontalCoord = 0;
            int verticalCoord = verticalMovement - 1; // Avoid corner repetition, -1 position
            String diagonal = "";
            while (horizontalCoord < dnaRows.length
                    && verticalCoord >= 0) {
                diagonal = diagonal.concat(Character.toString(
                        dnaRows[verticalCoord].charAt(horizontalCoord)));
                horizontalCoord++;
                verticalCoord--;
            }
            diagonales.add(diagonal);
        }


        System.out.println("Diagonales");
        System.out.println(diagonales);
        diagonales = diagonales.stream().filter(d -> d.length() >= 4).collect(Collectors.toList());
        System.out.println("Filtering -4 length sequences...");
        System.out.println(diagonales);
        String[] diagonalesArray = new String[diagonales.size()];
        return diagonales.toArray(diagonalesArray);
    }
}
