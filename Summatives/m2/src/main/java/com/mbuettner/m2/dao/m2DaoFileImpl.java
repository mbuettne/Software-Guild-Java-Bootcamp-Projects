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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author mbuet
 */
public class m2DaoFileImpl implements m2Dao {
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    UserIO io = new UserIOConsoleImpl();
    DVDLibraryView view = new DVDLibraryView(io);
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDvd(String title, DVD dvd) throws m2DaoException {
        loadLibrary();
        DVD newDvd = dvds.put(title, dvd);
        if (dvd.getNote().equals("")) {
            dvd.setNote(" ");
        }
        writeLibrary();
        return newDvd;
    }
    
    @Override
    public List<DVD> getAllDVDs() throws m2DaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }
    
    @Override
    public DVD getDVD(String title) throws m2DaoException {
        loadLibrary();
        return dvds.get(title);
    }
    
    @Override
    public DVD removeDVD(String title) throws m2DaoException {
        loadLibrary();
        DVD removedDvd;
        if (dvds.containsKey(title)) {
            removedDvd = dvds.remove(title);
            //view.displayRemoveSuccessBanner();
        } else {
            io.readString("Can't Find DVD. Please Check Spelling And Try Again. Hit Enter To Continue.");
            removedDvd = new DVD(" ", " ", " ", " ", " ");
        }
        writeLibrary();
        return removedDvd;
    }
    
    @Override
    public DVD editDVD(String title, int whatToEdit, String edit) throws m2DaoException {
        loadLibrary();
        DVD dvdToBeEdited;
        if (dvds.containsKey(title)) {
            dvdToBeEdited = dvds.get(title);
            switch (whatToEdit) {
                case 1:
                    dvdToBeEdited = dvds.remove(title);
                    dvds.put(edit, dvdToBeEdited);
                    dvdToBeEdited.setTitle(edit);
                    view.displayEditSuccessBanner();
                    break;
                case 2:
                    dvdToBeEdited.setReleaseDate(edit);
                    view.displayEditSuccessBanner();
                    break;
                case 3:
                    dvdToBeEdited.setRating(edit);
                    view.displayEditSuccessBanner();
                    break;
                case 4:
                    dvdToBeEdited.setDirectorName(edit);
                    view.displayEditSuccessBanner();
                    break;
                case 5:
                    dvdToBeEdited.setStudio(edit);
                    view.displayEditSuccessBanner();
                    break;
                case 6:
                    dvdToBeEdited.setNote(edit);
                    view.displayEditSuccessBanner();
                    break;
                case 7:
                    io.readString("Returning To Main Menu. Please Hit Enter To Continue.");
                default:
                    io.readString("Unrecognized Input. Please Hit Enter To Return To Main Menu.");
            }
        } else {
            io.readString("Can't Find DVD. Please Check Spelling And Try Again. Hit Enter To Continue.");
            dvdToBeEdited = new DVD("", "", "", "", "");
            
        }
        writeLibrary();
        return dvdToBeEdited;
    }
    
    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String title = dvdTokens[0];
        String releaseDate = dvdTokens[1];
        String rating = dvdTokens[2];
        String directorName = dvdTokens[3];
        String studio = dvdTokens[4];
        DVD dvdFromFile = new DVD(title, releaseDate, rating, directorName, studio);
        dvdFromFile.setNote(dvdTokens[5]);
        
        return dvdFromFile;
    }
    
    private void loadLibrary() throws m2DaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new m2DaoException("Could Not Load Library Data Into Memory.", e);
        }
        String currentLine;
        DVD currentDvd;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDvd = unmarshallDVD(currentLine);
            
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    private String marshallDVD(DVD dvd) {
        String dvdAsText = dvd.getTitle() + DELIMITER;
        dvdAsText += dvd.getReleaseDate() + DELIMITER;
        dvdAsText += dvd.getRating() + DELIMITER;
        dvdAsText += dvd.getDirectorName() + DELIMITER;
        dvdAsText += dvd.getStudio() + DELIMITER;
        dvdAsText += dvd.getNote();
        
        return dvdAsText;
    }
    
    private void writeLibrary() throws m2DaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new m2DaoException("Could not save student data.", e);
        }
        
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDvd : dvdList) {
            dvdAsText = marshallDVD(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }
}
