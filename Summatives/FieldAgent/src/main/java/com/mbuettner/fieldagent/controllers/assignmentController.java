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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mbuet
 */
@Controller
public class assignmentController {

    @Autowired
    AddService add;

    @Autowired
    DeleteService delete;

    @Autowired
    LookupService lookup;

    @Autowired
    UpdateService update;

    Set<ConstraintViolation<Assignment>> violations = new HashSet<>();

    @GetMapping("/assignment")
    public String assignment(String id, Model model) {
        Agent agent = lookup.findAgentByIdentifier(id);
        List<Agent> agentList = lookup.findAllAgents();
        List<Country> countryList = lookup.findAllCountries();
        model.addAttribute("agents", agentList);
        model.addAttribute("thisAgent", agent);
        model.addAttribute("countries", countryList);
        model.addAttribute("errors", violations);
        return "assignment";
    }

    @PostMapping("/addAssignment")
    public String addAssignment(Assignment assignment, Model model) {
        if (assignment.getStartDate().isAfter(assignment.getProjectedEndDate())) {
            assignment.setStartDate(null);
        }
        if (assignment.getActualEndDate() != null) {
            if (assignment.getStartDate().isAfter(assignment.getActualEndDate())) {
                assignment.setStartDate(null);
            }
        }
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(assignment);
        if (violations.isEmpty()) {
            add.addAssignment(assignment);
            return "redirect:/";
        } else {
            List<Agent> agentList = lookup.findAllAgents();
            List<Country> countryList = lookup.findAllCountries();
            model.addAttribute("agents", agentList);
            model.addAttribute("countries", countryList);
            model.addAttribute("errors", violations);
            return "assignment";
        }
    }

    @GetMapping("/editAssignment")
    public String editAssignment(@RequestParam("id") int id, Model model) {
        Assignment assignment = lookup.findAssignmentById(id);
        Agent agent = assignment.getAgent();
        List<Agent> agentList = lookup.findAllAgents();
        List<Country> countryList = lookup.findAllCountries();
        model.addAttribute("assignment", assignment);
        model.addAttribute("agents", agentList);
        model.addAttribute("thisAgent", agent);
        model.addAttribute("countries", countryList);
        model.addAttribute("errors", violations);
        return "editAssignment";
    }

    @PostMapping("/editAssignment")
    public String editAssignment(HttpServletRequest request, @RequestParam("id") int id, Model model) {
        Assignment assignment = lookup.findAssignmentById(id);

        String identifier = request.getParameter("agent");
        Agent agent = lookup.findAgentByIdentifier(identifier);
        assignment.setAgent(agent);

        String countryCode = request.getParameter("country");
        Country country = lookup.findCountryByCode(countryCode);
        assignment.setCountry(country);

        String startStr = request.getParameter("startDate");
        LocalDate startDate = LocalDate.now();
        if (startStr.equals("")) {
            startDate = null;
        } else {
            startDate = LocalDate.parse(startStr);
        }
        assignment.setStartDate(startDate);

        String projEndStr = request.getParameter("projectedEndDate");
        LocalDate projectedEndDate = LocalDate.now();
        if (projEndStr.equals("")) {
            projectedEndDate = null;
        } else {
            projectedEndDate = LocalDate.parse(projEndStr);
        }
        assignment.setProjectedEndDate(projectedEndDate);

        String actualEndStr = request.getParameter("actualEndDate");

        if (actualEndStr.equals("")) {
            assignment.setActualEndDate(actualEndStr);
        } else {
            LocalDate actualEndDate = LocalDate.parse(request.getParameter("actualEndDate"));
            assignment.setActualEndDate(actualEndDate);
        }

        String notes = request.getParameter("notes");
        assignment.setNotes(notes);
        
        if (assignment.getStartDate().isAfter(assignment.getProjectedEndDate())) {
            assignment.setStartDate(null);
        }

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(assignment);

        if (violations.isEmpty()) {
            update.updateAssignment(assignment);
            return "redirect:/";
        }
        List<Agent> agentList = lookup.findAllAgents();
        List<Country> countryList = lookup.findAllCountries();
        model.addAttribute("agents", agentList);
        model.addAttribute("countries", countryList);
        model.addAttribute("assignment", assignment);
        model.addAttribute("errors", violations);
        return "editAssignment";

    }
}
