package com.example.demo.Repositories;

import com.example.demo.Documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
