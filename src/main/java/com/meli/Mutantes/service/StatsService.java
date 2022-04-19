package com.meli.Mutantes.service;

import com.meli.Mutantes.repository.AdnRepository;
import com.meli.Mutantes.view.StatsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    private AdnRepository adnRepository;

    public StatsView getStats() {
        Long countMutant = adnRepository.countByIsMutantTrue();
        Long countHuman = adnRepository.count() - countMutant;
        float ratio = countHuman == 0 ? 1.0f : countMutant / countHuman;
        return new StatsView(countMutant, countHuman, ratio);
    }
}
