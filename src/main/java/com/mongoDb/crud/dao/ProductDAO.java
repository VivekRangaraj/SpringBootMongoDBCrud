package com.mongoDb.crud.dao;


import com.mongoDb.crud.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<Product, Long> {
}
