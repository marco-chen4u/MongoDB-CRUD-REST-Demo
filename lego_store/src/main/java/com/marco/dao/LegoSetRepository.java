package com.marco.dao;

import com.marco.model.LegoDifficulty;
import com.marco.model.LegoSet;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Collection;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
    Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);

    Collection<LegoSet> findAllByDifficultyEquals(String level);

    Collection<LegoSet> findAllByDifficultyAndNameStartingWith(LegoDifficulty difficulty, String name);

    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")    //in mongo-db, the pure query statement = db.getCollection('legosets').find({"delivery.deliveryFee" : {$lt : value}})
    Collection<LegoSet> findByDeliveryFeeLessThan(int price);
}
