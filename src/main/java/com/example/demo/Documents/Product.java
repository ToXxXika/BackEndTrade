package com.example.demo.Documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document(collection = "product")
public class Product {

    @Id
    private ObjectId id ;
    private String nomproduit;
    private String matriculeproduit;
    private String imgproduit;
    private Map<String, Date> infoTrade ;

    public Map<String, Date> getInfoTrade() {
        return infoTrade;
    }

    public void setInfoTrade(Map<String, Date> infoTrade) {
        this.infoTrade = infoTrade;
    }

    public ObjectId getId() {
        return id;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public String getMatriculeproduit() {
        return matriculeproduit;
    }

    public void setMatriculeproduit(String matriculeproduit) {
        this.matriculeproduit = matriculeproduit;
    }

    public String getImgproduit() {
        return imgproduit;
    }

    public void setImgproduit(String imgproduit) {
        this.imgproduit = imgproduit;
    }

    public Product(){

    }

    public Product(ObjectId id, String nomproduit, String matriculeproduit, String imgproduit) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.matriculeproduit = matriculeproduit;
        this.imgproduit = imgproduit;
    }
}
