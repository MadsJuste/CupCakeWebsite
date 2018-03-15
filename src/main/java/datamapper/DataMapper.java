package datamapper;

import entity.User;
import entity.Order;
import java.util.ArrayList;
import dbconnector.DBConnector;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used to both write to the database and get data from the database and
 * write to objects
 *
 * @author Temporalis
 */
public class DataMapper implements DataMapperInterface {

    private DBConnector dbc = new DBConnector();

    /**
     * basic constructor given a dataSource so it is able to connect to the
     * database which is specified.
     *
     * @param ds the DataSource object, which contains information on the
     * database
     */
    public DataMapper(DataSource ds) {
        dbc.setDataSource(ds);
    }

    /**
     * A method used to get all users stored in the database and return them as
     * an ArrayList of User objects
     *
     * @return an ArrayList with all User objects in the database
     */
    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList();

        try {
            dbc.open();

            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                User u = new User(userid, username, userpassword, admin);

                users.add(u);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * A method that returns an ArrayList with all users that contains the
     * specified input as user objects
     *
     * @param input is the input which the method sends to the database to check
     * for mathces
     * @return an ArrayList of user objects with a username which contains the
     * input.
     */
    @Override
    public ArrayList<User> getUsers(String input) {
        ArrayList<User> users = new ArrayList();

        try {
            dbc.open();

            String sql = "SELECT * FROM users WHERE username LIKE ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, "%" + input + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                User u = new User(userid, username, userpassword, admin);

                users.add(u);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * A method used to get a single user with the id matching the int input
     *
     * @param id is the input int which gets compared to the users in the
     * database
     * @return a single user object matching the info from the database. If
     * there is none the user will be a null
     */
    @Override
    public User getUser(int id) {
        User u = null;

        try {
            dbc.open();

            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                u = new User(userid, username, userpassword, admin);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    /**
     * A method used to get a single user with the username matching the name
     * input
     *
     * @param name is the input String which gets compared to the users in the
     * database
     * @return a single user object matching the info from the database. If
     * there is none the user will be a null
     */
    @Override
    public User getUser(String name) {
        try {
            dbc.open();

            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                return new User(userid, username, userpassword, admin);
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * A method that deletes a user from the database with an id matching the
     * input
     *
     * @param id is the input int, which gets compared to the users in the
     * database
     * @return a boolean if the user has been successfully deleted from the
     * database
     */
    @Override
    public boolean deleteUser(int id) {
        try {
            dbc.open();

            String sql = "DELETE FROM users WHERE user_id = ?;";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * A method that updates a user in the database to match the user object
     * parameter
     *
     * @param u is the input user object from which the updated information is
     * gotten from
     * @return a boolean true if the update was a success and a false if it
     * failed.
     */
    @Override
    public boolean updateUser(User u) {
        try {
            dbc.open();

            String sql = "UPDATE users SET username = ?, password = ?, admin = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setBoolean(3, u.isAdmin());
            preparedStatement.setInt(4, u.getId());
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * A method which adds a user matching the User object parameter to the
     * database
     *
     * @param u is the User object from which information added to the database
     * is received
     * @return a boolean true if the update was a success and a false if it
     * failed.
     */
    @Override
    public boolean createUser(User u) {
        try {
            dbc.open();

            String sql = "INSERT INTO users VALUES(null, ?, ?, ?)";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setBoolean(3, u.isAdmin());
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * A method which makes a simple check to find a user where both the
     * username and password parameters match both the username and password
     * fields stored in the database.
     *
     * @param username is the username which gets compared to the database
     * @param password is the password which gets compared to the database
     * @return a single user object on which both matches. If none exists then a
     * null is returned instead.
     */
    public User validateUser(String username, String password) {
        User user = null;

        try {
            dbc.open();

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                boolean admin = resultSet.getInt("admin") > 0;

                user = new User(id, username, password, admin);
            }

            dbc.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    /**
     * This method creates an order in the database and links it to the user
     * which placed the order
     *
     * @param o is the order object which contains information of bottom,
     * topping and amount
     * @param user is the user object with information on the user which has
     * placed this the order o.
     * @return a boolean true if the update was a success and a false if it
     * failed.
     */
    public boolean createOrder(Order o, User user) {
        try {
            dbc.open();

            String sql = "INSERT INTO orderItem (bottom_id, topping_id, amount) VALUES (?, ?, ?) OUTPUT SCOPE_IDENTITY()";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, o.getBot());
            preparedStatement.setInt(2, o.getTop());
            preparedStatement.setInt(3, o.getAmount());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                sql = "INSERT INTO orders (item_id, user_id) VALUES (?, ?)";
                preparedStatement = dbc.preparedStatement(sql);
                preparedStatement.setInt(1, resultSet.getInt("item_id"));
                preparedStatement.setInt(2, user.getId());
                preparedStatement.executeUpdate();
            }

            dbc.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * A method which returns an ArrayList containing all orders placed by a
     * specific user matching the user parameter
     *
     * @param user is the input user which is looked up in the database
     * @return an ArrayList of order objects which contains all orders. If the
     * ArrayList is empty no orders were found made by the customer
     */
    public ArrayList<Order> getOrders(User user) {
        ArrayList<Order> orders = new ArrayList();

        try {
            dbc.open();

            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet rs;
            String sql2 = "SELECT * FROM order_item WHERE item_id = ?";
            PreparedStatement stmt = dbc.preparedStatement(sql2);

            while (resultSet.next()) {
                stmt.setInt(1, resultSet.getInt("item_id"));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    int bot = rs.getInt("bottom_id");
                    int top = rs.getInt("topping_id");
                    int amount = rs.getInt("amount");

                    Order order = new Order(bot, top, amount);

                    orders.add(order);
                }
            }

            dbc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

}
