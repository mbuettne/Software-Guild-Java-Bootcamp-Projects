/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.BestXI.Dao;

import com.mbuettner.BestXI.Entities.Team;
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
public class TeamDaoDB implements teamDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Team getTeamById(int id) {
        try{
            final String SELECT_TEAM_BY_ID = "SELECT * FROM team WHERE teamId = ?";
            return jdbc.queryForObject(SELECT_TEAM_BY_ID, new TeamMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Team getTeamByTeamName(String teamName) {
        try {
            final String SELECT_TEAM_BY_TEAMNAME = "SELECT * FROM team WHERE teamName = ?";
            return jdbc.queryForObject(SELECT_TEAM_BY_TEAMNAME, new TeamMapper(), teamName);
        } catch (DataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Team> getAllTeams() {
        final String SELECT_ALL_TEAMS = "SELECT * FROM team";
        return jdbc.query(SELECT_ALL_TEAMS, new TeamMapper());
    }

    @Override
    public void updateTeam(Team team) {
        final String UPDATE_TEAM = "UPDATE team SET teamName = ?, coachName = ?, logoUrl = ? WHERE teamId = ?";
        jdbc.update(UPDATE_TEAM, team.getTeamName(), team.getCoachName(), team.getLogoUrl(), team.getTeamId());
        
    }

    @Override
    public void deleteTeam(int id) {
        final String DELETE_TEAM = "DELETE FROM team WHERE teamId = ?";
        jdbc.update(DELETE_TEAM, id);
    }

    @Override
    @Transactional
    public Team createTeam(Team team) {
        final String INSERT_TEAM = "INSERT INTO team(teamName, coachName, logoUrl) VALUES(?, ?, ?)";
        jdbc.update(INSERT_TEAM, team.getTeamName(), team.getCoachName(), team.getLogoUrl());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        team.setTeamId(newId);
        
        return team;
    }
    
    
     public static final class TeamMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet rs, int i) throws SQLException {
            Team team = new Team();
            team.setTeamId(rs.getInt("teamId"));
            team.setTeamName(rs.getString("teamName"));
            team.setCoachName(rs.getString("coachName"));
            team.setLogoUrl(rs.getString("logoUrl"));
            return team;
        }
    }
    
}
