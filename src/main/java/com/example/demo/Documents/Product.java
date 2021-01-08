package com.example.demo.Documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "product")
public class Product {

    @Id
    private ObjectId id ;
    private String nomproduit;
    private String matriculeproduit;
    private String imgproduit;
    private Map<ObjectId,String> infoTrade ;
    private String category;
    private String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<ObjectId, String> getInfoTrade() {
        return infoTrade;
    }

    public void setInfoTrade(Map<ObjectId, String> infoTrade) {
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

    public Product(ObjectId id, String nomproduit, String matriculeproduit, String imgproduit, Map<ObjectId, String> infoTrade, String category, String description) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.matriculeproduit = matriculeproduit;
        this.imgproduit = imgproduit;
        this.infoTrade = infoTrade;
        this.category = category;
        this.description = description;
    }
}
