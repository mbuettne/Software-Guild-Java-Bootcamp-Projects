/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.service;

import com.mbuettner.m3.summative.dao.VendingMachineAuditDao;
import com.mbuettner.m3.summative.dao.VendingMachineDao;
import com.mbuettner.m3.summative.dao.VendingMachineDaoException;
import com.mbuettner.m3.summative.dto.VendingItem;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mbuet
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public boolean hasMoney(VendingItem item, BigDecimal userMoney) throws insufficientFundsException, noItemInventoryException {

        if (userMoney.compareTo(item.getPrice()) < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean hasStock(VendingItem item) throws noItemInventoryException {

        if (item.getQuantity() == 0) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public BigDecimal moneyCalculation(VendingItem item, BigDecimal userMoney) throws insufficientFundsException, noItemInventoryException, VendingMachineDaoException {

        BigDecimal newUserMoney = userMoney.subtract(item.getPrice());
        newUserMoney.setScale(2, RoundingMode.HALF_UP);
        
        auditDao.writeAuditEntry("Item " + item.getName() + " Purchased For " + item.getPrice() + ". " + (item.getQuantity() - 1) + " Items Remaining In Stock.");
        
        return newUserMoney;
    }

}
