package com.example.demo.Documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

    @Id
    private String id ;
    private String nomproduit;
    private String matriculeproduit;
    private String imgproduit;

    public String getId() {
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

    public Product(String id, String nomproduit, String matriculeproduit, String imgproduit) {
        this.id = id;
        this.nomproduit = nomproduit;
        this.matriculeproduit = matriculeproduit;
        this.imgproduit = imgproduit;
    }
}
