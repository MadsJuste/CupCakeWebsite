<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.security.MessageDigest"%>
<%@ page import ="java.sql.*" %>
<%
    String uname = request.getParameter("uname");
    String pass = request.getParameter("pass"); // + "InUtQcbgAWZomA";
    
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(pass.getBytes());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < hash.length; i++) {
        sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
    }
    pass = sb.toString();
    
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://159.89.99.105/CupCakeDB2",
            "Juste", "admin");
    
    String sql = "INSERT INTO users (username, password, admin) VALUES (?, ?, 0)";
    PreparedStatement stmt = con.prepareStatement(sql);
    stmt.setString(1, uname);
    stmt.setString(2, pass);
    if (stmt.execute()) {
        response.sendRedirect("welcome.jsp");
    } else {
        response.sendRedirect("index.jsp");
    }
%>
