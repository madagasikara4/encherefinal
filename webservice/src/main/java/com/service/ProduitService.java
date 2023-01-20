package com.service;

import com.models.*;
import com.models.Error;
import com.repository.BeneficeRepository;
import com.repository.CommissionRepository;
import com.repository.ProduitRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    BloquageService bloquageService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommissionRepository commissionRepository;

    @Autowired
    BeneficeRepository beneficeRepository;

    public Object getAllProduit()
    {
        try{
            List<Produit> produit = new ArrayList<Produit>();
            produitRepository.findAll().forEach(produit1 -> produit.add(produit1));
            return new Data(produit);
        }catch (Exception e){
            return new Error(e);
        }
    }

    public Object getProduitById(int id) {
        try {
            return new Data(produitRepository.findById(id));
        }
        catch (Exception e){
            return new Error(e);
        }
    }

    public Object getValideByCategorie(int idcategorie){
        try{
            List<Produit> produit = new ArrayList<Produit>();
            produitRepository.findAll().forEach(produit1 -> produit.add(produit1));
            List<Produit> actuProduit = new ArrayList<Produit>();
            Date date=new Date();
            for(int i=0;i<produit.size();i++){
                Produit pdt=(Produit) produit.get(i);
                if((pdt.getIdcategorie()==idcategorie) && (pdt.getStatut()==0)){
                    actuProduit.add(pdt);
                }
            }
            return new Data(actuProduit);
        }catch(Exception e){
            return new Error(e);
        }
    }

    public void saveOrUpdate(Produit produit)
    {
        produitRepository.save(produit);
    }

    public void delete(int id)
    {
        produitRepository.deleteById(id);
    }

    public void update(Produit produit, int produitid)
    {
        produitRepository.save(produit);
    }

    static boolean isTerminer(Produit p){
        Calendar cal=Calendar.getInstance();
        cal.setTime(p.getDebut());
        Double d=new Double(p.getDuree());
        cal.add(Calendar.HOUR,d.intValue());
        Date termine= cal.getTime();
        cal=Calendar.getInstance();
        if(cal.getTime().before(termine)==true)
            return false;
        return true;
    }

    public void terminerEnchere(){
        try {
            List<Produit> produit = new ArrayList<Produit>();
            produitRepository.findAll().forEach(produit1 -> produit.add(produit1));
            for (int i = 0; i < produit.size(); i++) {
                if (ProduitService.isTerminer(produit.get(i)) == true) {
                    produit.get(i).setStatut(1);
                    this.update(produit.get(i), produit.get(i).getIdproduit());
                    Bloquage bloq = bloquageService.getBloquageByIdProduit(produit.get(i).getIdproduit());
                    Float prix=new Float(bloq.getPrix());
                    if(prix<produit.get(i).getPrixenchere()) {
                        bloquageService.delete(bloq);
                        continue;
                    }
                    Utilisateur user=userRepository.findById(bloq.getIdutilisateur()).get();
                    user.setMontant(user.getMontant()-prix.intValue());
                    userRepository.save(user);
                    Utilisateur u=userRepository.findById(produit.get(i).getIdUtilisateur()).get();
                    Commission com=commissionRepository.findById(1).get();
                    float b=prix*com.getCommission()/100;
                    prix=prix-(prix*com.getCommission()/100);
                    u.setMontant(u.getMontant()+prix.intValue());
                    userRepository.save(u);

                    Benefice benef= new Benefice();
                    benef.setIdproduit(produit.get(i).getIdproduit());
                    benef.setValeur(b);
                    beneficeRepository.save(benef);

                    bloquageService.delete(bloq);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
