package datasource;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSource{
    private MysqlDataSource dataSource = new MysqlDataSource();
    
    public DataSource()
    {
        dataSource.setServerName("159.89.99.105");
        dataSource.setDatabaseName("CupCakeDB2");
        dataSource.setUser("Juste");
        dataSource.setPassword("admin");
    }
    
    public MysqlDataSource getDataSource()
    {
        return dataSource;
    }
}
