package com.meli.Mutantes.service;

import com.meli.Mutantes.repository.DnaRepository;
import com.meli.Mutantes.view.StatsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private DnaRepository dnaRepository;

    public StatsView getStats() {
        Long countMutant = dnaRepository.countByIsMutantTrue();
        Long countTotal = dnaRepository.count();
        float ratio = countTotal == 0 ? 0.0f : (float) countMutant / countTotal;
        return new StatsView(countMutant, countTotal - countMutant, ratio);
    }
}
