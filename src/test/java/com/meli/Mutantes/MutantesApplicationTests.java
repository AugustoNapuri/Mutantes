package com.meli.Mutantes;

import com.meli.Mutantes.repository.AdnRepository;
import com.meli.Mutantes.rest.MutantesRestController;
import com.meli.Mutantes.rest.StatsRestController;
import com.meli.Mutantes.service.AdnService;
import com.meli.Mutantes.service.StatsService;
import com.meli.Mutantes.util.AdnSequenceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class MutantesApplicationTests {

    @Autowired
    protected AdnService adnService;
    @Autowired
    protected StatsService statsService;
    @Autowired
    protected AdnSequenceUtil adnSequenceUtil;
    @Autowired
    protected MutantesRestController mutantesRestController;
    @Autowired
    protected StatsRestController statsRestController;
    @Autowired
    protected AdnRepository adnRepository;
}
