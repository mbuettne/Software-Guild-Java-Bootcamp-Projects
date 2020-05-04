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
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class AddService {

    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;
    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;

    public AddService(AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo, AgentRepository agentRepo, AssignmentRepository assignmentRepo) {
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
    }
    
    @Transactional
    public Agent addAgent(Agent agent){
        return agentRepo.save(agent);
    }
    
    @Transactional
    public Assignment addAssignment(Assignment assignment){
        return assignmentRepo.save(assignment);
    }
    
}
