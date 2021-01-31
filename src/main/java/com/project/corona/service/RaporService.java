package com.project.corona.service;

import com.project.corona.dto.RaporlarDTO;
import com.project.corona.entity.Rapor;
import com.project.corona.repository.RaporRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class RaporService {

    @Autowired
    HaberRecognitor haberRecognitor;
    @Autowired
    RaporRepository raporRepository;

    public ResponseEntity<List<RaporlarDTO>> getListRaporlarDTO(String sehir) {
        HashSet<String> turler = haberRecognitor.getTurler();
        List<RaporlarDTO> raporlarDTOList = new ArrayList<>();
        for (String tur : turler) {
            raporlarDTOList.add(new RaporlarDTO(raporRepository.aggregate(tur, sehir), tur));
        }
        return new ResponseEntity<List<RaporlarDTO>>(raporlarDTOList, HttpStatus.OK);
    }
    public List<String> findDistinctSehirler() {
        return raporRepository.findDistinctSehirler();
    }

    public List<Rapor> saveAll(List<Rapor> raporList) {
        return raporRepository.saveAll(raporList);
    }
}
