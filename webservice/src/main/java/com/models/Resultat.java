package com.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "v_encheretermine")
public class Resultat {
    @Id
    @Column(name = "idproduit")
    private int idproduit;
    @Column(name = "idutilisateur")
    private int idutilisateur;
    @Column(name = "nomproduit")
    private String nomproduit;
    @Column(name = "idcategorie")
    private int idcategorie;
    @Column(name = "descri")
    private String descri;
    @Column(name = "prixenchere")
    private int prixenchere;
    @Column(name = "prixmin")
    private int prixmin;
    @Column(name = "duree")
    private int duree;
    @Column(name = "debut")
    private Date debut;
    @Column(name = "statut")
    private int statut;
    @Column(name = "nomcategorie")
    private String nomcategorie;
    @Column(name = "gagnant")
    private int  gagnant;
    @Column(name = "prix")
    private int prix;
    @Column(name = "datemise")
    private Date datemise;

    public Resultat() {
    }

    public Resultat(int idproduit, int idutilisateur, String nomproduit, int idcategorie, String descri, int prixenchere, int prixmin, int duree, Date debut, int statut, String nomcategorie,  int gagnant, int prix, Date datemise) {
        this.idproduit = idproduit;
        this.idutilisateur = idutilisateur;
        this.nomproduit = nomproduit;
        this.idcategorie = idcategorie;
        this.descri = descri;
        this.prixenchere = prixenchere;
        this.prixmin = prixmin;
        this.duree = duree;
        this.debut = debut;
        this.statut = statut;
        this.nomcategorie = nomcategorie;
        this.gagnant = gagnant;
        this.prix = prix;
        this.datemise = datemise;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getPrixenchere() {
        return prixenchere;
    }

    public void setPrixenchere(int prixenchere) {
        this.prixenchere = prixenchere;
    }

    public int getPrixmin() {
        return prixmin;
    }

    public void setPrixmin(int prixmin) {
        this.prixmin = prixmin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }


    public int getGagnant() {
        return gagnant;
    }

    public void setGagnant(int gagnant) {
        this.gagnant = gagnant;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Date getDatemise() {
        return datemise;
    }

    public void setDatemise(Date datemise) {
        this.datemise = datemise;
    }
}
