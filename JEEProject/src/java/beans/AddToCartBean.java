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
   int line =0; 
   private int productID;
   private int orderID;
   private int quantity;
   private int userID;
   private static final String URL = "jdbc:derby://localhost:1527/SoukMVC;create=true;user=MoulSouk;password=mika3achra";
   
    private Connection getDBConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }
          

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
        //System.out.println(productID);
    }

  
    public int getQuantity() {
        
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        //System.out.println(quantity);
        
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public  int getOrderID (int userID) throws ClassNotFoundException, SQLException{
        System.out.println(userID);
        String query;             
            //Statement statement = getDBConnection().createStatement();
            query="SELECT ID FROM ORDERS WHERE ACCOUNTID= ?";
            PreparedStatement st = getDBConnection().prepareStatement(query);
            st.setInt(1, userID);
            
            ResultSet results = st.executeQuery();
            if(results.next()){ //if replaced while
                this.orderID = results.getInt(1);
               
            }
            
            return orderID;
            
    };
    
   
    public void addItem () throws ClassNotFoundException, SQLException{
        String query;
        line++;
            query = "INSERT INTO ITEM VALUES(?,?,?,?)";
                    
            PreparedStatement st = getDBConnection().prepareStatement(query);
            st.setInt(1,line);
            st.setInt(2, this.orderID);
            st.setInt(3, this.productID);
            st.setInt(4, this.quantity);           
            st.executeUpdate();   
    };
    
    
     
    
}
