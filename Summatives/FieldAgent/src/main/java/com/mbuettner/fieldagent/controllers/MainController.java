/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.fieldagent.controllers;

import com.mbuettner.fieldagent.entities.Agent;
import com.mbuettner.fieldagent.entities.Assignment;
import com.mbuettner.fieldagent.service.AddService;
import com.mbuettner.fieldagent.service.DeleteService;
import com.mbuettner.fieldagent.service.LookupService;
import com.mbuettner.fieldagent.service.UpdateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class MainController {
    
    @Autowired
    AddService add;
    
    @Autowired
    DeleteService delete;
    
    @Autowired
    LookupService lookup;
    
    @Autowired
    UpdateService update;
    
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("agents", lookup.findAllAgents());
        return "index";
    }

}
