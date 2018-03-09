package datamapper;

import entity.User;
import java.util.ArrayList;
import dbconnector.DBConnector;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapper implements DataMapperInterface
{
    private DBConnector dbc = new DBConnector();

    public DataMapper(DataSource ds)
    {
        dbc.setDataSource(ds);
    }

   
    @Override
    public ArrayList<User> getUsers()
    {
        ArrayList<User> users = new ArrayList();

        try
        {
            dbc.open();

            String sql = "select * from users";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                User u = new User(userid, username, userpassword, admin);

                users.add(u);
            }

            dbc.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public ArrayList<User> getUsers(String name)
    {
        ArrayList<User> users = new ArrayList();

        try
        {
            dbc.open();

            String sql = "select * from users where username like ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                User u = new User(userid, username, userpassword, admin);

                users.add(u);
            }

            dbc.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User getUser(int id)
    {
        User u = null;
        
        try
        {
            dbc.open();

            String sql = "select * from users where user_id = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                u = new User(userid, username, userpassword, admin);
            }

            dbc.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public User getUser(String name)
    {
        try
        {
            dbc.open();

            String sql = "select * from users where username = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int userid = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String userpassword = resultSet.getString("password");
                boolean admin = resultSet.getBoolean("admin");

                return new User(userid, username, userpassword, admin);
            }

            dbc.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteUser(int id)
    {
        try
        {
            dbc.open();

            String sql = "delete from users where user_id = ?;";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUser(User u)
    {
        try
        {
            dbc.open();

            String sql = "update users set "
                    + "username = ?, "
                    + "password = ?, "
                    + "admin = ? "
                    + "where user_id = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setBoolean(3, u.isAdmin());
            preparedStatement.setInt(4, u.getId());
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createUser(User u)
    {
        try
        {
            dbc.open();

            String sql = "insert into users values(null, ?, ?, ?)";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, u.getUsername());
            preparedStatement.setString(2, u.getPassword());
            preparedStatement.setBoolean(3, u.isAdmin());
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
    
    public User validateUser(String username, String password)
    {
        User user = null;
        
        try
        {
            dbc.open();
            
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next())
            {
                int id = resultSet.getInt("user_id");
                boolean admin = resultSet.getInt("admin") > 0;
                
                user = new User(id, username, password, admin);
            }

            dbc.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return user;
    }
    
    public boolean createOrder(Order o)
    {
        try
        {
            dbc.open();

            String sql = "insert into orderItem values(?, ?, ?)";
            PreparedStatement preparedStatement = dbc.preparedStatement(sql);
            preparedStatement.setInt(1, o.getBot());
            preparedStatement.setInt(2, o.getTop());
            preparedStatement.setInt(3, o.getAmount());
            preparedStatement.executeUpdate();

            dbc.close();

            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
    
}