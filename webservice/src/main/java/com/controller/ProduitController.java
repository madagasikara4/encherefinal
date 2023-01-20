package com.controller;

import com.models.Produit;
import com.service.ProduitService;
import com.service.VProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/produits")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @Autowired
    VProduitService vProduitService;

    @GetMapping()
    private Object getAllVProduit() {
        return vProduitService.getAllVProduit();
    }

    @GetMapping("/{produitid}")
    private Object getVProduit(@PathVariable("produitid") int produitid)
    {
        return vProduitService.getVProduitById(produitid);
    }

    @GetMapping("/bycategorie/{categorieid}")
    private Object getValideProduitByCategorie(@PathVariable("categorieid") int categorieid)
    {
        return vProduitService.getValideByCategorie(categorieid);
    }

    @PostMapping()
    private int saveProduit(@RequestBody Produit produit){
        produitService.saveOrUpdate(produit);
        return produit.getIdproduit();
    }

    @DeleteMapping("/{produitid}")
    private void deleteProduit(@PathVariable("produitid") int produitid)
    {
        produitService.delete(produitid);
    }

    @GetMapping("/find/{produitid}")
    private Object findById(@PathVariable("produitid") int produitid)
    {
        return produitService.getProduitById(produitid);
    }

    @PutMapping()
    private void update(@RequestBody Produit produit)
    {
        produitService.saveOrUpdate(produit);
    }

    @GetMapping("/terminer")
    private void termineProduit(){
        produitService.terminerEnchere();
    }


}
