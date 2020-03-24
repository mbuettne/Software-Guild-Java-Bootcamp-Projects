/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.service;

import com.mbuettner.m3.summative.dao.VendingMachineDaoException;
import com.mbuettner.m3.summative.dto.VendingItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mbuet
 */
public interface VendingMachineServiceLayer {
    
    public boolean hasMoney(VendingItem item, BigDecimal userMoney) throws insufficientFundsException, noItemInventoryException;
    
    public boolean hasStock(VendingItem item) throws noItemInventoryException;
    
    public BigDecimal moneyCalculation(VendingItem item, BigDecimal userMoney) throws insufficientFundsException, noItemInventoryException, VendingMachineDaoException;
}
