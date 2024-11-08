import java.sql.*;
import java.util.ArrayList;
import java.util.List;;

public class Main {
    public static void main(String[] args) {
        Connection conn = Queries.connect();

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }

            List<SqlParameter> parameters = new ArrayList<>();
            parameters.add(new SqlParameter("@Name", SqlDbType.VARCHAR, "name"));
            parameters.add(new SqlParameter("@Email", SqlDbType.VARCHAR, "email"));

            Queries.executeProcedure("SP_SELECT_USER", parameters, null, 30);
        }
    }
}

