/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.dao;

import com.mbuettner.m2.dto.DVD;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mbuet
 */
public class m2DaoTest {

    private m2Dao dao = new m2DaoFileImpl();

    public m2DaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        List<DVD> dvdList = dao.getAllDVDs();
        for (DVD dvd : dvdList) {
            dao.removeDVD(dvd.getTitle());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class m2Dao.
     */
    @Test
    public void testAddGetDvd() throws Exception {
        DVD dvd1 = new DVD("Title1", "ReleaseDate1", "Rating1", "Director1", "Studio1");
        dvd1.setNote("Note1");

        dao.addDvd(dvd1.getTitle(), dvd1);

        DVD fromDao = dao.getDVD(dvd1.getTitle());

        assertEquals(dvd1, fromDao);
    }

    /**
     * Test of getAllDVDs method, of class m2Dao.
     */
    @Test
    public void testGetAllDVDs() throws Exception {
        DVD dvd1 = new DVD("Title1", "ReleaseDate1", "Rating1", "Director1", "Studio1");
        dvd1.setNote("Note1");
        dao.addDvd(dvd1.getTitle(), dvd1);

        DVD dvd2 = new DVD("Title2", "ReleaseDate2", "Rating2", "Director2", "Studio2");
        dvd2.setNote("Note2");
        dao.addDvd(dvd2.getTitle(), dvd2);

        assertEquals(2, dao.getAllDVDs().size());
    }

    /**
     * Test of removeDVD method, of class m2Dao.
     */
    @Test
    public void testRemoveDVD() throws Exception {
        DVD dvd1 = new DVD("Title1", "ReleaseDate1", "Rating1", "Director1", "Studio1");
        dvd1.setNote("Note1");
        dao.addDvd(dvd1.getTitle(), dvd1);

        DVD dvd2 = new DVD("Title2", "ReleaseDate2", "Rating2", "Director2", "Studio2");
        dvd2.setNote("Note2");
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        dao.removeDVD(dvd1.getTitle());
        assertEquals(1, dao.getAllDVDs().size());
        assertNull(dao.getDVD(dvd1.getTitle()));
        
        dao.removeDVD(dvd2.getTitle());
        assertEquals(0, dao.getAllDVDs().size());
        assertNull(dao.getDVD(dvd2.getTitle()));
    }

    /**
     * Test of editDVD method, of class m2Dao.
     */
    @Test
    public void testEditDVD() throws Exception {
    }
}
