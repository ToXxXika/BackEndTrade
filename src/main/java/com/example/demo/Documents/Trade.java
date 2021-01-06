package com.example.demo.Documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trade")
public class Trade {

    @Id
    private String id ;
    private String client1;
    private String client2;
    private boolean statutechange ;
    private boolean confirmationc1;
    private String produitc1;
    private String produitc2;
    private boolean confirmationadmin;

    public Trade() {
    }

    public Trade(String id, String client1, String client2, boolean statutechange, boolean confirmationc1, String produitc1, String produitc2, boolean confirmationadmin) {
        this.id = id;
        this.client1 = client1;
        this.client2 = client2;
        this.statutechange = statutechange;
        this.confirmationc1 = confirmationc1;
        this.produitc1 = produitc1;
        this.produitc2 = produitc2;
        this.confirmationadmin = confirmationadmin;
    }

    public void setClient1(String client1) {
        this.client1 = client1;
    }

    public void setClient2(String client2) {
        this.client2 = client2;
    }

    public void setStatutechange(boolean statutechange) {
        this.statutechange = statutechange;
    }

    public void setConfirmationc1(boolean confirmationc1) {
        this.confirmationc1 = confirmationc1;
    }

    public void setProduitc1(String produitc1) {
        this.produitc1 = produitc1;
    }

    public void setProduitc2(String produitc2) {
        this.produitc2 = produitc2;
    }

    public void setConfirmationadmin(boolean confirmationadmin) {
        this.confirmationadmin = confirmationadmin;
    }

    public String getId() {
        return id;
    }

    public String getClient1() {
        return client1;
    }

    public String getClient2() {
        return client2;
    }

    public boolean isStatutechange() {
        return statutechange;
    }

    public boolean isConfirmationc1() {
        return confirmationc1;
    }

    public String getProduitc1() {
        return produitc1;
    }

    public String getProduitc2() {
        return produitc2;
    }

    public boolean isConfirmationadmin() {
        return confirmationadmin;
    }
}
