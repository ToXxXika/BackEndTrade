package com.example.demo.Documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

    private ObjectId id ;
    private String cin ;
    private String name;
    private String surname;
    private String username;
    private String telephone;
    private String mail;


    public  User(){

    }

    public User(ObjectId id, String cin, String name, String surname, String username, String telephone, String mail) {
        this.id = id;
        this.cin = cin;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.telephone = telephone;
        this.mail = mail;
    }

    public ObjectId getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
