/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbuettner.flooringmastery.dao;

import com.mbuettner.flooringmastery.dto.Order;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import com.mbuettner.flooringmastery.dao.FlooringMasteryDaoException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    public static final String PATH = "C:\\Users\\mbuet\\Documents\\repos\\java-mpls-0220-mbuettne\\Summatives\\oop-mastery\\FlooringMastery";
    public static final String DELIMITER = "::";
    public static final String DATES_FILE = "dates.txt";
    public static final String ORDER_NUMBERS = "orderNumbers.txt";
    public ArrayList<String> orderNumbers = new ArrayList<>();
    public Set<String> fileNames = new HashSet<>();
    private Map<String, Order> orders = new HashMap<>();

    public Order removeOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        orders = loadOrders(date);
        Order removedOrder;
        if (orders.containsKey(Integer.toString(orderNumber))) {
            removedOrder = orders.remove(Integer.toString(orderNumber));
        } else {
            System.out.println("Order Not Found.");
            removedOrder = new Order("", "", "", new BigDecimal("0"));
        }
        writeOrders(date);
        return removedOrder;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber, Order edited) throws FlooringMasteryDaoException {
        orders = loadOrders(date);
        Order orderToBeEdited;
        if (orders.containsKey(Integer.toString(orderNumber))) {
            orderToBeEdited = orders.get(Integer.toString(orderNumber));
            String editedName = edited.getName();
            if (!editedName.equals("")) {
                orderToBeEdited.setName(editedName);
            } 

            String editedState = edited.getState();
            if (!editedState.equals("")) {
                orderToBeEdited.setState(editedState);
            }

            String editedProduct = edited.getProduct();
            if (!editedProduct.equals("")) {
                orderToBeEdited.setProduct(editedProduct);
            } 

            BigDecimal editedArea = edited.getArea();
            if (!editedArea.equals("0.00")) {
                orderToBeEdited.setArea(editedArea);
            } 
            
            orderToBeEdited.setProductCost();
            orderToBeEdited.setCostSqFt();
            orderToBeEdited.setLaborCost();
            orderToBeEdited.setLaborSqFt();
            orderToBeEdited.setTaxRate();
            orderToBeEdited.setTaxCost();
            orderToBeEdited.setTotalCost();
            orderToBeEdited.setDate(date);
            orderToBeEdited.setOrderNumber(orderNumber);

            orders.replace(Integer.toString(orderNumber), orderToBeEdited);
            replaceMap(orderToBeEdited, date);
            writeOrders(orderToBeEdited.getDate());
        } else {
            System.out.println("Can't Find Order. Please Check Spelling And Try Again.");
            orderToBeEdited = new Order("", "", "", new BigDecimal("0"));
        }

        //writeOrders();
        return orderToBeEdited;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMap(Order newOrder) throws FlooringMasteryDaoException {
        orders.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
    }

    @Override
    public void replaceMap(Order newOrder, LocalDate date) throws FlooringMasteryDaoException {
        loadDates();
        orders = loadOrders(date);
        orders.replace(Integer.toString(newOrder.getOrderNumber()), newOrder);
    }

    @Override
    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException {
        File file = new File(PATH + date + ".txt");
        fileNames.add(date.toString());
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(DATES_FILE, false));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        out.println(date);
        out.flush();
        out.close();
    }

    private Order unmarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);
        int orderNumber = Integer.parseInt(orderTokens[0]);
        String name = orderTokens[1];
        String state = orderTokens[2];
        BigDecimal taxRate = new BigDecimal(orderTokens[3]);
        String product = orderTokens[4];
        BigDecimal area = new BigDecimal(orderTokens[5]);
        BigDecimal costSqFt = new BigDecimal(orderTokens[6]);
        BigDecimal laborSqFt = new BigDecimal(orderTokens[7]);
        BigDecimal materialCost = new BigDecimal(orderTokens[8]);
        BigDecimal laborCost = new BigDecimal(orderTokens[9]);
        BigDecimal taxCost = new BigDecimal(orderTokens[10]);
        BigDecimal totalCost = new BigDecimal(orderTokens[11]);
        Order orderFromFile = new Order(name, state, product, area);

        // orderFromFile.setDate(date);
        orderFromFile.setLaborCost(laborCost);
        orderFromFile.setOrderNumber(orderNumber);
        orderFromFile.setTaxRate(taxRate);
        orderFromFile.setCostSqFt(costSqFt);
        orderFromFile.setLaborSqFt(laborSqFt);
        orderFromFile.setProductCost(materialCost);
        orderFromFile.setTaxCost(taxCost);
        orderFromFile.setTotalCost(totalCost);
        return orderFromFile;
    }

    @Override
    public void loadDates() throws FlooringMasteryDaoException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(DATES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load orders from memory.", e);
        }
        while (s.hasNextLine()) {
            fileNames.add(s.nextLine());
        }
        s.close();
    }

    @Override
    public void loadOrderNumbers() throws FlooringMasteryDaoException {
        Scanner s;
        orderNumbers = new ArrayList<>();
        try {
            s = new Scanner(new BufferedReader(new FileReader(ORDER_NUMBERS)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load orders from memory.", e);
        }
        while (s.hasNextLine()) {
            orderNumbers.add(s.nextLine());
        }
        s.close();
    }

    @Override
    public int getNextOrderNumber() {
        int nextOrderNumber = orderNumbers.size() + 1;
        return nextOrderNumber;
    }
//THIS METHOD LOADS ALL SONGS FROM ALL FILES

//  private void loadAllSongs() throws EyeTunesDaoException {
//        Scanner s;
//        for (String currentFile : fileNames) {
//            try {
//                s = new Scanner(new BufferedReader(new FileReader(currentFile + ".txt")));
//            } catch (FileNotFoundException e) {
//                throw new EyeTunesDaoException(
//                        "-_- Could not load songs into memory.", e);
//            }
//            String currentLine;
//            Song currentSong;
//            while (s.hasNextLine()) {
//                currentLine = s.nextLine();
//                currentSong = unmarshallSong(currentLine, currentFile);
//                songs.put(currentSong.getTitle().toUpperCase(), currentSong);
//            }
//            s.close();
//        }
//    }
    //Loads all songs from a single file 
    @Override
    public HashMap<String, Order> loadOrders(LocalDate date) throws FlooringMasteryDaoException {
        Scanner s;
        HashMap<String, Order> ordersMap = new HashMap<>();
        try {
            s = new Scanner(new BufferedReader(new FileReader(date + ".txt")));
        } catch (FileNotFoundException e) {
            //createNewFile(date);
            throw new FlooringMasteryDaoException(
                    "-_- Could not load orders from memory.", e);
        }
        String currentLine;
        Order currentOrder;
        while (s.hasNextLine()) {
            currentLine = s.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            ordersMap.put(Integer.toString(currentOrder.getOrderNumber()), currentOrder);

        }
        s.close();
        return ordersMap;
    }

    private String marshallOrder(Order order) {
        String orderAsText = order.getOrderNumber() + DELIMITER;
        orderAsText += order.getName() + DELIMITER;
        orderAsText += order.getState() + DELIMITER;
        orderAsText += order.getTaxRate() + DELIMITER;
        orderAsText += order.getProduct() + DELIMITER;
        orderAsText += order.getArea() + DELIMITER;
        orderAsText += order.getCostSqFt() + DELIMITER;
        orderAsText += order.getLaborSqFt() + DELIMITER;
        orderAsText += order.getProductCost() + DELIMITER;
        orderAsText += order.getLaborCost() + DELIMITER;
        orderAsText += order.getTaxCost() + DELIMITER;
        orderAsText += order.getTotalCost();
        return orderAsText;
    }

    @Override
    public void writeOrders(LocalDate date) throws FlooringMasteryDaoException {
        //  loadDates();
        PrintWriter out;
        //   for (String currentFile : fileNames) {
        try {
            out = new PrintWriter(new FileWriter(date + ".txt", false));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        String orderAsText;
        List<Order> orderList = orders.values()
                .stream()
                //       .filter(s -> s.getDate().toString().equals(currentFile))
                .collect(Collectors.toList());;
        for (Order currentOrder : orderList) {
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }
    //   }

    @Override
    public void writeOrderNumbers() throws FlooringMasteryDaoException {
        loadOrderNumbers();
        PrintWriter out;
        //  for (String currentNumber : orderNumbers) {
        try {
            out = new PrintWriter(new FileWriter(ORDER_NUMBERS, true));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        String nextOrderNumber = Integer.toString(orderNumbers.size() + 1);
        out.println(nextOrderNumber);
        out.flush();
        out.close();
    }
    //}

}
