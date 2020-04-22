package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository
        extends JpaRepository<Agency, Integer> {

}
