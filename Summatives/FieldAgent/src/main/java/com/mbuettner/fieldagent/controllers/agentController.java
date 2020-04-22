/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.fieldagent.controllers;

import com.mbuettner.fieldagent.entities.Agency;
import com.mbuettner.fieldagent.entities.Agent;
import com.mbuettner.fieldagent.entities.Assignment;
import com.mbuettner.fieldagent.entities.SecurityClearance;
import com.mbuettner.fieldagent.service.AddService;
import com.mbuettner.fieldagent.service.DeleteService;
import com.mbuettner.fieldagent.service.LookupService;
import com.mbuettner.fieldagent.service.UpdateService;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mbuet
 */
@Controller
public class agentController {

    @Autowired
    AddService add;

    @Autowired
    DeleteService delete;

    @Autowired
    LookupService lookup;

    @Autowired
    UpdateService update;

    Set<ConstraintViolation<Agent>> violations = new HashSet<>();

    @GetMapping("/agent")
    public String getAgent(@RequestParam("id") String identifier, Model model) {
        Agent agent = lookup.findAgentByIdentifier(identifier);
        List<Assignment> assignments = lookup.findAssignmentsByAgent(identifier);
        List<Agency> agencies = lookup.findAllAgencies();
        List<SecurityClearance> securityClearances = lookup.findAllSecurityClearances();
        model.addAttribute("agent", agent);
        model.addAttribute("assignments", assignments);
        model.addAttribute("agencies", agencies);
        model.addAttribute("securityClearances", securityClearances);
        model.addAttribute("errors", violations);

        return "agent";
    }

    @PostMapping("/editAgent")
    public String editAgent(HttpServletRequest request, Model model) {
        Agent agent = new Agent();
         String firstName = request.getParameter("firstName");
        agent.setFirstName(firstName);
        String middleName = request.getParameter("middleName");
        agent.setMiddleName(middleName);
        String lastName = request.getParameter("lastName");
        agent.setLastName(lastName);
        String birthString = request.getParameter("birthDate");
        LocalDate birthDate = LocalDate.now();
        if (birthString.equals("")) {
            birthDate = null;
            agent.setBirthDate(birthDate);
        } else {
            birthDate = LocalDate.parse(birthString);
            agent.setBirthDate(birthDate);
        }
        String heightStr = request.getParameter("height");
        int height = 10;
        if (heightStr.equals("")) {
            height = 0;
            agent.setHeight(height);
        } else {
            height = Integer.parseInt(heightStr);
            agent.setHeight(height);
        }
        String identifier = request.getParameter("identifier");
        agent.setIdentifier(identifier);
        int agencyId = Integer.parseInt(request.getParameter("agency"));
        Agency agency = lookup.findAgencyById(agencyId);
        agent.setAgency(agency);
        int security = Integer.parseInt(request.getParameter("securityClearance"));
        SecurityClearance securityClearance = lookup.findSecurityClearanceById(security);
        agent.setSecurityClearance(securityClearance);
        String actDate = request.getParameter("activationDate");
        LocalDate activationDate = LocalDate.now();
        if (actDate.equals("")) {
            activationDate = null;
            agent.setActivationDate(activationDate);
        } else {
            activationDate = LocalDate.parse(actDate);
            agent.setActivationDate(activationDate);
        }
        String activeStr = request.getParameter("isActive");
        if(activeStr.equals("true")){
            agent.setIsActive(true);
        }
        String pictureUrl = request.getParameter("pictureUrl");
        agent.setPictureUrl(pictureUrl);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(agent);

        if (violations.isEmpty()) {
            update.updateAgent(agent);
            return "redirect:/";
        }else {
            List<Agency> agencies = lookup.findAllAgencies();
            List<SecurityClearance> securityClearances = lookup.findAllSecurityClearances();
            model.addAttribute("agent", agent);
            model.addAttribute("agencies", agencies);
            model.addAttribute("securityClearances", securityClearances);
            model.addAttribute("errors", violations);
            return "agent";
        }

    }

}
