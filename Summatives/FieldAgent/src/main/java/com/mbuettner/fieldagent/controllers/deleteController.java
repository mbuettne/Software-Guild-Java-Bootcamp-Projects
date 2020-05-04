/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.fieldagent.controllers;

import com.mbuettner.fieldagent.entities.Agent;
import com.mbuettner.fieldagent.entities.Assignment;
import com.mbuettner.fieldagent.entities.Country;
import com.mbuettner.fieldagent.service.AddService;
import com.mbuettner.fieldagent.service.DeleteService;
import com.mbuettner.fieldagent.service.LookupService;
import com.mbuettner.fieldagent.service.UpdateService;
import static java.lang.System.console;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mbuet
 */
@Controller
public class deleteController {

    @Autowired
    AddService add;

    @Autowired
    DeleteService delete;

    @Autowired
    LookupService lookup;

    @Autowired
    UpdateService update;

    @GetMapping("/deleteAgent")
    public String deleteAgent(String id, Model model) {
        Agent agent = lookup.findAgentByIdentifier(id);
        List<Assignment> assignmentList = lookup.findAssignmentsByAgent(id);
        model.addAttribute("assignments", assignmentList);
        model.addAttribute("thisAgent", agent);
        return "deleteAgent";
    }

    @PostMapping("/deleteAgent")
    public String confirmDelete(@RequestParam("id") String identifier) {

        delete.deleteAgent(identifier);
        return "redirect:/";
    }
    
    @GetMapping("/deleteAssignment")
    public String deleteAssignment(@RequestParam("id") int assignmentId){
        Assignment assignment = lookup.findAssignmentById(assignmentId);
        delete.deleteAssignment(assignment);
        return "redirect:/";
    }
}
