package servlet;

import datamapper.DataMapper;
import datasource.DataSource;
import entity.User;
import entity.Order;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The one and only servlet in this project. This takes care of any input from
 * forms and any other actions which might connect the frontend and backend
 * portions of the assignment.
 *
 * @author Temporalis
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class Control extends HttpServlet {

    DataMapper dm;

    /**
     * Default constructor. It creates a dataMapper object with the datasource
     * given from the DataSource class.
     */
    public Control() {
        dm = new DataMapper(new DataSource().getDataSource());
    }

    /**
     * This method takes in the data submitted in the form. It looks at the
     * hidden field origin which we have in all of the forms and everything that
     * interacts with the servlet. The information from the origin parameter
     * then goes through a switch and depending on the origin appropriate
     * actions will be taken by the servlet.
     *
     * @param request this is the HttpServletRequest from the form
     * @param response this is the HttpServletResponse from the form
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        switch (request.getParameter("origin")) {
            case "login": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                User user = dm.validateUser(username, password);

                request.getSession().setAttribute("user", user);

                response.sendRedirect("user.jsp");

            }
            break;
            case "search": {
                String username = request.getParameter("username");

                request.getSession().setAttribute("users", dm.getUsers(username));

                response.sendRedirect("users.jsp");

            }
            break;
            case "create": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

                dm.createUser(new User(username, password, admin));

                response.sendRedirect("usercreated.jsp");

            }
            break;
            case "update": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                boolean admin = Boolean.parseBoolean(request.getParameter("admin"));

                User user = (User) request.getSession().getAttribute("user");

                if (user != null) {
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setAdmin(admin);

                    dm.updateUser(user);

                    response.sendRedirect("userupdated.jsp");
                }

            }
            break;
            case "delete": {
                User user = (User) request.getSession().getAttribute("user");

                if (user != null) {
                    dm.deleteUser(user.getId());

                    response.sendRedirect("userdeleted.jsp");
                }

            }
            break;
            case "placeOrder": {
                int bot = Integer.parseInt(request.getParameter("bottom"));
                int top = Integer.parseInt(request.getParameter("topping"));
                int amount = Integer.parseInt(request.getParameter("amount"));

                dm.createOrder(new Order(bot, top, amount), (User) request.getSession().getAttribute("user"));

                response.sendRedirect("ordered.jsp");
            }
            break;
            case "seeOrders": {

                ArrayList<Order> orders = dm.getOrders((User) request.getSession().getAttribute("user"));

                request.getSession().setAttribute("users", dm.getOrders((User) request.getSession().getAttribute("user")));

                response.sendRedirect("orders.jsp");
            }
            break;
            case "order": {
                response.sendRedirect("ordering.jsp");
            }
            break;
        }
    }

    /**
     * When the servlet gets a get request from a form this is the action that
     * will be taken. All it does is that it sends the request and response to
     * processRequest.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * When the servlet gets a post request from a form this is the action that
     * will be taken. All it does is that it sends the request and response to
     * processRequest.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet and what it is capable of.
     * 
     * @return the servlet info.
     * @deprecated 
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
