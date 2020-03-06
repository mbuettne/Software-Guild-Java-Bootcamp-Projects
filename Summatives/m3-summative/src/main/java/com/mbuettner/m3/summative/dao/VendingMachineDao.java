/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dao;

import com.mbuettner.m3.summative.dto.VendingItem;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface VendingMachineDao {
    
    List<VendingItem> getAllAvailableItems() throws VendingMachineDaoException;
    
    public VendingItem purchaseItem(int choice) throws VendingMachineDaoException;
    
    public boolean hasStock(VendingItem item);
    
    public boolean hasMoney(VendingItem item, double userMoney);
    
    public double moneyCalculation(VendingItem item, double userMoney);
    
     public void stockReduce(VendingItem item) throws VendingMachineDaoException;
    
}
