/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXICapstone.Controllers;

import com.mbuettner.BestXICapstone.Entities.Player;
import com.mbuettner.BestXICapstone.Entities.Playergame;
import com.mbuettner.BestXICapstone.Entities.Role;
import com.mbuettner.BestXICapstone.Entities.Team;
import com.mbuettner.BestXICapstone.Entities.Teamgame;
import com.mbuettner.BestXICapstone.Entities.User;
import com.mbuettner.BestXICapstone.Services.playerService;
import com.mbuettner.BestXICapstone.Services.playergameService;
import com.mbuettner.BestXICapstone.Services.roleService;
import com.mbuettner.BestXICapstone.Services.teamService;
import com.mbuettner.BestXICapstone.Services.teamgameService;
import com.mbuettner.BestXICapstone.Services.userService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.Validator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mbuet
 */
@Controller
public class MainController {

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
    Set<ConstraintViolation<Player>> playerviolations = new HashSet<>();
    Set<ConstraintViolation<Playergame>> playergameviolations = new HashSet<>();
    Set<ConstraintViolation<Teamgame>> teamgameviolations = new HashSet<>();

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/")
    public String dashboardPage(String username, Model model) {
        User user = userService.getUserByUsername(username);
        List<Player> players = playerService.getAllPlayersByTeam(user.getTeamid());
        Teamgame latestGame = teamgameService.getLatestGameByTeam(user.getTeamid());
        List<String> topScorers = playergameService.topScorerStringList(user.getTeamid());
        model.addAttribute("players", players);
        model.addAttribute("latestGame", latestGame);
        model.addAttribute("topScorers", topScorers);
        return "index";
    }

    @GetMapping("/account")
    public String accountPage(String username, Model model) {
        User user = userService.getUserByUsername(username);
        Team team = teamService.getTeamById(user.getTeamid());
        model.addAttribute("currentUser", user);
        model.addAttribute("users", userService.getAllUsersByTeam(team.getTeamid()));

        return "account";
    }
    
    @PostMapping("/addUser")
    public String addUser(String firstname, String lastname, String username, String password, int teamid, String adminusername){
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);
        user.setRoleid(2);
        user.setTeamid(teamid);
        
        userService.saveOrUpdateUser(user);
        return "redirect:/account?username=" + adminusername;
    }
    
    @GetMapping("/deleteSelf")
    public String deleteSelf(Integer id){
        
        return "deleteSelf";
    }
    
    @PostMapping("/deleteSelf")
    public String deleteSelf(String username){
        User user = userService.getUserByUsername(username);
        userService.deleteUser(user.getUserid());
        
        return "redirect:/login";
    }
    
    @PostMapping("/deleteUser")
    public String deleteUser(Integer userid, String adminusername){
        userService.deleteUser(userid);
        
        return "redirect:/account?username=" + adminusername;
    }
    
    @GetMapping("/editUser")
    public String editUser(Integer id, Model model, Integer error){
        User user = userService.getUserById(id);
        List<Role> roleList = roleService.returnAllRoles();
        model.addAttribute("roles", roleList);
        model.addAttribute("user", user);
        
        if(error !=null){
            if(error == 1){
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }
        return "editUser";
    }
    
    @PostMapping("/editUser")
    public String updateUser(Integer roleId, Integer userid, String firstname, String lastname, String username){
        User user = userService.getUserById(userid);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        if(user.getRoleid()==1){
            user.setRoleid(roleId);
        }
        
        userService.saveOrUpdateUser(user);
        
        return "redirect:/account?username=" + username;
    }
    
    @PostMapping("editPassword")
    public String editPassword(Integer userid, String password, String confirmPassword, String username){
        User user = userService.getUserById(userid);
        
        if(password.equals(confirmPassword)){
            user.setPassword(encoder.encode(password));
            userService.saveOrUpdateUser(user);
            return "redirect:/account?username=" + username;
        } else {
            return "redirect:/editUser?id=" + userid + "&error=1";
        }
    }
}
