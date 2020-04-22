package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.entities.SecurityClearance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityClearanceRepository
        extends JpaRepository<SecurityClearance, Integer> {

}
