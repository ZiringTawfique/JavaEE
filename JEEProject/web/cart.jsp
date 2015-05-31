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
        <title>JSP Page</title>
    </head>
    <body>
        
        <jsp:useBean id="historyBean" scope="request" class="beans.HistoryBean" />
        <div id="container">
            <div id="header">
                <h2>Your Shopping Cart</h2>
                <form action="historyServlet" method="POST">
                    <input name="productButton" type="submit" value="Products" />
                    <input name="historyButton" type="submit" value="Past Orders" />
                    <input name="accountButton" type="submit" value="Account" />
                    <input name="cartButton" type ="submit" value="Go to Cart" />
                </form> 
            </div>
            <div id="content">
                <c:forEach var="item" items="${historyBean.historyList.toArray()}">
                        <p>${item.getProductName()}</p>
                        <p>${item.getPrice()}</p>
                </c:forEach> 
                <c:if test="${historyBean.historyList.isEmpty()== false}">
                    <form action="historyServlet" method="POST">
                        <input name="finalizeButton" type="submit" value="Finalize" />
                    </form>
                </c:if>
                <!-- TODO: Display images and information regarding each product -->
            </div>
        </div> 
        <div id="footer">
            <h2>Random Online Store</h2>
        </div>    
    </body>
</html>
