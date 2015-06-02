<%-- 
    Document   : account
    Created on : May 30, 2015, 2:23:48 PM
    Author     : Charalampos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/main.css" rel="stylesheet" type="text/css"/>
        <title>Souk MVC: Online Shop - My Account</title>
    </head>
    <body>        
        <div id="container">
            <div id="header">
                <h2>Your Account Information</h2>
              
                <form action="historyServlet" method="POST">
                    <input name="productButton" type="submit" value="Products" />
                    <input name="cartButton" type ="submit" value="Go to Cart" />
                    <input name="historyButton" type="submit" value="Past Orders" />
                    <input name="accountButton" type="submit" value="Account" />
                    <input name="logoutButton" type="submit" value="Log out"/>
                </form> 
            </div>
        
            <div id="content">
                <h2>Your personal information</h2>

                <h3><c:out value="Firstname" /> : <c:out value="${userBean.getFirstname()}" /></h3>
                <h3><c:out value="Lastname" /> : <c:out value="${userBean.getLastname()}" /></h3>
                <h3><c:out value="Address" /> : <c:out value="${userBean.getAddress()}" /></h3>
                <h3><c:out value="City" /> : <c:out value="${userBean.getCity()}" /></h3>
                <h3><c:out value="Country" /> : <c:out value="${userBean.getCountry()}" /></h3>
                <h3><c:out value="Email" /> : <c:out value="${userBean.getEmail()}" /></h3>
                <h3><c:out value="Balance" /> : <c:out value="${userBean.getBalance()}" /></h3>
                           
                <div id="updateinfo">
                    <br/>
                    <h3>Update your contact information here</h3>
                    <h4>New contact information</h4>
                    <form action="historyServlet" method='POST'>
                      <input type="text" name="address" placeholder="Address" />
                      <input type="email" name ="email" placeholder="E-mail" />
                      <input name="updatebutton" type="submit" value="Update" />
                    </form>  
                    <!-- TODO: Check first if fields are empty, then update -->
                </div>
            </div>
                
            <div id="footer">
                <h2>Souk MVC - All Rights Reserved</h2>
                <h4><c:out value="Web store made by: "/></h4>
                <h6 id="namespacing">Garara, Ilyass</h6>
                <h6 id="namespacing">Georgiadis, Charalampos</h6>
                <h6 id="namespacing">Tawfique, Ziring</h6>
            </div>
        </div>
    </body>