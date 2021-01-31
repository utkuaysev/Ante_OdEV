package com.project.corona.controller;

import com.project.corona.service.HaberRecognitor;
import com.project.corona.dto.RaporlarDTO;
import com.project.corona.repository.RaporRepository;
import com.project.corona.service.RaporService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GrafikController {



    @Autowired
    private RaporService raporService;

    @RequestMapping(value = "/sehirler", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getSehirler() {
        return new ResponseEntity<List<String>>(raporService.findDistinctSehirler(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sehir", method = RequestMethod.POST)
    public ResponseEntity<List<RaporlarDTO>> getBySehir(@RequestBody HashMap<String, String> val) {
        String sehir = val.get("sehir");
        if (sehir == null)
            return raporService.getListRaporlarDTO(null);
        return raporService.getListRaporlarDTO(val.get("sehir"));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<RaporlarDTO>> getAllData() {
        return raporService.getListRaporlarDTO(null);
    }

}






