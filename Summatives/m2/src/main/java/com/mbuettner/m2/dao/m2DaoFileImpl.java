/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.dao;

import com.mbuettner.m2.dto.DVD;
import com.mbuettner.m2.ui.DVDLibraryView;
import com.mbuettner.m2.ui.UserIO;
import com.mbuettner.m2.ui.UserIOConsoleImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mbuet
 */
public class m2DaoFileImpl implements m2Dao {

    UserIO io = new UserIOConsoleImpl();
    DVDLibraryView view = new DVDLibraryView();

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDvd(String title, DVD dvd) {
        DVD newDvd = dvds.put(title, dvd);
        return newDvd;
    }

    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) {
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) {
        DVD removedDvd;
        if (dvds.containsKey(title)) {
            removedDvd = dvds.remove(title);
            view.displayRemoveSuccessBanner();
        } else {
            io.readString("Can't Find DVD. Please Check Spelling And Try Again. Hit Enter To Continue.");
            removedDvd = new DVD("", "", "", "", "");
        }
        return removedDvd;
    }

    @Override
    public DVD editDVD(String title, int whatToEdit, String edit) {
        DVD dvdToBeEdited = dvds.get(title);
        switch (whatToEdit) {
            case 1:
                dvdToBeEdited.setTitle(edit);
                break;
            case 2:
                dvdToBeEdited.setReleaseDate(edit);
                break;
            case 3:
                dvdToBeEdited.setRating(edit);
                break;
            case 4:
                dvdToBeEdited.setDirectorName(edit);
                break;
            case 5:
                dvdToBeEdited.setStudio(edit);
            case 6:
                dvdToBeEdited.setNote(edit);
            case 7:
                io.readString("Returning To Main Menu. Please Hit Enter To Continue.");
            default:
                io.readString("Unrecognized Input. Please Hit Enter To Return To Main Menu.");
        }
        return dvdToBeEdited;
    }

}
