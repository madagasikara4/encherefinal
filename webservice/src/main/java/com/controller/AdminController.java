package com.controller;


import com.models.Admin;
import com.models.Categorie;
import com.models.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    private Object login(@RequestBody Admin adm){
        try{
            return new Data(Admin.login(adm));

        }
        catch (Exception e){
            return new Error(e);
        }
    }
}
