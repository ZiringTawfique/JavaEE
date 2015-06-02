/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author disturbedv1
 */
@Stateful
public class HistoryBean {
    private int userID;
    private ArrayList<HistoryItem> historyList;
    
    private static final String URL = "jdbc:derby://localhost:1527/Market;create=true;user=test;password=test";
    private Connection connection;
    
    public HistoryBean() {
    }

    /**
     * @return the historyList
     */
    public ArrayList<HistoryItem> getHistoryList() {
        return historyList;
    }
    
    /**
     * @param userID the itemLine to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void readHistory(int user) throws ClassNotFoundException, SQLException{
        try{
            this.historyList = new ArrayList<HistoryItem>();
            this.userID = user;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(URL);
            String query= "SELECT i.Line, i.OrderID, i.ProductID, p.Name, p.Price, i.Quantity "
                    + "FROM Item i LEFT JOIN Product p ON i.ProductID = p.ID "
                    + "WHERE i.OrderID IN (SELECT o.ID FROM Orders o WHERE o.AccountID = ? AND o.PAID = TRUE) "
                    + "ORDER BY i.OrderID, i.Line";
            PreparedStatement statement = connection.prepareStatement(query);                
            statement.setInt(1,this.userID);
            ResultSet results = statement.executeQuery();
            while(results.next()){
                HistoryItem temp = new HistoryItem();
                temp.setItemLine(results.getInt(1));
                temp.setOrderID(results.getInt(2));
                temp.setProductID(results.getInt(3));
                temp.setProductName(results.getString(4));
                temp.setPrice(results.getDouble(5));
                temp.setQuantity(results.getInt(6));
                this.historyList.add(temp);
            }
            connection.close();
        }
        catch(Exception ex){
                System.out.println("MOUCHKILA!!! THERE'S AN EXCEPTION SOMEWHERE" + ex);
        }
    }
    
    public void ShowCart(int user) throws ClassNotFoundException, SQLException{
        try{
            this.historyList = new ArrayList<HistoryItem>();
            this.userID = user;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(URL);
            String query= "SELECT i.Line, i.OrderID, i.ProductID, p.Name, p.Price, i.Quantity "
                    + "FROM Item i LEFT JOIN Product p ON i.ProductID = p.ID "
                    + "WHERE i.OrderID IN (SELECT o.ID FROM Orders o WHERE o.AccountID = ? AND o.PAID = FALSE) "
                    + "ORDER BY i.OrderID, i.Line";
            PreparedStatement statement = connection.prepareStatement(query);                
            statement.setInt(1,this.userID);
            ResultSet results = statement.executeQuery();
            while(results.next()){
                HistoryItem temp = new HistoryItem();
                temp.setItemLine(results.getInt(1));
                temp.setOrderID(results.getInt(2));
                temp.setProductID(results.getInt(3));
                temp.setProductName(results.getString(4));
                temp.setPrice(results.getDouble(5));
                temp.setQuantity(results.getInt(6));
                this.historyList.add(temp);
            }
            connection.close();
        }
        catch(Exception ex){
                System.out.println("MOUCHKILA!!! THERE'S AN EXCEPTION SOMEWHERE" + ex);
        }
    }
    
    public void FinalizeDeal(int user, double balance) throws ClassNotFoundException, SQLException{
        this.userID = user;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection(URL);
        double total = computeOrderTotal();
        if(total < balance){
            String query1 = "UPDATE ORDERS O SET O.PAID = TRUE, O.TOTAL = ? "
                    + "WHERE O.ID = (SELECT MAX(r.ID) FROM ORDERS r WHERE ACCOUNTID = ? AND r.PAID = FALSE)";
            PreparedStatement statement = connection.prepareStatement(query1);                
            statement.setDouble(1, total);
            statement.setInt(2,this.userID);
            statement.executeUpdate();
            
            String query2 = "UPDATE ACCOUNT A SET A.BALANCE = (A.BALANCE - ?) WHERE A.ID = ?";
            statement = connection.prepareStatement(query2);
            statement.setDouble(1, total);
            statement.setInt(2,this.userID);
            statement.executeUpdate();

            String query3 = "INSERT INTO ORDERS (ACCOUNTID) VALUES (?)";
            PreparedStatement statement2 = connection.prepareStatement(query3);
            statement2.setInt(1,this.userID);
            System.out.println("=======Executing order insert");
            statement2.executeUpdate();
            System.out.println("=======Executed order insert");

            ShowCart(user);
            connection.close();
        }
    }
    
    private double computeOrderTotal(){
        double total=0;
        
        for(int i=0; i<this.historyList.size();i++){
            total += (historyList.get(i).getPrice() * historyList.get(i).getQuantity());
        }
        return total;
    }
    
    public void EmptyCart(int user) throws ClassNotFoundException, SQLException{
        this.userID = user;
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection(URL);
        String query1= "DELETE FROM ORDERS WHERE ACCOUNTID = ? AND PAID = FALSE";
        PreparedStatement statement = connection.prepareStatement(query1);
        statement.setInt(1,this.userID);
        statement.executeUpdate();
        
        String query2 = "INSERT INTO ORDERS (ACCOUNTID) VALUES (?)";
        statement = connection.prepareStatement(query2);
        statement.setInt(1,this.userID);
        statement.executeUpdate();
        
        ShowCart(user);
        connection.close();
    }
}
