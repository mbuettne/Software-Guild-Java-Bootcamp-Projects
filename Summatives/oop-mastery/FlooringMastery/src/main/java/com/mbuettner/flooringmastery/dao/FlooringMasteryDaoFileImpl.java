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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao{
    
    public static final String PATH = "C:\\Users\\mbuet\\Documents\\repos\\java-mpls-0220-mbuettne\\summatives\\oop-mastery\\FlooringMastery";
    public static final String DELIMITER = "::";
    public static final String DATE_FILE = "dates.txt";
    public ArrayList<String> fileNames = new ArrayList<>();
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order saveEdits(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException {
        File file = new File(PATH + date + ".txt");
        fileNames.add(date.toString());
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("dates.txt", true));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        out.println(date);
        out.flush();
        out.close();
    }

    private Order unmarshallOrder(String orderAsText, String fileName) {
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
        LocalDate date = LocalDate.parse(orderTokens[12], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order orderFromFile = new Order(name, state, product, area);
        
        orderFromFile.setDate(date);
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

    private void loadDate() throws FlooringMasteryDaoException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader("artist.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load order into memory.", e);
        }
        while (s.hasNextLine()) {
            fileNames.add(s.nextLine());
        }
        s.close();
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
    private void loadOrders(LocalDate date) throws FlooringMasteryDaoException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(date + ".txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load orders from memory.", e);
        }
        String currentLine;
        Order currentOrder;
        while (s.hasNextLine()) {
            currentLine = s.nextLine();
            currentOrder = unmarshallOrder(currentLine, date.toString());
            orders.put(Integer.toString(currentOrder.getOrderNumber()), currentOrder);
        }
        s.close();
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



    private void writeOrders() throws FlooringMasteryDaoException {
        PrintWriter out;
        for (String currentFile : fileNames) {
            try {
                out = new PrintWriter(new FileWriter(currentFile + ".txt"));
            } catch (IOException e) {
                throw new FlooringMasteryDaoException(
                        "Could not save order data.", e);
            }
            String orderAsText;
            List<Order> orderList = orders.values()
                    .stream()
                    .filter(s -> s.getDate() == LocalDate.parse(currentFile))
                    .collect(Collectors.toList());;
            for (Order currentOrder : orderList) {
                orderAsText = marshallOrder(currentOrder);
                out.println(orderAsText);
                out.flush();
            }
            out.close();
        }
    }
}
