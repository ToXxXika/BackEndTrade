package com.example.demo.Service;

import com.example.demo.Documents.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository UR;
    @Autowired
    private MongoTemplate mongoTemplate ;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails Result = null;
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(s));
        try {
            List<User> users = mongoTemplate.find(query,User.class);
            for (User i:users
                 ) {
                System.out.println(i.getUsername());
            }
            if(users.isEmpty()){
                Result = null ;
            }else {
                System.out.println("************** LoadUserByUsername Success **************");

                Result = (UserDetails) users.get(0);
            }
        }catch (Exception E){
            System.out.println(E.getMessage());
        }
             return Result ;

    }
}
