package util;

import java.sql.*;

public class Database {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/schoolmanagementsystem";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD= "4844";

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    public Statement getSQLStatement() throws SQLException {
        return this.getDatabaseConnection().createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return this.getDatabaseConnection().prepareStatement(sql);
    }
}
