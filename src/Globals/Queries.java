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

    // Método de conexão
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

    // Método para executar uma procedure (insert, update, delete)
    public static void executeProcedure(String procedureName, List<SqlParameter> parameters, int timeout) {
        classReturnMessage = "OK";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Criar a chamada para a procedure usando a sintaxe CALL
            StringBuilder sql = new StringBuilder("CALL " + procedureName + "(");
            for (int i = 0; i < parameters.size(); i++) {
                sql.append("?,");
            }
            if (parameters.size() > 0) {
                sql.deleteCharAt(sql.length() - 1); // Remover última vírgula
            }
            sql.append(")");

            CallableStatement stmt = connection.prepareCall(sql.toString());

            // Definindo os parâmetros de entrada
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Configurando o tempo limite
            stmt.execute();
        } catch (SQLException ex) {
            classReturnMessage = "An error occurred while trying to access the database. Details: " + ex.getMessage();
            ex.printStackTrace();
        }
    }

    // Método para executar uma function (exclusivo para consulta de dados)
    public static List<List<Object>> fetchFunctionResults(String functionName, List<SqlParameter> parameters, int timeout) {
        List<List<Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Criar a chamada para a function
            StringBuilder query = new StringBuilder("SELECT * FROM " + functionName + "(");
            for (int i = 0; i < parameters.size(); i++) {
                query.append("?,");
            }
            if (parameters.size() > 0) {
                query.deleteCharAt(query.length() - 1); // Remover última vírgula
            }
            query.append(")");

            CallableStatement stmt = connection.prepareCall(query.toString());

            // Definindo os parâmetros de entrada
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Configurando o tempo limite

            // Executando a function
            ResultSet rs = stmt.executeQuery();

            // Processando os resultados
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

    public static List<List<Object>> fetchProcedureResults(String procedureName, List<SqlParameter> parameters, int timeout) {
        List<List<Object>> results = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Criando a chamada para a procedure
            StringBuilder query = new StringBuilder("{CALL " + procedureName + "(");
            for (int i = 0; i < parameters.size(); i++) {
                query.append("?,");
            }
            if (parameters.size() > 0) {
                query.deleteCharAt(query.length() - 1); // Remover a última vírgula
            }
            query.append(")}");

            CallableStatement stmt = connection.prepareCall(query.toString());

            // Definindo os parâmetros de entrada
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Configurando o tempo limite

            // Executando a procedure
            ResultSet rs = stmt.executeQuery();  // Aqui você executaria a procedure que retorna resultados

            // Processando os resultados
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