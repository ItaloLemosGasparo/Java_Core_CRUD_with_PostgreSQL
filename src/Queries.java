import java.sql.*;
import java.util.List;

public class Queries {
    private static final String URL = "jdbc:postgresql://isabelle.db.elephantsql.com/dlhgjlcw";
    private static final String USER = "dlhgjlcw";
    private static final String PASSWORD = "y015XCmBptri_hY2l3ZFnVeT8CRfVC68";

    public static  String classReturnMessage;
    public static String getClassReturnMessage() {
        return classReturnMessage;
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static int executeProcedure(String procedureName, List<SqlParameter> parameters, SqlParameter idOutput, int timeout) {
        int id = 0;
        classReturnMessage = "OK";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            CallableStatement stmt = connection.prepareCall("{ CALL " + procedureName + "(" + "?".repeat(parameters.size()).replaceAll("(.{1})", "$0,").replaceAll(",$", "") + ")}");

            // Definir os parâmetros de entrada
            for (int i = 0; i < parameters.size(); i++) {
                SqlParameter param = parameters.get(i);
                stmt.setObject(i + 1, param.getValue(), param.getSqlDbType().getJdbcType());
            }

            // Se existir um parâmetro de saída, adicioná-lo
            if (idOutput != null) {
                stmt.registerOutParameter(idOutput.getParameterName(), idOutput.getSqlDbType().getJdbcType());
            }

            stmt.setQueryTimeout(timeout); // Configura o tempo de execução

            // Executar a procedure
            stmt.execute();

            // Se houver um parâmetro de saída, obter o valor
            if (idOutput != null) {
                id = stmt.getInt(idOutput.getParameterName());
            }
        } catch (SQLException ex) {
            classReturnMessage = "An error occurred while trying to access the database. Details: " + ex.getMessage();
            ex.printStackTrace();
        }

        return id;
    }
}
