package com.meli.Mutantes.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class AdnSequenceUtil {

    private static final Logger LOGGER = Logger.getLogger( AdnSequenceUtil.class.getName() );
    public static final String[] MUTANT_DNA_SEQUENCES = {"AAAA", "CCCC", "GGGG", "TTTT"};

    public boolean checkValidity(String[] sequence) {
        for (String row: sequence) {
            if (sequence.length != row.length()) {
                return false;
            }
        }
        return true;
    }

    public String[] extractColumns(String[] dnaRows) {
        String[] dnaColumns = new String[dnaRows.length];
        for (String row: dnaRows) {
            for (int i = 0; i < dnaRows.length; i++) {
                dnaColumns[i] = dnaColumns[i] == null ?
                        "".concat(Character.toString(row.charAt(i))) :
                        dnaColumns[i].concat(Character.toString(row.charAt(i)));
            }
        }
        LOGGER.log(Level.INFO,"COLUMNAS: " + Arrays.toString(dnaColumns));
        return dnaColumns;
    }

    public String[] extractDiagonals(String[] dnaRows) {
        List<String> diagonales = new ArrayList<>();
        //Uphill diagonals
        /*
         * ----------
         * |        |
         * |        |
         * |        |
         * <--------+
         */
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
        /*
         * ----------
         * ⌃        |
         * |        |
         * |        |
         * ----------
         */

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

        /*
         * <--------+
         * |        |
         * |        |
         * |        |
         * ----------
         */

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

        /*
         * ----------
         * |        |
         * |        |
         * |        |
         * v---------
         */

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


        LOGGER.log(Level.INFO,"DIAGONALS: " + diagonales);
        diagonales = diagonales.stream().filter(d -> d.length() >= MUTANT_DNA_SEQUENCES.length).collect(Collectors.toList());
        LOGGER.log(Level.INFO,"Filtering short length diagonal sequences...");
        LOGGER.log(Level.INFO, diagonales.toString());
        String[] diagonalesArray = new String[diagonales.size()];
        return diagonales.toArray(diagonalesArray);
    }
}
