package vn.codegym.tcomplex.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection() {

        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data_complex?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                    "root",
                    "123456");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return conn;
    }
}
