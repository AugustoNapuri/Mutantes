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
        Long countTotal = adnRepository.count();
        float ratio = countTotal == 0 ? 0.0f : (float) countMutant / countTotal;
        return new StatsView(countMutant, countTotal - countMutant, ratio);
    }
}
