/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m2;

import com.mbuettner.m2.controller.DVDLibraryController;

/**
 *
 * @author mbuet
 */
public class App {
    public static void main(String[] args) {
        DVDLibraryController controller = new DVDLibraryController();
        
        controller.run();
    }
}
