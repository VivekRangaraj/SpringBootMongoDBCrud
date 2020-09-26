package com.mongoDb.crud.service;

import com.mongoDb.crud.model.ProductSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceService {

    @Autowired
    private MongoOperations mongo;

    public long generateSequence(String seqName) {
        ProductSequence counter = mongo.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                ProductSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
