package com.meli.Mutantes.rest;

import com.meli.Mutantes.entities.DnaCase;
import com.meli.Mutantes.service.DnaService;
import com.meli.Mutantes.util.DnaSequenceUtil;
import com.meli.Mutantes.view.DnaCaseRequest;
import com.meli.Mutantes.view.DnaCaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "mutant")
public class MutantesRestController {

    @Autowired
    private DnaService dnaService;
    @Autowired
    private DnaSequenceUtil dnaSequenceUtil;

    @GetMapping(path = "/random")
    public ResponseEntity getRandom() {
        return new ResponseEntity(dnaService.getRandomAdn(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(dnaService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity checkMutant(@RequestBody DnaCaseRequest dnaCaseRequest) {
        if (!dnaSequenceUtil.checkValidity(dnaCaseRequest.getDna())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        DnaCaseResponse dnaCaseResponse = new DnaCaseResponse(
                dnaService.checkDna(new DnaCase(dnaCaseRequest.getDna())).isMutant());
        if (dnaCaseResponse.isMutant()) {
            return new ResponseEntity(dnaCaseResponse, HttpStatus.OK);
        }
        return new ResponseEntity(dnaCaseResponse, HttpStatus.FORBIDDEN);
    }
}
