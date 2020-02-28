/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.controller;

import com.mbuettner.m2.dao.m2Dao;
import com.mbuettner.m2.dao.m2DaoFileImpl;
import com.mbuettner.m2.dto.DVD;
import com.mbuettner.m2.ui.DVDLibraryView;
import com.mbuettner.m2.ui.UserIO;
import com.mbuettner.m2.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class DVDLibraryController {
    private UserIO io = new UserIOConsoleImpl();
    DVDLibraryView view = new DVDLibraryView();
    m2Dao dao = new m2DaoFileImpl();
    
    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        while(keepGoing){
            menuSelection = getMenuSelection();
            
            switch(menuSelection){
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
                    io.print("UNKNOWN COMMAND");
            }
            
        }
        io.print("GOODBYE");
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createDVD(){
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    private void listDVDs(){
        view.displayShowAllDVDBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void dvdSearch(){
        view.displayShowDVDBanner();
        String dvdTitle = view.getDVDChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }
    
    private void removeDVD(){
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDChoice();
        dao.removeDVD(dvdTitle);
    }
    
    private void editDVD(){
        int editToMake = view.printEditMenu();
        String title = view.getDVDChoice();
        String edit = view.getEdit();
        
        dao.editDVD(title, editToMake, edit);
    }
        
}
