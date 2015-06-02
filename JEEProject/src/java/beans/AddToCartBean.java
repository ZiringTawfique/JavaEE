/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ZiringTawfique
 */
@Stateless
public class AddToCartBean {
   int line =1; 
   private int productID;
   private int orderID;
   private int quantity;
   private int userID;
   
   private static final String URL = "jdbc:derby://localhost:1527/Market;create=true;user=test;password=test";
   private Connection connection;         

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void initLine(){
        this.line = 1;
    }
   
    public  int getOrderID (int userID) throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection(URL);
        String query="SELECT ID FROM ORDERS WHERE PAID = FALSE and ACCOUNTID= ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, userID);

        ResultSet results = st.executeQuery();
        if(results.next()){ //if replaced while
            this.orderID = results.getInt(1);
        }
       // System.out.println(orderID);
        connection.close();
        return orderID;
    };
    
   
    public int addItem () throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection(URL);
        
        String query = "INSERT INTO ITEM VALUES(?,?,?,?)";                    
        PreparedStatement st = connection.prepareStatement(query);
        st.setInt(1, this.line);
        st.setInt(2, this.orderID);
        st.setInt(3, this.productID);
        st.setInt(4, this.quantity); 

        int success =  st.executeUpdate();
        line++;
        connection.close();
        return success;
    }  
}
