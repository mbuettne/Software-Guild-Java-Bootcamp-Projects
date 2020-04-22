package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository
        extends JpaRepository<Agent, String> {

}
