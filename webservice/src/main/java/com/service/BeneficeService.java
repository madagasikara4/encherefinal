package com.service;

import com.models.Categorie;
import com.models.Data;
import com.models.Benefice;
import com.models.Utilisateur;
import com.repository.BeneficeRepository;
import com.repository.VProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeneficeService {
    @Autowired
    BeneficeRepository beneficeRepository;

    public Object getAllBenefice()
    {
        try{
            List<Benefice> benefice = new ArrayList<Benefice>();
            beneficeRepository.findAll().forEach(benefice1 -> benefice.add(benefice1));
            return new Data(benefice);
        }catch (Exception e){
            return new Error(e);
        }
    }

    public Object getBeneficeById(int id) {
        try {
            return new Data(beneficeRepository.findById(id).get());
        }
        catch (Exception e){
            return new Error(e);
        }
    }

    public void saveOrUpdate(Benefice benefice)
    {
        beneficeRepository.save(benefice);
    }

    public void delete(int id)
    {
        beneficeRepository.deleteById(id);
    }

    public void updateBenefice(Benefice benefice)
    {
        beneficeRepository.save(benefice);
    }

}
