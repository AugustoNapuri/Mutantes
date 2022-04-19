package com.meli.Mutantes.rest;

import com.meli.Mutantes.entities.AdnCase;
import com.meli.Mutantes.service.AdnService;
import com.meli.Mutantes.util.AdnSequenceUtil;
import com.meli.Mutantes.view.AdnCaseRequest;
import com.meli.Mutantes.view.AdnCaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "mutant")
public class MutantesRestController {

    @Autowired
    private AdnService adnService;
    @Autowired
    private AdnSequenceUtil adnSequenceUtil;

    @GetMapping(path = "/random")
    public ResponseEntity getRandom() {
        return new ResponseEntity(adnService.getRandomAdn(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(adnService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity checkMutant(@RequestBody AdnCaseRequest adnCaseRequest) {
        if (!adnSequenceUtil.checkValidity(adnCaseRequest.getAdn())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        AdnCaseResponse adnCaseResponse = new AdnCaseResponse(
                adnService.checkAdn(new AdnCase(adnCaseRequest.getAdn())).isMutant());
        if (adnCaseResponse.isMutant()) {
            return new ResponseEntity(adnCaseResponse, HttpStatus.OK);
        }
        return new ResponseEntity(adnCaseResponse, HttpStatus.FORBIDDEN);
    }
}
