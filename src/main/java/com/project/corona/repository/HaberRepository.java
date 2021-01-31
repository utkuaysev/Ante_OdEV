package com.project.corona.repository;

import com.project.corona.entity.Haber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HaberRepository extends MongoRepository<Haber, String> {
    //
}
