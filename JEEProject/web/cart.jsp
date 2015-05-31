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
                <h2>Shopping Cart</h2>
            </div>
            <div id="content">
                <c:forEach var="item" items="${historyBean.historyList.toArray()}">
                        <p>${item.getProductName()}</p>
                        <p>${item.getPrice()}</p>
                </c:forEach>
            </div>
        </div>
        <div id="footer">
            <h2>Random Online Store</h2>
        </div>    
    </body>
</html>
