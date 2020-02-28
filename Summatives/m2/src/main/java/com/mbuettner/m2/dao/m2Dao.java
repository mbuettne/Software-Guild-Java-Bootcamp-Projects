/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.dao;

import com.mbuettner.m2.dto.DVD;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface m2Dao {
    DVD addDvd(String title, DVD dvd);
    
    List<DVD> getAllDVDs();
    
    DVD getDVD(String title);
    
    DVD removeDVD(String title);
    
    DVD editDVD(String title, int whatToEdit, String edit);
}
