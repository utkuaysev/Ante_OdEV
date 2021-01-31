package com.project.corona.controller;


import com.project.corona.service.HaberRecognitor;
import com.project.corona.entity.Haber;
import com.project.corona.entity.Rapor;
import com.project.corona.repository.HaberRepository;
import com.project.corona.repository.RaporRepository;
import com.project.corona.service.HaberService;
import com.project.corona.service.RaporService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class KayitController {
    @Autowired
    private RaporService raporService;
    @Autowired
    private HaberService haberService;
    @Autowired
    private HaberRecognitor haberRecognitor;

    @RequestMapping(value = "/haber", method = RequestMethod.POST)
    public String yeniHaber(@RequestBody HashMap<String, String> val) throws Exception {
        String haber = val.get("val");
        String haber_id = haberService.save(new Haber(haber));
        Rapor[] raporlar = haberRecognitor.getRaporlarFromHaber(val.get("val"), haber_id);
        if (raporlar != null)
            raporService.saveAll(Arrays.asList(raporlar));
        else {
            haberService.deleteById(haber_id);
            throw new Exception("Metin formatı yanlış.");
        }
        return "done";
    }

}
