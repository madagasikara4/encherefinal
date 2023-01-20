package com.service;

import com.models.*;
import com.models.Error;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnchereService {
    @Autowired
    EnchereRepository enchereRepository;

    @Autowired
    BloquageRepository bloquageRepository;

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    VEncherePrixMaxRepository prixMaxRepository;

    @Autowired
    UserRepository userRepository;

    public Object encherir(Enchere enchere){
        try{
            VEncherePrixMax prixmax=getPrixMaxProduit(enchere.getIdproduit());
            if(prixmax!=null && enchere.getPrix()<=prixmax.getPrix()){
                throw new Exception("Prix inférieure à l'enchère précédente");
            }
            //Données de test amin'ny enchere + compte rechargé

            //Verifier si argent de l'user (argent user - bloquage user) > enchere
            //v_montant_user.montant>enchere
            if(getMontantUser(enchere.getIdutilisateur())==0){
                throw new Exception("Veuillez charger votre compte");
            }

            if(enchere.getPrix()>getMontantUser(enchere.getIdutilisateur())){
                throw new Exception("Solde insuffisant");
            }

            Object pdt=produitRepository.findById(enchere.getIdproduit());
            Produit produit=(Produit) pdt;

            //Verifier si enchere.prix>Produit.prixMin
            if(enchere.getPrix()<produit.getPrixmin()){
                throw new Exception("Votre enchere est inferieure au prix min du produit");
            }

            enchereRepository.save(enchere);

            if(findBloquage(produit.getIdproduit())!=null){
                Bloquage lastBloquage=findBloquage(produit.getIdproduit());
                bloquageRepository.delete(lastBloquage);
            }

            Bloquage nouvelleBloquage=new Bloquage();
            nouvelleBloquage.setIdproduit(produit.getIdproduit());
            nouvelleBloquage.setIdutilisateur(enchere.getIdutilisateur());
            nouvelleBloquage.setPrix(enchere.getPrix());
            bloquageRepository.save(nouvelleBloquage);

            return new Data(true);
        }catch(Exception e){
            return new Error(e);
        }
    }

    public int getMontantUser(int idUser){
        Object object=userRepository.findById(idUser);
        Utilisateur user=(Utilisateur) object;
        return user.getMontant();
    }

    private VEncherePrixMax getPrixMaxProduit(int produitid){
        Object o=prixMaxRepository.findById(produitid);
        VEncherePrixMax v=(VEncherePrixMax) o;
        return v;
    }

    public Object getAllEnchere(){
        try{
            List<Enchere> list=new ArrayList<Enchere>();
            enchereRepository.findAll().forEach(enchere -> list.add(enchere));
            return new Data(list);
        }catch(Exception e){
            return new Error(e);
        }
    }

    public Object findById(int idenchere){
        try{
            return new Data(enchereRepository.findById(idenchere).get());
        }catch(Exception e){
            return new Error(e);
        }
    }

    public Object findByIdProduit(int idproduit){
        try{
            List<Enchere> list=new ArrayList<Enchere>();
            enchereRepository.findAll().forEach(enchere -> list.add(enchere));
            List<Enchere> nlist=new ArrayList<Enchere>();
            for(int i=0;i<list.size();i++){
                if(list.get(i).getIdproduit()==idproduit){
                    nlist.add((Enchere) list.get(i));
                }
            }
            return new Data(nlist);
        }catch(Exception e){
            return new Error(e);
        }
    }

    private Bloquage findBloquage(int idproduit){
        Bloquage bl=null;
        List<Bloquage> list=new ArrayList<Bloquage>();
        bloquageRepository.findAll().forEach(bloquage -> list.add(bloquage));
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIdproduit()==idproduit){
                bl=(Bloquage) list.get(i);
                break;
            }
        }
        return bl;
    }

}
