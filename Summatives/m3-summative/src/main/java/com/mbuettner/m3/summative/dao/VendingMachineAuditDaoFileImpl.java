/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mbuet
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao{
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws VendingMachineDaoException {
        PrintWriter out; 
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException ex) {
            throw new VendingMachineDaoException("Could Not Persist Audit Information", ex);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
    
}
