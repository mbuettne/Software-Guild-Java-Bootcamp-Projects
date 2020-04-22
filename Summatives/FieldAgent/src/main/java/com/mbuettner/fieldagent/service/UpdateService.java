/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.fieldagent.service;

import com.mbuettner.fieldagent.data.AgencyRepository;
import com.mbuettner.fieldagent.data.AgentRepository;
import com.mbuettner.fieldagent.data.AssignmentRepository;
import com.mbuettner.fieldagent.data.CountryRepository;
import com.mbuettner.fieldagent.data.SecurityClearanceRepository;
import com.mbuettner.fieldagent.entities.Agent;
import com.mbuettner.fieldagent.entities.Assignment;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class UpdateService {

    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;
    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;

    public UpdateService(AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo, AgentRepository agentRepo, AssignmentRepository assignmentRepo) {
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
    }
    
    public Agent updateAgent(Agent agent){
        return agentRepo.save(agent);
    }
    
    public Assignment updateAssignment(Assignment assignment){
        return assignmentRepo.save(assignment);
    }
}
