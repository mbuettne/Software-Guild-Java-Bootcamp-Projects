/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.controller;

import com.mbuettner.m2.dao.m2Dao;
import com.mbuettner.m2.dao.m2DaoException;
import com.mbuettner.m2.dto.DVD;
import com.mbuettner.m2.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class DVDLibraryController {

    DVDLibraryView view;
    m2Dao dao;

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                    menuSelection = getMenuSelection();
               
                switch (menuSelection) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        dvdSearch();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        displayUnknownCommand();
                }
            }
        } catch (m2DaoException e) {
            view.displayError(e.getMessage());
        }

        displayExit();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws m2DaoException {
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDVDs() throws m2DaoException {
        view.displayShowAllDVDBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void dvdSearch() throws m2DaoException {
        view.displayShowDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }

    private void removeDVD() throws m2DaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        dao.removeDVD(dvdTitle);
    }

    private void editDVD() throws m2DaoException {
        int editToMake = view.printEditMenu();
        if (editToMake > 0 && editToMake < 7) {
            String title = view.getDVDChoice();
            String edit = view.getEdit();

            dao.editDVD(title, editToMake, edit);
        } else if (editToMake == 7) {
            view.returnToMenu();
        }

    }

    private void displayExit() {
        view.displayExitBanner();
    }

    private void displayUnknownCommand() {
        view.displayUnknownCommand();
    }

    public DVDLibraryController(m2Dao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
}
