package com.meli.Mutantes.service;


import com.meli.Mutantes.entities.AdnCase;
import com.meli.Mutantes.repository.AdnRepository;
import com.meli.Mutantes.util.AdnSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AdnService {

    private static final Logger LOGGER = Logger.getLogger( AdnService.class.getName() );
    @Autowired
    private AdnRepository repository;
    @Autowired
    private AdnSequenceUtil adnSequenceUtil;

    public AdnCase checkAdn(AdnCase adnCase) {
        int matchesCounter = 0;
        String[] dnaRows = adnCase.getAdn();
        String[] dnaColumns = adnSequenceUtil.extractColumns(adnCase.getAdn());
        String[] diagonales = adnSequenceUtil.extractDiagonals(adnCase.getAdn());

        matchesCounter += analizeAdnSequences(dnaRows);
        matchesCounter += analizeAdnSequences(dnaColumns);
        matchesCounter += analizeAdnSequences(diagonales);
        adnCase.setMutantSequences(matchesCounter);

        if (adnCase.getMutantSequences() >= 2) {
            adnCase.setMutant(true);
        }
        repository.save(adnCase);
        return adnCase;
    }

    private int analizeAdnSequences(String[] dnaSequences) {
        int matchesCounter = 0;
        for (String row: dnaSequences) {
            for (String mutantDnaSequence: AdnSequenceUtil.MUTANT_DNA_SEQUENCES) {
                if (row.contains(mutantDnaSequence)) {
                    matchesCounter++;
                }
            }
        }
        return matchesCounter;
    }

    public String[] getRandomAdn() {
        Random r = new Random();
        int sizeOfSequence = r.nextInt(4) + AdnSequenceUtil.MUTANT_DNA_SEQUENCES.length;
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

    public List<AdnCase> getAll() {
        return repository.findAll();
    }
}
