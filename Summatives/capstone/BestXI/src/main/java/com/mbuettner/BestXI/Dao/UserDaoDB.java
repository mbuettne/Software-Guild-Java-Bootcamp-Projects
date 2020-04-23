/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Dao.RoleDaoDB.RoleMapper;
import com.mbuettner.BestXI.Dao.TeamDaoDB.TeamMapper;
import com.mbuettner.BestXI.Entities.Role;
import com.mbuettner.BestXI.Entities.Team;
import com.mbuettner.BestXI.Entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mbuet
 */
@Repository
public class UserDaoDB implements userDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public User getUserById(int id) {
        try{
            final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE userId = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_ID, new UserMapper(), id);
            user.setRole(getRoleForUser(id));
            user.setTeam(getTeamForUser(id));
            return user;
        } catch (DataAccessException ex){
            return null;
        }
    }
    
    private Role getRoleForUser(int userId){
        final String SELECT_ROLE_FOR_USER = "SELECT * FROM role JOIN users ON role.roleId = users.roleID WHERE users.userID = ?";
        return jdbc.queryForObject(SELECT_ROLE_FOR_USER, new RoleMapper(), userId);
    }
    
    private Team getTeamForUser(int userId){
        final String SELECT_TEAM_FOR_USER = "SELECT * FROM team JOIN users ON team.teamId = users.teamId WHERE users.userId = ?";
        return jdbc.queryForObject(SELECT_TEAM_FOR_USER, new TeamMapper(), userId);
    }

    @Override
    public User getUserByUsername(String username) {
        try{
            final String SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
            User user = jdbc.queryForObject(SELECT_USER_BY_USERNAME, new UserMapper(), username);
            user.setRole(getRoleForUser(user.getUserId()));
            user.setTeam(getTeamForUser(user.getUserId()));
            return user;
        } catch (DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM users";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        associateRolesAndTeams(users);
        return users;
    }
    
    @Override
      public List<User> getAllUsersByTeam(int teamId){
         final String SELECT_ALL_USERS_BY_TEAM = "SELECT * FROM users WHERE teamId = ?";
        List<User> usersByTeam = jdbc.query(SELECT_ALL_USERS_BY_TEAM, new UserMapper(), teamId);
        associateRolesAndTeams(usersByTeam);
        return usersByTeam;
      }
      
      private void associateRolesAndTeams(List<User> users){
          for(User user : users){
              user.setRole(getRoleForUser(user.getUserId()));
              user.setTeam(getTeamForUser(user.getUserId()));
          }
      }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE users SET firstName = ?, lastName = ?, username = ?, password = ?, enabled = ?, roleId = ?, teamId = ? WHERE userId = ?";
        jdbc.update(UPDATE_USER, user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.isEnabled(), user.getRole().getRoleId(), user.getTeam().getTeamId(), user.getUserId());
    }

    @Override
    public void deleteUser(int id) {
        final String DELETE_USER = "DELETE FROM users WHERE userId = ?";
        jdbc.update(DELETE_USER, id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
       final String INSERT_USER = "INSERT INTO users(roleId, firstName, lastName, username, password, enabled, teamId) VALUES(?,?,?,?,?,?,?)";
       jdbc.update(INSERT_USER, user.getRole().getRoleId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.isEnabled(), user.getTeam().getTeamId());
       int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
       user.setUserId(newId);
       
       return user;
    }
    
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            return user;
        }
    }
    
}
