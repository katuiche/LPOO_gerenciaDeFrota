package trabalho.locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException{
        String dbDriver= "com.mysql.jdbc.Driver";
        String dbUrl= "jdbc:mysql://localhost/sist";
        String dbUser= "root";
        String dbPwd= "";
        return DriverManager.getConnection(dbUrl, dbUser, dbPwd);

    }
}
