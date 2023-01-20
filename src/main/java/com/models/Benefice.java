package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "benefice")
public class Benefice {

        @Id
        @Column(name = "idbenefice")
        private int idbenefice;

        @Column(name = "idproduit")
        private int idproduit;

        @Column(name = "valeur")
        private float valeur;

    public int getIdBenefice() {
        return idbenefice;
    }

    public void setIdbenefice(int idbenefice) {
        this.idbenefice = idbenefice;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }
}
