package Globals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static String classReturnMessage;

    // Returns the class return message (success or error)
    public static String getClassReturnMessage() {
        return classReturnMessage;
    }

    // Method to connect to the PostgreSQL database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return conn;
    }

    // Method to execute a stored procedure (insert, update, delete)
    public static void executeProcedure(String procedureName, List<SqlParameter> parameters, int timeout) {
        classReturnMessage = "OK";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Build the call string for the procedure
            StringBuilder sql = new StringBuilder("CALL " + procedureName + "(");
            for (int i = 0; i < parameters.size(); i++)
                sql.append("?,");

            if (parameters.size() > 0)
                sql.deleteCharAt(sql.length() - 1); // Remove the last comma

            sql.append(")");

            CallableStatement stmt = connection.prepareCall(sql.toString());

            // Set the input parameters for the procedure
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Set timeout
            stmt.execute(); // Execute the procedure
        } catch (SQLException ex) {
            classReturnMessage = "An error occurred while trying to access the database. Details: " + ex.getMessage();
            ex.printStackTrace();
        }
    }

    // Method to execute a function (for data retrieval only)
    public static List<List<Object>> fetchFunctionResults(String functionName, List<SqlParameter> parameters, int timeout) {
        List<List<Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Build the call string for the function
            StringBuilder query = new StringBuilder("SELECT * FROM " + functionName + "(");
            for (int i = 0; i < parameters.size(); i++)
                query.append("?,");

            if (parameters.size() > 0)
                query.deleteCharAt(query.length() - 1); // Remove the last comma

            query.append(")");

            CallableStatement stmt = connection.prepareCall(query.toString());

            // Set the input parameters for the function
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Set timeout

            // Execute the function
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                results.add(row);
            }
            rs.close();
        } catch (SQLException ex) {
            classReturnMessage = "Error fetching function results: " + ex.getMessage();
            ex.printStackTrace();
        }

        return results;
    }

    // Method to execute a stored procedure that returns results (for data retrieval)
    public static List<List<Object>> fetchProcedureResults(String procedureName, List<SqlParameter> parameters, int timeout) {
        List<List<Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Build the call string for the procedure
            StringBuilder query = new StringBuilder("{CALL " + procedureName + "(");
            for (int i = 0; i < parameters.size(); i++)
                query.append("?,");

            if (parameters.size() > 0)
                query.deleteCharAt(query.length() - 1); // Remove the last comma

            query.append(")}");

            CallableStatement stmt = connection.prepareCall(query.toString());

            // Set the input parameters for the procedure
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Set timeout
            // Execute the procedure and fetch results
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getObject(i));
                }
                results.add(row);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error fetching procedure results: " + ex.getMessage());
        }

        return results;
    }
}
