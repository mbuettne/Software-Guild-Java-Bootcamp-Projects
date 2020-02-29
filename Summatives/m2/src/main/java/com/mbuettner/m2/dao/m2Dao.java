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

    DVD addDvd(String title, DVD dvd) throws m2DaoException;

    List<DVD> getAllDVDs() throws m2DaoException;

    DVD getDVD(String title) throws m2DaoException;

    DVD removeDVD(String title) throws m2DaoException;

    DVD editDVD(String title, int whatToEdit, String edit) throws m2DaoException;
}
