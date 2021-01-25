package com.example.demo.Controllers;

import com.example.demo.Documents.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate ;

    @Autowired
    private UserRepository UR ;

    @GetMapping("/getUsers")
    public List<User> getAllusers(){
        return UR.findAll();
    }
    @PostMapping("/adduser")
    public boolean adduser(@RequestBody User u){
        try {
            UR.save(u);
            return  true;
        }catch (Exception E){
            return  false ;
        }
    }
    @GetMapping("/login")
    public Principal login(Principal principal){
        System.out.println("userLogged"+principal);
        return principal ;
    }
    // this function is used before we set Spring Security
    /*
    @GetMapping("/verifUSer")
    public boolean verifuser(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username).and("password").is(password));
        try {
            List<User> users = mongoTemplate.find(query,User.class);
            if(users.isEmpty()){
                return false;
            }else return  true ;
        }catch (Exception E ){
            System.out.println(E.getMessage());return false;
        }
    }
*/
    @GetMapping("/getUserByCin")
    public int getuserbycin(@RequestParam(name = "cin") String cin ){

        Query query = new Query();
        query.addCriteria(Criteria.where("cin").is(cin));
        List<User> users = mongoTemplate.find(query, User.class);
        return users.size();
    }
    @GetMapping("/deleteuser")
    public boolean deleteuser(@RequestParam(name = "cin")String cin){
      try {
          Query query = new Query();
          query.addCriteria(Criteria.where("cin").is(cin));
          List<User> users = mongoTemplate.find(query, User.class);
          UR.deleteById(users.get(0).getId());
          return  true ;
      }catch (Exception E){ System.out.println(E.getMessage());return false ;}


    }


}
