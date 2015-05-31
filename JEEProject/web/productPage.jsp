
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : productPage
    Created on : May 20, 2015, 10:05:33 PM
    Author     : ZiringTawfique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/main.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
          <div id="container">
            <div id="header">
                <h2>Welcome Random User</h2>
                <form action="historyServlet" method="POST">
                    <input name="productButton" type="submit" value="Products" />
                    <input name="historyButton" type="submit" value="Past Orders" />
                    <input name="accountButton" type="submit" value="Account" />
                </form> 
            </div>
            <div id="content">
                <sql:query var="result" dataSource="jdbc/soukDatasource">
                    SELECT * FROM Product
                </sql:query>
    
                <table border="1">
                    <!-- column headers -->
                    <tr>
                    <c:forEach var="columnName" items="${result.columnNames}">
                        <th><c:out value="${columnName}"/></th>
                       
                    </c:forEach>
                        <th>Add to Cart</th>
                        
                   </tr>
                
                    <!-- column data -->
                    <c:forEach var="row" items="${result.rowsByIndex}">
                        <tr>
                        <c:forEach var="column" items="${row}">
                            <td><c:out value="${column}"/></td>
                            
                        </c:forEach>
                            <td><input id="buyButton" type="submit" value="Buy"/></td>
                        </tr>
                    </c:forEach>
                      
                </table>
                
            </div>
            <div id="footer">
                <h2>Random Online Store</h2>
            </div>
        </div>
    </body>
</html>
