package beans;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateful;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class UserBean {
    private String salt = "ZtbI052Z";
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String city;
    private String country;
    private double balance;
    private static final String URL = "jdbc:derby://localhost:1527/SoukMVC;create=true;user=MoulSouk;password=mika3achra";
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
    
    public String getCity() {
        return address;
    }
    
    public String getCountry() {
        return address;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    private Connection getDBConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }
    
    public boolean authenticate() throws ClassNotFoundException, SQLException{
        boolean match = false;
        try{
            String protectedPassword = hashPass(salt+password);
            String query;             
            //Statement statement = getDBConnection().createStatement();
            query="SELECT username, firstname, lastname, email, address, city, country, "
                    + "balance FROM ACCOUNT WHERE username= ? AND password= ?";
            PreparedStatement st = getDBConnection().prepareStatement(query);
            st.setString(1, this.username);
            st.setString(2, protectedPassword);
            
            System.out.println(protectedPassword);
            ResultSet results = st.executeQuery();
            if(results.next()){ //if replaced while
                this.username = results.getString(1);
                this.firstname = results.getString(2);
                this.lastname = results.getString(3);
                this.email = results.getString(4);
                this.address = results.getString(5);
                this.city = results.getString(6);
                this.country = results.getString(7);
                this.balance = results.getDouble(8);
                match = true;
            }
            return match;  
        }
        catch(Exception ex){
                System.out.println("THERE'S AN EXCEPTION SOMEWHERE" + ex);
        }
        return match;
    }
    
    
    public static String hashPass(String password) throws NoSuchAlgorithmException{
        String hashPass = null;
        
        
        if (null==password){return null;}
        else
        {
        MessageDigest messageToDigit = MessageDigest.getInstance("MD5");
        
        messageToDigit.update(password.getBytes(),0,password.length());
             
        hashPass = new BigInteger(1, messageToDigit.digest()).toString(16);
        }
       
    return hashPass;
    };
}
