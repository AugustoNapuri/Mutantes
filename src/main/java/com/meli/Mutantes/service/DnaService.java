package com.meli.Mutantes.service;

import com.meli.Mutantes.entities.DnaCase;
import com.meli.Mutantes.repository.DnaRepository;
import com.meli.Mutantes.util.DnaSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DnaService {

    private static final Logger LOGGER = Logger.getLogger( DnaService.class.getName() );
    @Autowired
    private DnaRepository repository;
    @Autowired
    private DnaSequenceUtil dnaSequenceUtil;

    public DnaCase checkDna(DnaCase dnaCase) {
        int matchesCounter = 0;
        String[] dnaRows = dnaCase.getDna();
        String[] dnaColumns = dnaSequenceUtil.extractColumns(dnaCase.getDna());
        String[] diagonales = dnaSequenceUtil.extractDiagonals(dnaCase.getDna());

        matchesCounter += analizeDnaSequences(dnaRows);
        matchesCounter += analizeDnaSequences(dnaColumns);
        matchesCounter += analizeDnaSequences(diagonales);
        dnaCase.setAmountMutantSequences(matchesCounter);

        if (dnaCase.getAmountMutantSequences() >= 2) {
            dnaCase.setMutant(true);
        }
        repository.save(dnaCase);
        return dnaCase;
    }

    private int analizeDnaSequences(String[] dnaSequences) {
        int matchesCounter = 0;
        for (String row: dnaSequences) {
            for (String mutantDnaSequence: DnaSequenceUtil.MUTANT_DNA_SEQUENCES) {
                if (row.contains(mutantDnaSequence)) {
                    matchesCounter++;
                }
            }
        }
        return matchesCounter;
    }

    public String[] getRandomDna() {
        Random r = new Random();
        int sizeOfSequence = r.nextInt(4) + DnaSequenceUtil.MUTANT_DNA_SEQUENCES.length;
        String[] nitrogens = {"A", "C", "G", "T"};

        String[] sequence = new String[sizeOfSequence];
        for (int i = 0; i < sizeOfSequence; i++) {
            String nitrogen = "";
            for (int j = 0; j < sizeOfSequence; j++) {
                nitrogen = nitrogen.concat(nitrogens[r.nextInt(4)]);
            }
            sequence[i] = nitrogen;
        }
        LOGGER.log(Level.INFO, "New random sequence created: " + Arrays.toString(sequence));
        return sequence;
    }

    public List<DnaCase> getAll() {
        return repository.findAll();
    }
}
