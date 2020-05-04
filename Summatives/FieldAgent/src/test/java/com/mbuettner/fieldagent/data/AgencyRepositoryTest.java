package com.mbuettner.fieldagent.data;

import com.mbuettner.fieldagent.data.AgencyRepository;
import com.mbuettner.fieldagent.entities.Agency;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AgencyRepositoryTest {

    @Autowired
    private AgencyRepository repo;

    public AgencyRepositoryTest() {
    }

    @Test
    public void testFindAll() {
        List<Agency> agencies = repo.findAll();
        assertEquals(9, agencies.size());
    }

}
