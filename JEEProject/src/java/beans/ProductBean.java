/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateless;


/**
 *
 * @author ZiringTawfique
 */
@Stateless
public class ProductBean{

    private ArrayList<ProductItem> productList;
    private static final String URL = "jdbc:derby://localhost:1527/SoukMVC;create=true;user=MoulSouk;password=mika3achra";
    private Connection connection;
   

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public ProductBean() {
    }

    public ArrayList<ProductItem> getProductList() {
        return productList;
    }
    
    
 public void readProduct() throws ClassNotFoundException, SQLException{
        try{
            this.productList = new ArrayList<ProductItem>();
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            String query= "SELECT * FROM Product";
              

            ResultSet results = statement.executeQuery(query);
            while(results.next()){
                ProductItem productItem = new ProductItem();
                productItem.setID(results.getInt(1));
                productItem.setName(results.getString(2));
                productItem.setPrice(results.getInt(3));
                productItem.setCategory(results.getString(5));
                productItem.setDescription(results.getString(6));
                System.out.println(productItem);
                this.productList.add(productItem);
            }
            connection.close();
        }
        catch(Exception ex){
                System.out.println("Exception Occured" + ex);
        }
    }
   
    
}
