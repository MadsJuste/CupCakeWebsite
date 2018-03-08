<%@page import="java.util.ArrayList"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="user.css" rel="stylesheet" type="text/css"/>
        <title>WebProject - Users</title>
    </head>
    <body>
        <div>
        <h1>USERS</h1>
        
        <%
            ArrayList<User> users = (ArrayList<User>) session.getAttribute("users");
            
            if(users.size() > 0)
            {
                out.println("<p>Users found...</p>");
                out.println("<p>");
                
                for(User u : users)
                {
                    out.println(u.getUsername() + "<br>");
                }
                
                out.println("</p>");
            }
            else
            {
                out.println("<p>No users found...</p>");
            }
            
        %>
        
        <a href="index.jsp">BACK...</a>
        </div>
    </body>
</html>
