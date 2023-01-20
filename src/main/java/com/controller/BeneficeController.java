package com.controller;

import com.models.Benefice;
import com.models.Categorie;
import com.models.Produit;
import com.service.BeneficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/benefices")
public class BeneficeController {
    @Autowired
    BeneficeService beneficeService;

    @GetMapping
    private Object getAllBenefice(){
        return beneficeService.getAllBenefice();
    }

    @GetMapping("/{beneficeid}")
    private Object getBenefice(@PathVariable("beneficeid") int beneficeid)
    {
        return beneficeService.getBeneficeById(beneficeid);
    }

    @PostMapping()
    private int saveBenefice(@RequestBody Benefice benefice){
        beneficeService.saveOrUpdate(benefice);
        return benefice.getIdBenefice();
    }

    @PutMapping()
    private void updateBenefice(@RequestBody Benefice benefice){
        beneficeService.updateBenefice(benefice);
    }

    @DeleteMapping("/{beneficeid}")
    private void deleteBenefice(@PathVariable("beneficeid") int beneficeid)
    {
        beneficeService.delete(beneficeid);
    }
}

