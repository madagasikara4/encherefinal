package com.service;

import com.models.*;
import com.models.Error;
import com.repository.CAcategorieRepository;
import com.repository.CAclientRepository;
import com.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class Statistique {
    @Autowired
    private ResultatRepository resultatRepository;
    @Autowired
    private CAcategorieRepository catRepository;
    @Autowired
    private CAclientRepository cliRepository;

    public Object caclient(){
        try {
            final List<CAclient> categorie = new ArrayList<CAclient>();
            cliRepository.findAll().forEach(categorie1 -> categorie.add(categorie1));
            return new Data(categorie);
        }
        catch (Exception e){
            return new Error(e);
        }
    }

    public Object cacategorie(){
        try {
            final List<CAcategorie> categorie = new ArrayList<CAcategorie>();
            catRepository.findAll().forEach(categorie1 -> categorie.add(categorie1));
            return new Data(categorie);
        }
        catch (Exception e){
            return new Error(e);
        }
    }

    private List<Resultat> resultat()throws Exception{
        try {
            List<Resultat> categorie = new ArrayList<Resultat>();
            resultatRepository.findAll().forEach(categorie1 -> categorie.add(categorie1));
            return categorie;
        }
        catch (Exception e){
            throw e;
        }
    }

    public Object produitSort(int compare){
        try{
            List<Resultat> res=this.resultat();
            if(res.size()==0)
                return new Data(null);
            Resultat val=res.get(0);
            for (int i = 1; i < res.size(); i++) {
                if(val.getPrix()<res.get(i).getPrix() && compare==1)
                    val=res.get(i);
                if(val.getPrix()>res.get(i).getPrix() && compare==0)
                    val=res.get(i);
            }
            return new Data(val);
        }
        catch (Exception e){
            return new Error(e);
        }
    }

    public Object categorieSort(int compare){
        try{
            List<CAcategorie> categorie = new ArrayList<CAcategorie>();
            catRepository.findAll().forEach(categorie1 -> categorie.add(categorie1));
            Collections.sort(categorie,(s1,s2) -> s1.getSum() - s2.getSum());
            if(compare==0)
                return new Data(categorie.get(0));
            return new Data(categorie.get(categorie.size()-1));
        }
        catch (Exception e){
            return new Error(e);
        }
    }
}
