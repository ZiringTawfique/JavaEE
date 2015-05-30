<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <title>Souk MVC: Online Shop - Log in</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <h2>Welcome Random User</h2>
            </div>
 <div id="login">

  <h1>Log in</h1>
  <form action="loginServlet" method="POST">
    <input type="hidden" name="submit" value='true'/>
    <input type="text" name="username" placeholder="Username" />
    <input type="password" name ="password" placeholder="Password" />
    <input id="loginbutton" type="submit" value="Log in"/>
  </form>
  
  <c:if test="${param.submit}"> 
      
  <c:if test="${empty param.username || empty param.password}">
        <p>Please enter your username and password</p>
  </c:if>
      
  </c:if>
  
  <c:if test="${invalidLogin == true && not empty param.username && not empty param.password}">
      <p>Sorry, the account you have provided does not exists</p>        
  </c:if>
  
  
</div>
            <div id="footer">
                <h2>Stupid Footer!</h2>
            </div>
        </div>
    </body>
</html>
