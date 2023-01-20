package com.controller;

import com.models.Commission;
import com.service.CommissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/commission")
public class CommissionController {

    @Autowired
    CommissionService commissionService;

    @GetMapping
    private Object getAllCommission(){return commissionService.getAllCommission();}

    @GetMapping("/{idcommission}")
    private Object getAllCommission(@PathVariable("idcommission") int idcommission ){
        return commissionService.getCommission(idcommission);
    }

    @PutMapping
    private void updateCommission(@RequestBody Commission commission){
        commissionService.updateCommission(commission);
    }
}
