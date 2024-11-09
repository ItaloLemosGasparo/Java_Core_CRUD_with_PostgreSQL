package Globals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    private static final String URL = "jdbc:postgresql://kesavan.db.elephantsql.com/khcnvpuu";
    private static final String USER = "khcnvpuu";
    private static final String PASSWORD = "1wtYbwBs5b_vG71JYo6dS2jBBQDvhu9t";
    public static String classReturnMessage;

    // Retrieves the last returned message (success or error)
    public static String getClassReturnMessage() {
        return classReturnMessage;
    }

    // Establishes a connection to the PostgreSQL database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Executes a stored procedure that modifies the database (e.g., insert, update, delete)
    public static void executeProcedure(String procedureName, List<SqlParameter> parameters, int timeout) {
        classReturnMessage = "OK";
        String sql = buildProcedureSql(procedureName, parameters);

        try (Connection connection = getConnection();
             CallableStatement stmt = connection.prepareCall(sql)) {

            setParameters(stmt, parameters);
            stmt.setQueryTimeout(timeout); // Set query timeout
            stmt.execute(); // Execute the procedure
        } catch (SQLException ex) {
            classReturnMessage = "An error occurred while trying to access the database. Details: " + ex.getMessage();
        }
    }

    // Executes a function to retrieve data from the database
    public static List<List<Object>> fetchFunctionResults(String functionName, List<SqlParameter> parameters, int timeout) {
        return fetchResults("SELECT * FROM " + functionName, parameters, timeout);
    }

    // Executes a stored procedure that returns results (e.g., for data retrieval)
    public static List<List<Object>> fetchProcedureResults(String procedureName, List<SqlParameter> parameters, int timeout) {
        return fetchResults("{CALL " + procedureName, parameters, timeout);
    }

    // Common method to execute either functions or procedures and fetch results
    private static List<List<Object>> fetchResults(String baseQuery, List<SqlParameter> parameters, int timeout) {
        List<List<Object>> results = new ArrayList<>();
        String sql = buildProcedureSql(baseQuery, parameters);

        try (Connection connection = getConnection();
             CallableStatement stmt = connection.prepareCall(sql)) {

            setParameters(stmt, parameters); // Set the parameters for the statement
            stmt.setQueryTimeout(timeout); // Set query timeout

            try (ResultSet rs = stmt.executeQuery()) {
                // Process the results from the query
                while (rs.next()) {
                    List<Object> row = new ArrayList<>();

                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++)
                        row.add(rs.getObject(i)); // Collect each column value

                    results.add(row);
                }
            }
        } catch (SQLException ex) {
            classReturnMessage = "Error fetching results: " + ex.getMessage();
        }

        return results;
    }

    // Helper method to construct the SQL query string for stored procedures or functions
    private static String buildProcedureSql(String baseQuery, List<SqlParameter> parameters) {
        StringBuilder sql = new StringBuilder(baseQuery + "(");
        sql.append("?,".repeat(parameters.size())); // Add placeholders for each parameter

        if (!parameters.isEmpty())
            sql.deleteCharAt(sql.length() - 1); // Remove the last comma

        sql.append(")");
        return sql.toString();
    }

    // Helper method to set the parameters for a CallableStatement
    private static void setParameters(CallableStatement stmt, List<SqlParameter> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) {
            SqlParameter param = parameters.get(i);
            stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType()); // Set each parameter value
        }
    }
}
