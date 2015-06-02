<%-- 
    Document   : history
    Created on : May 29, 2015, 2:43:26 PM
    Author     : disturbedv1
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>Souk MVC: Online Shop - Previous Orders</title>
    </head>
    <body>
        <jsp:useBean id="historyBean" scope="request" class="beans.HistoryBean" />
        <div id="container">
            <div id="header">
                <h2>Your Previous Purchases</h2>
              
                <form action="historyServlet" method="POST">
                    <input name="productButton" type="submit" value="Products" />
                    <input name="cartButton" type ="submit" value="Go to Cart" />
                    <input name="historyButton" type="submit" value="Past Orders" />
                    <input name="accountButton" type="submit" value="Account" />
                    <input name="logoutButton" type="submit" value="Log out"/>
                </form> 
            </div>
            
            <div id="content">
               
                <c:forEach var="item" items="${historyBean.historyList.toArray()}">
                    <div class="ListBox">
                        <img src="img/${item.getProductID()}.jpg" alt="img/1.jpg"/>
                        <div class="boxText">
                            <h4 class="historyName">${item.getProductName()}</h4>
                            <p class="historyPrice">${item.getPrice()} SEK   *   ${item.getQuantity()}   =   ${item.getPrice() * item.getQuantity()} SEK</p>
                        </div>
                    </div>
                </c:forEach>
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
</html>
