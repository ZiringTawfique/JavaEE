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
        <jsp:useBean id="historyBean" scope="session" class="beans.HistoryBean" />
        <div id="container">
            <div id="header">
                <h2>Welcome Random User</h2>
            </div>
            <div id="content">
                <p>chi haja</p>
                <c:forEach var="item" items="historyBean.historyList">
                    <p>${item}</p>
                </c:forEach>
                <p>chi haja oukhra</p>
            </div>
            <div id="footer">
                <h2>Stupid Footer!</h2>
            </div>
        </div>
    </body>
</html>
