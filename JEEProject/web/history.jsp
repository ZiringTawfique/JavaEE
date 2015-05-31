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
                <h2>Welcome <c:out value="${userBean.firstname}"/>!</h2>
                <form action="historyServlet" method="POST">
                    <input name="productButton" type="submit" value="Products" />
                    <input name="historyButton" type="submit" value="Past Orders" />
                    <input name="accountButton" type="submit" value="Account" />
                </form> 
            </div>
            
            <div id="content">
                <div id="ListBox">
                    <img src="img/7.jpg" alt="img/1.jpg"/>
                    <span class="spacing"><h4>T shirt Utd Season 2000/2011</h4></span>
                    <span class="spacing"><p>300 SEK   *   1   =   300 SEK</p></span>
                </div>
                <br/>
                <div id="ListBox">
                    <img src="img/10.jpg" alt="img/1.jpg"/>
                    <span class="spacing"><h4>T shirt Bayern Season 2000/2011</h4></span>
                    <span class="spacing"><p>800 SEK   *   1   =   800 SEK</p></span>
                </div>
                
                <c:forEach var="item" items="${historyBean.historyList.toArray()}">
                    <p>${item.getProductName()}</p>
                    <p>${item.getPrice()}</p>
                </c:forEach>
            </div>
            <div id="footer">
                <h2>Random Online Store</h2>
            </div>
        </div>
    </body>
</html>
