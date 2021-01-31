package com.project.corona.repository;

import com.project.corona.entity.Rapor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaporRepository extends MongoRepository<Rapor, String>, RaporRepositoryCustom {
    //
}
