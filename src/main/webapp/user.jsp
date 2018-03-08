<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="user.css" rel="stylesheet" type="text/css"/>
        <title>WebProject - User</title>
    </head>
    <body>
        <div>
        <h1>USER</h1>
            
        <%
            User user = (User) session.getAttribute("user");
            
            if (user != null)
            {
                out.print("Login worked");
            }
            else
            {
                out.print("Login failed");
                
                user = new User(0, "Anonymous", "none", false);
            }
        %>

        <p>
            Logged in as: <%= user.getUsername() %><br>
            Id: <%= user.getId() %><br>
            Password: <%= user.getPassword() %><br>
            Admin: <%= user.isAdmin() %><br>
        </p>
        
        <h2>UPDATE USER</h2>
        
        <form action="Control" method="post">
            <input type="text" name="username" placeholder="Username" />
            <input type="text" name="password" placeholder="Password" />
            <input type="text" name="admin" placeholder="Admin" />            
            <input type="hidden" name="origin" value="update" />
            <input type="submit" value="UPDATE USER" />
        </form>
        
        <h2>DELETE USER</h2>
        
        <form action="Control" method="post">
            <input type="hidden" name="origin" value="delete" />
            <input type="submit" value="DELETE USER" />
        </form>
        
        <a href="index.jsp">BACK...</a>
        </div>
        
    </body>
</html>
