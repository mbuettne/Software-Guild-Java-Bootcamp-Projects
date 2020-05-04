package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.data.AgentRepository;
import com.mbuettner.fieldagent.entities.Agency;
import com.mbuettner.fieldagent.entities.Agent;
import com.mbuettner.fieldagent.entities.SecurityClearance;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AgentRepositoryTest {

    private final static String TEST_IDENTIFIER = "TEST";

    @Autowired
    private AgentRepository repo;

    public AgentRepositoryTest() {
    }

    @Test
    public void testFindAll() {
        assertTrue(repo.findAll().size() > 0);
    }

    @Test
    public void testFindById() {

        Agent agent = repo.findById("SR-475-4PRAL")
                .orElse(null);

        assertNotNull(agent);
        System.out.println(agent);
    }

    @Test
    public void testCreate() {

        Agent agent = makeTestAgent();

        agent = repo.save(agent);

        Agent actual = repo.findById(TEST_IDENTIFIER)
                .orElse(null);

        assertNotNull(actual);

        repo.deleteById(TEST_IDENTIFIER);
    }

    @Test
    public void testUpdate() {

        Agent agent = makeTestAgent();
        agent = repo.save(agent);

        Agent toUpdate = makeTestAgent();
        toUpdate.setHeight(62);

        toUpdate = repo.save(toUpdate);

        Agent actual = repo.findById(TEST_IDENTIFIER).orElse(null);
        assertNotNull(actual);
        assertEquals(62, actual.getHeight());

        repo.deleteById(TEST_IDENTIFIER);
    }

    private Agent makeTestAgent() {
        Agency agency = new Agency();
        agency.setAgencyId(1);
        agency.setName("CIA");

        SecurityClearance sc = new SecurityClearance();
        sc.setSecurityClearanceId(2);
        sc.setName("Secret");

        Agent agent = new Agent();
        agent.setIdentifier(TEST_IDENTIFIER);
        agent.setFirstName("TestFirst");
        agent.setLastName("TestLast");
        agent.setBirthDate(LocalDate.parse("1990-04-04"));
        agent.setHeight(74);
        agent.setActivationDate(LocalDate.parse("2016-02-14"));
        agent.setAgency(agency);
        agent.setSecurityClearance(sc);

        return agent;
    }

}
