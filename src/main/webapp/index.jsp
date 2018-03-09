<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CreateSearch.css" rel="stylesheet" type="text/css"/>
        <title>CupCakeWebShop</title>

    </head>
    <body>
        
        <%@include file="includes/menu.jsp" %>
        
        

        
        <div>
        <h2>SEARCH USER</h2>
        
        <form id="formSearch" action="Control" method="post">
            <label id="labelUsername" for="username">Username</label>
            <input type="text" name="username" placeholder="Enter username..."/>
            <input type="hidden" name="origin" value="search" />
              <br>
            <input type="submit" value="SEARCH USER" />
        </form>
      
        <h2>CREATE USER</h2>
        
        <form id="formCreate" action="Control" method="post">
            <label id="labelUsername" for="username">Username</label>
            <input type="text" name="username" placeholder="Enter username..."/>
              <br>
            <label id="labelPassword" for="password">Password</label>
            <input type="text" name="password" placeholder="Enter password..."/>
              <br>
            <label id="labelAdmin" for="admin">Admin</label>
            <input type="text" name="admin" placeholder="Enter admin status..."/>
              <br>
            <input type="hidden" name="origin" value="create" />
            <input type="submit" value="CREATE USER" />
        </form>
        </div>
    </body>
</html>
