/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.m3.summative.dao;

import com.mbuettner.m3.summative.dto.VendingItem;
import com.mbuettner.m3.summative.ui.UserIO;
import com.mbuettner.m3.summative.ui.UserIOConsoleImpl;
import com.mbuettner.m3.summative.ui.VendingMachineView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.mbuettner.m3.summative.dto.Coins;

/**
 *
 * @author mbuet
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    public static final String VENDING_FILE = "vendingmachine.txt";
    public static final String DELIMITER = "::";
    UserIO io = new UserIOConsoleImpl();
    VendingMachineView view = new VendingMachineView(io);

    private Map<String, VendingItem> items = new HashMap<>();

    @Override
    public List<VendingItem> getAllAvailableItems() throws VendingMachineDaoException {

        loadItems();
        return items.values().stream().filter(i -> i.getQuantity() > 0).collect(Collectors.toList());
    }

    @Override
    public VendingItem purchaseItem(int choice) throws VendingMachineDaoException {
        List<VendingItem> items = getAllAvailableItems();
        VendingItem purchased = items.get(choice - 1);
        return purchased;
    }

    public void stockReduce(VendingItem item) throws VendingMachineDaoException {
        int currentStock = item.getQuantity();
        item.setQuantity(currentStock - 1);
        writeItems();
    }

    private VendingItem unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String name = itemTokens[0];
        BigDecimal price = new BigDecimal(itemTokens[1]);
        int quantity = Integer.parseInt(itemTokens[2]);
        VendingItem itemFromFile = new VendingItem(name, price, quantity);

        return itemFromFile;
    }

    private void loadItems() throws VendingMachineDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(VENDING_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException("Could Not Load Library Data Into Memory.", e);
        }
        String currentLine;
        VendingItem currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(VendingItem item) {
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getPrice() + DELIMITER;
        itemAsText += item.getQuantity();

        return itemAsText;
    }

    private void writeItems() throws VendingMachineDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_FILE));
        } catch (IOException e) {
            throw new VendingMachineDaoException("Could not save student data.", e);
        }

        String itemAsText;
        List<VendingItem> itemList = new ArrayList(items.values());
        for (VendingItem currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

}
