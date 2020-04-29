/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Team;
import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Services.playerService;
import com.mbuettner.BestXICapstone.Services.playergameService;
import com.mbuettner.BestXICapstone.Services.roleService;
import com.mbuettner.BestXICapstone.Services.teamService;
import com.mbuettner.BestXICapstone.Services.teamgameService;
import com.mbuettner.BestXICapstone.Services.userService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class createController {

    @Autowired
    playerService playerService;

    @Autowired
    playergameService playergameService;

    @Autowired
    teamService teamService;

    @Autowired
    teamgameService teamgameService;

    @Autowired
    userService userService;

    @Autowired
    roleService roleService;

    @Autowired
    PasswordEncoder encoder;

    Set<ConstraintViolation<Team>> violations = new HashSet<>();

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @GetMapping("/createTeam")
    public String displayCreateTeamPage(Model model) {
        model.addAttribute("errors", violations);
        return "createTeam";
    }

    @PostMapping("/createTeam")
    public String createNewTeam(HttpServletRequest request, Model model) {
        Team team = new Team();
        team.setCoachname(request.getParameter("coachname"));
        team.setTeamname(request.getParameter("teamname"));
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(team);

        if (violations.isEmpty()) {
            teamService.saveOrUpdateTeam(team);
            return "redirect:/createAccount";
        } else {
            model.addAttribute("errors", violations);
            return "createTeam";
        }

    }
    
    @GetMapping("/createAccount")
    public String displayCreateAccountPage(Model model){
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "createAccount";
    }
    
    @PostMapping("/createAccount")
    public String createNewAccount(HttpServletRequest request, Model model){
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        int teamid = Integer.parseInt(request.getParameter("teamid"));
        String password = request.getParameter("password");
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setTeamid(teamid);
        user.setEnabled(true);
        user.setPassword(encoder.encode(password));
        
        List<User> users = userService.getAllUsersByTeam(teamid);
        if(users.size()==0){
            user.setRoleid(1);
        } else {
            user.setRoleid(2);
        }
        userService.saveOrUpdateUser(user);
        return "redirect:/login";
    }
}
