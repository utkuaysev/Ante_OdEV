package com.project.corona.repository;

import com.mongodb.client.DistinctIterable;
import com.project.corona.dto.RaporDTO;
import com.project.corona.entity.Rapor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class RaporRepositoryImpl implements RaporRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public RaporRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<RaporDTO> aggregate(String tur, String sehir) {
        MatchOperation matchOperation = getMatchOperation(tur,sehir);
        GroupOperation groupOperation = getGroupOperation();
        ProjectionOperation projectionOperation = getProjectOperation();

        return mongoTemplate.aggregate(Aggregation.newAggregation(
                matchOperation,
                groupOperation,
                projectionOperation
        ), Rapor.class, RaporDTO.class).getMappedResults();
    }

    @Override
    public List<String> findDistinctSehirler() {
        DistinctIterable<String> sehirler = mongoTemplate.getCollection("Rapor").distinct("sehir", String.class);
        List<String> sehirler_list = new ArrayList<>();
        for (String sehir:sehirler) {
            sehirler_list.add(sehir);
        }
        return sehirler_list;
    }




    private MatchOperation getMatchOperation(String tur, String sehir) {
        Criteria turCriteria =  where("tur").is(tur);
        if (sehir != null){
            turCriteria = turCriteria.andOperator(where("sehir").is(sehir));
        }
        return match(turCriteria);
    }

    private GroupOperation getGroupOperation() {
        return group("date")
                .sum("sayi").as("y");
    }

    private ProjectionOperation getProjectOperation() {
        return project( "y")
                .and("date").previousOperation();
    }
}
