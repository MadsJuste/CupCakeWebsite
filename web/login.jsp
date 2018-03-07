<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.security.MessageDigest"%>
<%@ page import ="java.sql.*" %>
<%
    String userid = request.getParameter("uname");
    String pass = request.getParameter("pass"); // + "InUtQcbgAWZomA";
    
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < hash.length; i++) {
        sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
    }
    pass = sb.toString();
    //hej mads
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://159.89.99.105/CupCakeDB2",
            "Juste", "admin");
    ResultSet rs;
    String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";

    PreparedStatement stmt = con.prepareStatement(sql);
    stmt.setString(1, userid);
    stmt.setString(2, pass);
    rs = stmt.executeQuery();
    if (rs.next()) {
        session.setAttribute("userid", userid);
        String sql2 = "INSERT INTO orders (user_id, price) VALUES ((SELECT user_id FROM users WHERE username = '"+session.getAttribute("userid") +"'), 0);";
        stmt = con.prepareStatement(sql2);
        stmt.execute();
        response.sendRedirect("CupCakeChoice.jsp");
    } else {
        out.println("Invalid password <a href='index.jsp'>try again</a>");
    }
%>
