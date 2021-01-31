package com.project.corona.repository;

import com.project.corona.dto.RaporDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaporRepositoryCustom {

    List<RaporDTO> aggregate(String tur, String sehir);
    List<String> findDistinctSehirler();


}
