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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author mbuet
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMddyyyy");
    public static final String PATH = "C:\\Users\\mbuet\\Documents\\repos\\java-mpls-0220-mbuettne\\Summatives\\oop-mastery\\FlooringMastery\\Orders_";
    public static final String DELIMITER = "::";
    public static final String DATES_FILE = "dates.txt";
    public static final String ORDER_NUMBERS = "orderNumbers.txt";
    public static final String TAXES = "taxes.txt";
    public static final String PRODUCTS = "products.txt";
    public ArrayList<String> orderNumbers = new ArrayList<>();
    public Set<String> fileNames = new HashSet<>();
    private Map<String, Order> orders = new HashMap<>();
    private HashMap<String, BigDecimal> taxes = new HashMap<>();
    private HashMap<String, ArrayList<BigDecimal>> products = new HashMap<>();

    //CRUD Functions
    @Override
    public Order createOrder(String name, String state, String product, BigDecimal area) throws FlooringMasteryDaoException {
        //load state taxes list and products prices lists
        loadTaxes();
        taxes = getTaxList();
        loadProducts();
        products = getProductList();

        //load orders list for date of entry, create new Order object with input data
        orders = loadOrders(LocalDate.now());
        Order newOrder = new Order(name, state, product, area);

        //Set all order info
        newOrder.setTaxRate(taxes.get(state));
        newOrder.setProductCost(products.get(product).get(0));
        newOrder.setLaborCost(products.get(product).get(1));
        newOrder.setCostSqFt();
        newOrder.setLaborSqFt();
        newOrder.setTaxCost();
        newOrder.setTotalCost();
        newOrder.setDate(LocalDate.now());

        //Set order number after object is created and other info is set
        loadOrderNumbers();
        writeOrderNumbers();
        int nextOrderNum = getNextOrderNumber();
        newOrder.setOrderNumber(nextOrderNum);

        //add order to list of orders for entry date, add order to map of all orders in dao
        orders.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
        setMap(newOrder);

        return newOrder;
    }

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
        //load orders from entry date to find what should be edited
        orders = loadOrders(date);

        //load tax an product info to compare to entry and set later values
        loadTaxes();
        loadProducts();
        taxes = getTaxList();
        products = getProductList();

        //add new or old values to a new order which will be returned
        Order orderToBeEdited;
        if (orders.containsKey(Integer.toString(orderNumber))) {
            //new order will have same order number as the original
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
            if (editedArea.compareTo(BigDecimal.ZERO) != 0) {
                orderToBeEdited.setArea(editedArea);
            }

            //Set all remaining info in relation to above info
            orderToBeEdited.setProductCost(products.get(orderToBeEdited.getProduct()).get(0));
            orderToBeEdited.setCostSqFt();
            orderToBeEdited.setLaborCost(products.get(orderToBeEdited.getProduct()).get(1));
            orderToBeEdited.setLaborSqFt();
            orderToBeEdited.setTaxRate(taxes.get(orderToBeEdited.getState()));
            orderToBeEdited.setTaxCost();
            orderToBeEdited.setTotalCost();
            orderToBeEdited.setDate(date);
            orderToBeEdited.setOrderNumber(orderNumber);

        } else {
            System.out.println("Can't Find Order. Please Check Spelling And Try Again.");
            orderToBeEdited = new Order("", "", "", new BigDecimal("0"));
        }

        return orderToBeEdited;
    }

    //Check If Info Exists Functions
    @Override
    public void checkDate(LocalDate date) throws FlooringMasteryDaoException, IOException {
        loadDates();

        for (String currentDate : fileNames) {
            if (currentDate.equals(date.toString())) {
                break;
            }
        }
        createNewFile(date);
    }

    @Override
    public boolean checkDateExists(LocalDate date) throws FlooringMasteryDaoException {
        boolean dateExists = false;
        loadDates();
        for (String currentDate : fileNames) {
            if (currentDate.equals(date.toString())) {
                dateExists = true;
            }
        }
        return dateExists;
    }

    @Override
    public boolean checkOrderExists(LocalDate date, int orderNumber) throws FlooringMasteryDaoException {
        boolean orderExists = false;
        orders = loadOrders(date);
        if (orders.containsKey(Integer.toString(orderNumber))) {
            orderExists = true;
        }
        return orderExists;
    }

    //Order Map Manipulation Functions
    @Override
    public void setMap(Order newOrder) throws FlooringMasteryDaoException {
        orders = loadOrders(newOrder.getDate());
        orders.put(Integer.toString(newOrder.getOrderNumber()), newOrder);
    }

    @Override
    public void replaceMap(Order newOrder, LocalDate date) throws FlooringMasteryDaoException {
        loadDates();
        orders = loadOrders(date);
        orders.replace(Integer.toString(newOrder.getOrderNumber()), newOrder);
    }

    //Write/Create Functions
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
    public void writeEdits(LocalDate date) throws FlooringMasteryDaoException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("Orders_" + date.format(DATE_FORMAT) + ".txt", false));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        String orderAsText;

        for (Order currentOrder : orders.values()) {
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writeOrders(LocalDate date, Order newOrder) throws FlooringMasteryDaoException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("Orders_" + date.format(DATE_FORMAT) + ".txt", true));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        String orderAsText;
        orderAsText = marshallOrder(newOrder);
        out.println(orderAsText);
        out.flush();
        out.close();
    }

    @Override
    public void writeOrders(LocalDate date) throws FlooringMasteryDaoException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("Orders_" + date.format(DATE_FORMAT) + ".txt", false));
        } catch (IOException e) {
            throw new FlooringMasteryDaoException(
                    "Could not save order data.", e);
        }
        String orderAsText;

        for (Order currentOrder : orders.values()) {
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public void writeOrderNumbers() throws FlooringMasteryDaoException {
        loadOrderNumbers();
        PrintWriter out;
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

    @Override
    public void createNewFile(LocalDate date) throws FlooringMasteryDaoException, IOException {
        PrintWriter out;
        File file = new File(PATH + date.format(DATE_FORMAT) + ".txt");
        fileNames.add(date.toString());
        if (file.createNewFile()) {
            try {
                out = new PrintWriter(new FileWriter(DATES_FILE, true));
            } catch (IOException e) {
                throw new FlooringMasteryDaoException(
                        "Could not save order data.", e);
            }
            out.println(date);
            out.flush();
            out.close();
        }
    }

    //Load/Read Functions
    private Order unmarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);

        //get info from string tokens
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

        // set info from string tokens to new order object
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
    public HashMap<String, Order> loadOrders(LocalDate date) throws FlooringMasteryDaoException {
        Scanner s;
        HashMap<String, Order> ordersMap = new HashMap<>();
        try {
            s = new Scanner(new BufferedReader(new FileReader("Orders_" + date.format(DATE_FORMAT) + ".txt")));
        } catch (FileNotFoundException e) {
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

    private HashMap<String, BigDecimal> unmarshallTaxes(String stateInfo) {
        String[] taxTokens = stateInfo.split(DELIMITER);
        String state = taxTokens[0];
        BigDecimal taxRate = new BigDecimal(taxTokens[1]);
        HashMap<String, BigDecimal> taxMap = new HashMap<String, BigDecimal>();
        taxMap.put(state, taxRate);
        return taxMap;
    }

    @Override
    public void loadTaxes() throws FlooringMasteryDaoException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(TAXES)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load orders from memory.", e);
        }
        String currentLine;
        while (s.hasNextLine()) {
            currentLine = s.nextLine();
            HashMap<String, BigDecimal> temp = unmarshallTaxes(currentLine);
            for (String i : temp.keySet()) {
                taxes.put(i, temp.get(i));
            }
        }
        s.close();
    }

    @Override
    public HashMap<String, BigDecimal> getTaxList() throws FlooringMasteryDaoException {
        return taxes;
    }

    private HashMap<String, ArrayList<BigDecimal>> unmarshallProducts(String productInfo) {
        String[] productTokens = productInfo.split(DELIMITER);
        String product = productTokens[0];
        BigDecimal productCost = new BigDecimal(productTokens[1]);
        BigDecimal laborCost = new BigDecimal(productTokens[2]);
        ArrayList<BigDecimal> bdList = new ArrayList<BigDecimal>();
        bdList.add(productCost);
        bdList.add(laborCost);
        HashMap<String, ArrayList<BigDecimal>> taxMap = new HashMap<String, ArrayList<BigDecimal>>();
        taxMap.put(product, bdList);
        return taxMap;
    }

    @Override
    public void loadProducts() throws FlooringMasteryDaoException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(PRODUCTS)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryDaoException(
                    "-_- Could not load orders from memory.", e);
        }
        String currentLine;
        while (s.hasNextLine()) {
            currentLine = s.nextLine();
            HashMap<String, ArrayList<BigDecimal>> temp = unmarshallProducts(currentLine);
            for (String i : temp.keySet()) {
                products.put(i, temp.get(i));
            }
        }
        s.close();
    }

    @Override
    public HashMap<String, ArrayList<BigDecimal>> getProductList() throws FlooringMasteryDaoException {
        return products;
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
}
