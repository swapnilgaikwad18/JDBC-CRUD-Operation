package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static Connection cn;

    public static Connection openConnection() throws SQLException{
        if(cn==null){
            String url = "jdbc:mysql://localhost:3306/company";
            cn = DriverManager.getConnection(url,"root","Pass@123");
        }
        return cn;
    }
}
