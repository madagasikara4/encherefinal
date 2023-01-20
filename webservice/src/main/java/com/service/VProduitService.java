package com.service;

import com.models.Data;
import com.models.Error;
import com.models.Produit;
import com.models.VProduit;
import com.repository.VProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VProduitService {
    @Autowired
    VProduitRepository vproduitRepository;

    public Object getAllVProduit()
    {
        try{
            List<VProduit> vproduit = new ArrayList<VProduit>();
            vproduitRepository.findAll().forEach(produit1 -> vproduit.add(produit1));
            return new Data(vproduit);
        }catch (Exception e){
            return new Error(e);
        }
    }

    public Object getValideByCategorie(int idcategorie){
        try{
            List<VProduit> produit = new ArrayList<VProduit>();
            vproduitRepository.findAll().forEach(produit1 -> produit.add(produit1));
            List<VProduit> actuProduit = new ArrayList<VProduit>();
            Date date=new Date();
            for(int i=0;i<produit.size();i++){
                VProduit pdt=(VProduit) produit.get(i);
                if((pdt.getIdcategorie()==idcategorie) && (pdt.getStatut()==0)){
                    actuProduit.add(pdt);
                }
            }
            return new Data(actuProduit);
        }catch(Exception e){
            return new Error(e);
        }
    }

    public Object getVProduitById(int id) {
        try {
            return new Data(vproduitRepository.findById(id).get());
        }
        catch (Exception e){
            return new Error(e);
        }
    }

    public Object getVProduitByIdUtilisateur(int id) {
        try {
            List<VProduit> vproduit = new ArrayList<VProduit>();
            vproduitRepository.findAll().forEach(produit1 -> vproduit.add(produit1));
            for (int i = 0; i < vproduit.size(); i++) {
                if(vproduit.get(i).getIdUtilisateur()==id)
                    continue;
                vproduit.remove(i);
                i--;
            }
            return new Data(vproduit);
        }
        catch (Exception e){
            return new Error(e);
        }
    }

}
