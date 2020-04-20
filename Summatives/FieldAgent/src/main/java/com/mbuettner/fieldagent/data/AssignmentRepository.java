package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.entities.Assignment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository
        extends JpaRepository<Assignment, Integer> {

    List<Assignment> findByAgentIdentifier(String indentifier);
}
