/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2.ui;

import com.mbuettner.m2.dao.m2DaoException;
import java.util.InputMismatchException;
import com.mbuettner.m2.dto.DVD;
import java.util.List;

/**
 *
 * @author mbuet
 */
public class DVDLibraryView {

    private UserIO io;

    public int printMenuAndGetSelection() {
        int choice = 0;

        io.print("---Main Menu---");
        io.print("1. Add DVD To Collection");
        io.print("2. Remove DVD From Collection");
        io.print("3. Edit Existing DVD In Collection");
        io.print("4. List All DVDs In Collection");
        io.print("5. Search For DVD In Collection");
        io.print("6. Exit DVD Library");
        choice = io.readInt("Please Select From The Above Menu.", 1, 6);

        return choice;
    }

    public int printEditMenu() {
        int choice = 0;

        io.print("");
        io.print("---Edit Menu---");
        io.print("1. Edit Title");
        io.print("2. Edit Release Date");
        io.print("3. Edit MPAA Rating");
        io.print("4. Edit Director");
        io.print("5. Edit Studio");
        io.print("6. Edit/Add Notes");
        io.print("7. Exit Edit Menu");
        choice = io.readInt("Please Select From The Above Menu.", 1, 7);

        return choice;

    }

    public String getEdit() {
        return io.readString("Enter The New Information: ");
    }

    public void displayCreateDVDBanner() {
        io.readString("");
        io.print("-----Create New DVD-----");
        io.print("");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD Successfully Created. Please Hit Enter To Continue.");
    }

    public DVD getNewDvdInfo() {
        String title = io.readString("Please Enter DVD Title");
        String releaseDate = io.readDate("Please Enter Release Date (Must be in MM/DD/YYY format)");
        String rating = io.readString("Please Enter MPAA Rating");
        String directorName = io.readString("Please Enter Director Name");
        String studio = io.readString("Please Enter Studio Name");
        String notes = io.readString("Enter Any Additional Notes (Optional). If No Notes Desired, Please Press Enter.");

        DVD currentDVD = new DVD(title, releaseDate, rating, directorName, studio);
        currentDVD.setNote(notes);

        return currentDVD;
    }

    public void displayShowAllDVDBanner() {
        io.readString("");
        io.print("-----Display All DVDs-----");
        io.print("");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDvd : dvdList) {
            io.print("Title: " + currentDvd.getTitle() + "\n----------------" + "\nRelease Date: " + currentDvd.getReleaseDate() + "\nMPAA Rating: "
                    + currentDvd.getRating() + "\nDirector: " + currentDvd.getDirectorName() + "\nProduction Studio: "
                    + currentDvd.getStudio() + "\nNotes: " + currentDvd.getNote() + "\n");
        }
        io.print("");
        io.readString("Please Hit Enter To Continue.");
    }

    public void displayShowDVDBanner() {
        io.print("-----DVD Search-----");
        io.print("");
    }

    public String getDVDChoice() {
        io.readString("");
        return io.readString("Please Enter Title Of The Desired DVD");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("Title: " + dvd.getTitle() + "\n----------------" + "\nRelease Date: " + dvd.getReleaseDate() + "\nMPAA Rating: "
                    + dvd.getRating() + "\nDirector: " + dvd.getDirectorName() + "\nProduction Studio: "
                    + dvd.getStudio() + "\nNotes: " + dvd.getNote() + "\n");
        } else {
            io.print("DVD Not Found. Try Making Sure You Spelled The Title Correctly.");
        }

        io.readString("Please Hit Enter To Continue.");
    }

    public void displayRemoveDVDBanner() {
        io.print("-----Remove DVD-----");
        io.print("");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD Successfully Removed From Collection. Please Hit Enter To Continue.");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD Successfully Edited. Please Hit Enter To Continue.");
    }

    public void displayExitBanner() {
        io.print("Thank You For Using The DVD Library. Happy Watching!");
    }

    public void displayUnknownCommand() {
        io.print("Unknown Command. Returning To Main Menu...");
    }

    public void returnToMenu() {
        io.print("Returning To Main Menu...");
    }

    public void displayError(String errorMsg) {
        io.print("--ERROR--");
        io.print(errorMsg);
    }

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
}
