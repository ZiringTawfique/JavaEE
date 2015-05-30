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
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author disturbedv1
 */
@Stateful
public class HistoryBean {
    private int userID;
    private List<HistoryItem> historyList;

    /*private int itemLine;
    private int orderID;
    private int productID;
    private String productName;
    private double price;
    private int quantity;*/
    
    private static final String URL = "jdbc:derby://localhost:1527/SoukMVC;create=true;user=MoulSouk;password=mika3achra";
    private Connection connection;
    
    public HistoryBean() {
    }

    /**
     * @return the historyList
     */
    public List<HistoryItem> getHistoryList() {
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
            HistoryItem temp = new HistoryItem();
            this.userID = user;
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(URL);
            String query= "SELECT i.Line, i.OrderID, i.ProductID, p.Name, p.Price, i.Quantity FROM Item i LEFT JOIN Product p ON i.ProductID = p.ID WHERE i.OrderID IN (SELECT o.ID FROM Orders o WHERE o.AccountID = ?) ORDER BY i.OrderID, i.Line";
            PreparedStatement statement = connection.prepareStatement(query);                
            statement.setInt(1,this.userID);
            ResultSet results = statement.executeQuery();
            /*if(results.next()){
                this.itemLine=results.getInt(1);
                this.orderID=results.getInt(2);
                this.productID=results.getInt(3);
                this.productName=results.getString(4);
                this.price=results.getDouble(5);
                this.quantity=results.getInt(6);
            }*/
            while(results.next()){
                temp.setItemLine(results.getInt(1));
                temp.setOrderID(results.getInt(2));
                temp.setProductID(results.getInt(3));
                temp.setProductName(results.getString(4));
                temp.setPrice(results.getDouble(5));
                temp.setQuantity(results.getInt(6));
                this.getHistoryList().add(temp);
            }
            connection.close();
        }
        catch(Exception ex){
                System.out.println("MOUCHKILA!!! THERE'S AN EXCEPTION SOMEWHERE" + ex);
        }
    }
    
}
