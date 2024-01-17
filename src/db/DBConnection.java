package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Created By Ravindu Prathibha
 * @created 1/14/2024 - 12:21 PM
 * @project Thogakade
 */
public class DBConnection {
    private static DBConnection dbConnection=null;
    private Connection connection;
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Thogakade",
                "root",
                "19990202Ravi@:&pra"
        );
    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection==null){
            dbConnection=new DBConnection();
        }
        return dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }

}
