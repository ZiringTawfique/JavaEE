<%-- 
    Document   : cart
    Created on : May 31, 2015, 5:05:46 PM
    Author     : Charalampos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/main.css" rel="stylesheet" type="text/css"/>
        <title>Souk MVC: Online Shop - Shopping Cart</title>
    </head>
    <body>
        
        <jsp:useBean id="historyBean" scope="request" class="beans.HistoryBean" />
        <div id="container">
            <div id="header">
                <h2>Your Shopping Cart</h2>
              
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
                <c:if test="${historyBean.historyList.isEmpty()== false}">
                    <form action="historyServlet" method="POST">
                        <input name="finalizeButton" type="submit" value="Finalize" />
                        <input name="emptyButton" type="submit" value="Empty Cart" />
                    </form>
                </c:if>
                <!-- TODO: Display images and information regarding each product -->
            </div>
        </div> 
        <div id="footer">
            <h2>Souk MVC - All Rights Reserved</h2>
            <h4><c:out value="Web store made by: "/></h4>
            <h6 id="namespacing">Garara, Ilyass</h6>
            <h6 id="namespacing">Georgiadis, Charalampos</h6>
            <h6 id="namespacing">Tawfique, Ziring</h6>
        </div>   
    </body>
</html>
