package com.meli.Mutantes.rest;

import com.meli.Mutantes.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "stats")
public class StatsRestController {

    @Autowired
    private StatsService statsService;

    @GetMapping
    public ResponseEntity getStats() {
        return new ResponseEntity(statsService.getStats(), HttpStatus.OK);
    }
}
