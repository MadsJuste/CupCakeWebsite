package entity;

public class User
{
    private int id;
    private String username;
    private String password;
    private boolean admin;

    public User()
    {
    }

    public User(String name, String password, boolean admin)
    {
        this.username = name;
        this.password = password;
        this.admin = admin;
    }

    public User(int id, String name, String password, boolean admin)
    {
        this.id = id;
        this.username = name;
        this.password = password;
        this.admin = admin;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", name=" + username + ", password=" + password + ", admin=" + admin + '}';
    }
}
