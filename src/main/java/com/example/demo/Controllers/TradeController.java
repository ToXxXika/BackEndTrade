package com.example.demo.Controllers;

import com.example.demo.Documents.Trade;
import com.example.demo.Repositories.TradeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private MongoTemplate mongoTemplate ;
    @Autowired
    private TradeRepository TR ;

    @PostMapping("/addTrade")
    public boolean addTrade(@RequestBody Trade T){
      try{
          TR.save(T);
          return true ;
      }catch(Exception E){
          System.out.println(E.getMessage());
          return false;
      }
    }
    @GetMapping("/getTradeByPr")
    public List<Trade> getTrades(@RequestParam(name = "nomp") String nomProduit){
        Query query = new Query();
        query.addCriteria(Criteria.where("cin").is(nomProduit));
        List<Trade> Trades = mongoTemplate.find(query, Trade.class);
        return Trades;
    }
    @GetMapping("getAllTrades")
    public List<Trade> getTrades2(){
        return TR.findAll();
    }

    @GetMapping("getTradeById")
    public Optional<Trade> getTradeById(@RequestParam(name = "id")ObjectId id){
        return  TR.findById(id);
    }
}
