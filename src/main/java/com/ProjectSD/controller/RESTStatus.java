package com.ProjectSD.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTStatus {
    
    @GetMapping(path="/Status")
    public String Status(){
        
        return "Servidor online";
    }
    @GetMapping(value="/check")
    public String check(){
        return "Checando status, funcionando ainda!!";
    }
}
