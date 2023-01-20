package com.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import org.bson.Document;

public class Admin {
    private Object _id;
    private String nom;
    private String mdp;

    public Admin() {
    }

    public Admin(Object _id, String nom, String mdp) {
        this._id = _id;
        this.nom = nom;
        this.mdp = mdp;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static Admin getAdmin()throws Exception{
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = null;
        try  {
            mongoClient = MongoClients.create(uri);
            MongoDatabase database = mongoClient.getDatabase("enchere");
            MongoCollection table=database.getCollection("admin");
            Document student1 = (Document) table.find(new Document("nom", "admin")).first();
            ObjectMapper mapper = new ObjectMapper();
            Admin obj = mapper.readValue(student1.toJson(), Admin.class);
            return obj;
        }
        catch (Exception e){
            throw e;
        }
        finally {
            if(mongoClient!=null)
                mongoClient.close();
        }
    }

    public static boolean login(Admin admin)throws Exception{
        //Admin ad=Admin.getAdmin();
        Admin ad=new Admin();
        ad.setNom("admin");
        ad.setMdp("admin");
        if(admin.getNom().compareTo(ad.getNom())==0 && admin.getMdp().compareTo(ad.getMdp())==0)
            return true;
        return false;
    }

}