package Globals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    private static final String URL = "jdbc:postgresql://kesavan.db.elephantsql.com/khcnvpuu";
    private static final String USER = "khcnvpuu";
    private static final String PASSWORD = "1wtYbwBs5b_vG71JYo6dS2jBBQDvhu9t";

    public static String classReturnMessage;

    public static String getClassReturnMessage() {
        return classReturnMessage;
    }

    // Connects to the PostgreSQL database and returns the connection.
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

    // Executes a stored procedure (for insert, update, or delete operations).
    public static void executeProcedure(String procedureName, List<SqlParameter> parameters, int timeout) {
        classReturnMessage = "OK";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Building the Procedure call
            String query = ProcedureCallBuilder(true, procedureName, parameters);

            CallableStatement stmt = connection.prepareCall(query);

            // Sets the input parameters in the CallableStatement.
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout);
            stmt.execute();
        } catch (SQLException ex) {
            classReturnMessage = "An error occurred while trying to access the database. Details: " + ex.getMessage();
        }
    }

    // Executes a stored procedure and retrieves the results as a list of lists.
    public static List<List<Object>> fetchProcedureResults(String procedureName, List<SqlParameter> parameters, int timeout) {
        classReturnMessage = "OK";
        List<List<Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Building the Procedure call
            String query = ProcedureCallBuilder(true, procedureName, parameters);

            CallableStatement stmt = connection.prepareCall(query);

            // Sets the input parameters in the CallableStatement.
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout);

            // Executes the stored procedure and retrieves the result set.
            ResultSet rs = stmt.executeQuery();

            // Processes the result set row by row.
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                int columnCount = rs.getMetaData().getColumnCount();

                // Adds each column value to the current row.
                for (int i = 1; i <= columnCount; i++)
                    row.add(rs.getObject(i));

                results.add(row);
            }
            rs.close();
        } catch (SQLException ex) {
            classReturnMessage = "Error fetching function results: " + ex.getMessage();
        }

        return results;
    }

    // Executes a function and retrieves the results as a list of lists (for data retrieval only).
    public static List<List<Object>> fetchFunctionResults(String functionName, List<SqlParameter> parameters, int timeout) {
        classReturnMessage = "OK";
        List<List<Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Building the Function call
            String query = ProcedureCallBuilder(false, functionName, parameters);

            CallableStatement stmt = connection.prepareCall(query);

            // Sets the input parameters in the CallableStatement.
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout);
            ResultSet rs = stmt.executeQuery();

            // Processes the result set row by row.
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                int columnCount = rs.getMetaData().getColumnCount();

                // Adds each column value to the current row.
                for (int i = 1; i <= columnCount; i++)
                    row.add(rs.getObject(i));

                results.add(row);
            }

            rs.close();
        } catch (SQLException ex) {
            classReturnMessage = "Error fetching function results: " + ex.getMessage();
        }

        return results;
    }

    // Builds the SQL call statement for either a stored procedure or function, including parameters.
    private static String ProcedureCallBuilder(boolean procedure, String name, List<SqlParameter> parameters) {
        StringBuilder query = procedure ? new StringBuilder("{CALL " + name + "(") : new StringBuilder("SELECT * FROM " + name + "(");

        // Appends placeholders for each parameter.
        for (int i = 0; i < parameters.size(); i++)
            query.append("?,");

        // Removes the trailing comma if parameters are present.
        if (parameters.size() > 0)
            query.deleteCharAt(query.length() - 1);

        // Closes the call statement.
        query.append(procedure ? ")}" : ")");

        return query.toString();
    }
}