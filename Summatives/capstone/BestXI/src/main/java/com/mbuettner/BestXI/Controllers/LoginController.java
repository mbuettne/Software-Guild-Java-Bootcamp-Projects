/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
}