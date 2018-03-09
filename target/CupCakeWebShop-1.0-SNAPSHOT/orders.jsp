<%-- 
    Document   : orders
    Created on : 09-Mar-2018, 03:30:34
    Author     : Esben
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
    </head>
    <body>
        <div>
        <h1>Orders</h1>
        
        <%
            if (session.getAttribute("orders") == null){
                response.sendRedirect("ordering.jsp");
            } 
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");
            
            if(orders.size() > 0)
            {
                out.println("<p>Orders found...</p>");
                out.println("<p>");
                
                for(Order order : orders)
                {
                    out.println(order.toString() + "<br>");
                }
                
                out.println("</p>");
            }
            else
            {
                out.println("<p>No orders found matching your user ID...</p>");
            }
            
        %>
        
        <a href="ordering.jsp">BACK...</a>
        </div>
    </body>
</html>
