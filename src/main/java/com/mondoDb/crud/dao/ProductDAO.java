package com.mondoDb.crud.dao;


import com.mondoDb.crud.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<Product, Long> {
}
