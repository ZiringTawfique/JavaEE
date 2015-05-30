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
        <title>My Account</title>
    </head>
    <body>        
        <div id="container">
            <div id="header">
                <h2>Welcome, <c:out value="${param.username}"/></h2>
            </div>
        
        <sql:query var="result" dataSource="jdbc/soukDatasource">
                    SELECT firstname, lastname, address, city, country, email, 
                    BALANCE FROM Account where username = '<c:out value="${param.username}"/>'
        </sql:query>
                    <h2>Your personal information</h2>
                    
                    <jsp:useBean id="userBean" scope="session" class="beans.UserBean" /> 
                    <!-- column data -->
                    <c:forEach var="row" items="${result.rowsByIndex}">
                        <c:set var="count" value="0" scope="page" />
                        <c:forEach var="column" items="${row}">
                            
                            <h3><c:out value="${result.columnNames[count]}"/>: <c:out value="${column}"/></h3>
                            <jsp:setProperty name="userBean" property="username" value="${param.username}"/>
                            <jsp:setProperty name="userBean" property="address" value="${row[2]}"/>
                            <jsp:setProperty name="userBean" property="email" value="${row[5]}"/>
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            
                        </c:forEach>
                    </c:forEach>
                       
                            
                <h3>Update your contact information here</h3>
                
                 <div id="updateinfo">

                    <h4>New contact information</h4>
                    <form action="account.jsp?username=${param.username}" method='POST'>
                      <input type="text" name="username" value="${param.username}" disabled />
                      <input type="text" name="address" placeholder="Address" />
                      <input type="email" name ="email" placeholder="E-mail" />
                      <input id="updatebutton" type="submit" value="Log in" />
                    </form>
                      
                <c:if test="${pageContext.request.method=='POST'}">
                    ${userBean.address}
                    
                    <!-- This here doesn't work for now -->
                    <sql:update dataSource="jdbc/soukDatasource">
                        UPDATE ACCOUNT SET address=?, email=? where username=?
                        <sql:param value='${userBean.address}' />
                        <sql:param value='email' />
                        <sql:param value='username' />
                    </sql:update> 
                </c:if>
</div>
        </div>
    </body>