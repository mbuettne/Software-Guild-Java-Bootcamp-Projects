package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository
        extends JpaRepository<Country, String> {

}
