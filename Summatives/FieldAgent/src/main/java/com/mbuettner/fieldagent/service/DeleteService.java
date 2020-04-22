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
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author mbuet
 */
@Service
public class DeleteService {

    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;
    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;
    private final LookupService lookup;

    public DeleteService(LookupService lookup, AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo, AgentRepository agentRepo, AssignmentRepository assignmentRepo) {
        this.lookup = lookup;
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
    }
    
    public void deleteAgent(String agentIdentifier){
        List<Assignment> assignments = lookup.findAssignmentsByAgent(agentIdentifier);
        if(assignments.size() > 0){
            for(Assignment assignment : assignments){
                assignmentRepo.deleteById(assignment.getAssignmentId());
            }
        }
      agentRepo.deleteById(agentIdentifier);
    }

    public void deleteAssignment(Assignment assignment) {
        assignmentRepo.delete(assignment);
    }
}
