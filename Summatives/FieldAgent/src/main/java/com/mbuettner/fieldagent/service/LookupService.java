package com.mbuettner.fieldagent.service;

import com.mbuettner.fieldagent.data.AgencyRepository;
import com.mbuettner.fieldagent.data.AgentRepository;
import com.mbuettner.fieldagent.data.AssignmentRepository;
import com.mbuettner.fieldagent.data.CountryRepository;
import com.mbuettner.fieldagent.data.SecurityClearanceRepository;
import com.mbuettner.fieldagent.entities.Agency;
import com.mbuettner.fieldagent.entities.Agent;
import com.mbuettner.fieldagent.entities.Assignment;
import com.mbuettner.fieldagent.entities.Country;
import com.mbuettner.fieldagent.entities.SecurityClearance;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LookupService {

    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;
    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;

    public LookupService(AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo, AgentRepository agentRepo, AssignmentRepository assignmentRepo) {
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
    }

    public List<Agency> findAllAgencies() {
        return agencyRepo.findAll();
    }

    public Agency findAgencyById(int agencyId) {
        return agencyRepo.findById(agencyId)
                .orElse(null);
    }

    public List<Country> findAllCountries() {
        return countryRepo.findAll();
    }

    public Country findCountryByCode(String countryCode) {
        return countryRepo.findById(countryCode)
                .orElse(null);
    }

    public List<SecurityClearance> findAllSecurityClearances() {
        return securityRepo.findAll();
    }

    public SecurityClearance findSecurityClearanceById(int securityClearanceId) {
        return securityRepo.findById(securityClearanceId)
                .orElse(null);
    }

    public List<Agent> findAllAgents() {
        return agentRepo.findAll();
    }

    public Agent findAgentByIdentifier(String agentId) {
        return agentRepo.findById(agentId)
                .orElse(null);
    }

    public List<Assignment> findAllAssignments() {
        return assignmentRepo.findAll();
    }

    public Assignment findAssignmentById(int assignmentId) {
        return assignmentRepo.findById(assignmentId)
                .orElse(null);
    }

    public List<Assignment> findAssignmentsByAgent(String agentIdentifier) {
        List<Assignment> agentAssignments = new ArrayList<>();
        Agent agent = findAgentByIdentifier(agentIdentifier);
        List<Assignment> assignments = findAllAssignments();
        for (Assignment assignment : assignments) {
            if (assignment.getAgent() == agent) {
                agentAssignments.add(assignment);
            }
        }
        return agentAssignments;
    }
}
