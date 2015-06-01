
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
        <link href="CSS/productpage.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
          <div id="container">
            <div id="header">
                <h2>Welcome, <c:out value="${userBean.firstname}"/>!</h2>
              
                <form action="historyServlet" method="POST">
                    <input name="productButton" type="submit" value="Products" />
                    <input name="historyButton" type="submit" value="Past Orders" />
                    <input name="accountButton" type="submit" value="Account" />
                    <input name="cartButton" type ="submit" value="Go to Cart" />
                    <input name="logoutButton" type="submit" value="Log out"/>
                </form> 
            </div>
                
               
            <div id="content">
              <c:forEach var="item" items="${productBean.productList.toArray()}">
                <div id="productItems" class="ItemBox">
                    
                    <img src="img/${item.getID()}.jpg" alt="img/1.jpg"/>
                    <h4>${item.getName()}</h4>
                   <h4>Price: ${item.getPrice()}</h4>
                    <h4>Category: ${item.getCategory()}</h4>
                    <p>${item.getDescription()}</p>
                    
                    <form action="historyServlet" method="POST">                   
                  <input id="quantity" name="quantity" type="text" placeholder="Quantity" value="1"/>
                  <input id="buyButton" name="buyButton" type="submit" value="Add to cart"/>
                    <input name="selectedProductId" id="selectedProductId" type="hidden" value="${item.getID()}"/>
                    </form>
                </div>
                </c:forEach>
                
               
            </div>
            <c:if test="${itemBought}">
            <script type="text/javascript">alert('Your product has been added to the shopping basket');</script>
                </c:if>
             <c:if test="${itemNOTBought}">
            <script type="text/javascript">alert('Your product has NOT been added ');</script>
                </c:if>
            <div id="footer">
                <h2>Random Online Store</h2>
                
                
            </div>
        </div>
    </body>
</html>
