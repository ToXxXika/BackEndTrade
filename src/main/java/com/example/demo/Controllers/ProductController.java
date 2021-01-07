package com.example.demo.Controllers;

import com.example.demo.Documents.Product;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductRepository PR;

    @GetMapping("/getProducts")
    public List<Product> getAllProducts(){return PR.findAll();}

    @PostMapping("addProduct")
    public boolean addProduct(@RequestBody Product p){
        try{
            PR.save(p);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @GetMapping("/getProductByProductName")
    public int getProductByName(@RequestParam(name = "name") String name){
        Query q = new Query();
        q.addCriteria(Criteria.where("nomproduit").is(name));
        List<Product> products = mongoTemplate.find(q, Product.class);
        return products.size();
    }

}
