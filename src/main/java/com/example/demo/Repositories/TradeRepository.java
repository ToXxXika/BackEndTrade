package com.example.demo.Repositories;

import com.example.demo.Documents.Trade;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeRepository extends MongoRepository<Trade, ObjectId> {
}
