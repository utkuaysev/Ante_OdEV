package com.project.corona.service;

import com.project.corona.entity.Haber;
import com.project.corona.repository.HaberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HaberService {
        @Autowired
        HaberRepository haberRepository;

    public String save(Haber haber) {
        return haberRepository.save(haber).get_id();
    }

    public void deleteById(String haber_id) {
        haberRepository.deleteById(haber_id);
    }
}
