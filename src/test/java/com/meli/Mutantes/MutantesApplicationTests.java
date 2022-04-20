package com.meli.Mutantes;

import com.meli.Mutantes.repository.DnaRepository;
import com.meli.Mutantes.rest.MutantesRestController;
import com.meli.Mutantes.rest.StatsRestController;
import com.meli.Mutantes.service.DnaService;
import com.meli.Mutantes.service.StatsService;
import com.meli.Mutantes.util.DnaSequenceUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class MutantesApplicationTests {

    @Autowired
    protected DnaService dnaService;
    @Autowired
    protected StatsService statsService;
    @Autowired
    protected DnaSequenceUtil dnaSequenceUtil;
    @Autowired
    protected MutantesRestController mutantesRestController;
    @Autowired
    protected StatsRestController statsRestController;
    @Autowired
    protected DnaRepository dnaRepository;
}
