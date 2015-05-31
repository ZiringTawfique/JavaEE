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
                <h2>Welcome Random User</h2>
            </div>
            <div id="content">
                <c:forEach var="item" items="${historyBean.historyList.toArray()}">
                    <p>${item.getProductName()}</p>
                    <p>${item.getPrice()}</p>
                </c:forEach>
            </div>
            <div id="footer">
                <h2>Stupid Footer!</h2>
            </div>
        </div>
    </body>
</html>
