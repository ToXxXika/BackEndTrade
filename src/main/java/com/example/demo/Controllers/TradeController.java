package com.example.demo.Controllers;

import com.example.demo.Documents.Product;
import com.example.demo.Documents.Trade;
import com.example.demo.Repositories.TradeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        System.out.println(T.getMatriculetrade());
       boolean res=true ;
       try {

           List<Trade> ListTrade = TR.findAll();
           if(ListTrade.isEmpty()){
               TR.save(T);
               return true ;
           }else {
               for (Trade TT : ListTrade) {
                   System.out.println(TT.getClient1());
                   if ((TT.getMatriculetrade() != null) && (TT.getMatriculetrade().equals(T.getMatriculetrade()))) {
                       Random R = new Random();
                       int min = 100;
                       int max = 10000;
                       String Mat = "Mat" + (R.nextInt((max - min) + 1) + min);
                       T.setMatriculetrade(Mat);
                       TR.save(T);
                       res = true;
                   } else {
                       try {
                           TR.save(T);
                           res = true;
                       } catch (Exception E) {
                           System.out.println(E.getMessage());
                           res = false;
                       }
                   }
               }
           }
       }catch (Exception E){
           System.out.println(E.getMessage());
           res=false;
       }
         return  res ;
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
    @PostMapping("updateTrade")
    public boolean updateTrade(@RequestBody Trade T){
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("matriculetrade").is(T.getMatriculetrade()));
            Update update = new Update();
            update.set("produitc2",T.getProduitc2());
            update.set("client2",T.getClient2());
            mongoTemplate.updateFirst(query,update,Trade.class);
            return  true ;
        }catch (Exception E){
            System.out.println(E.getMessage());
            return false;
        }

    }
    @GetMapping("acceptTrade")
    public boolean AcceptTrade(@RequestParam("Mat") String mat){
        try{
            Query query = new Query();
            Query query2 = new Query();
            query.addCriteria(Criteria.where("matriculetrade").is(mat));
            Trade T = mongoTemplate.findOne(query,Trade.class);
            query2.addCriteria(Criteria.where("nomproduit").is(T.getProduitc1()));
            Product P = mongoTemplate.findOne(query2,Product.class);
            Update update = new Update();
            update.set("statutechange",1);
            mongoTemplate.updateFirst(query,update,Trade.class);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            Update updateProduit= new Update();
            Map<ObjectId, String> ListProd = new HashMap<>();
            ListProd.put(T.getId(),dtf.format(now));
            updateProduit.set("infoTrade",ListProd);
            mongoTemplate.updateFirst(query2,updateProduit,Product.class);
            return true;
        }catch (Exception E){
            System.out.println(E.getMessage());
            return false;
        }
    }


}
