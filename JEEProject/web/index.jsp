<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <input type="email" name="username" placeholder="Email" />
    <input type="password" name ="password" placeholder="Password" />
    <input id="loginbutton" type="submit" value="Log in" />
  </form>
</div>
            <div id="footer">
                <h2>Stupid Footer!</h2>
            </div>
        </div>
    </body>
</html>
