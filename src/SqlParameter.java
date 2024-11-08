import java.sql.*;

public class SqlParameter {
    private String parameterName;
    private SqlDbType sqlDbType;
    private Object value;

    public SqlParameter(String parameterName, SqlDbType sqlDbType, Object value) {
        this.parameterName = parameterName;
        this.sqlDbType = sqlDbType;
        this.value = value;
    }

    public String getParameterName() {
        return parameterName;
    }

    public SqlDbType getSqlDbType() {
        return sqlDbType;
    }

    public Object getValue() {
        return value;
    }
}

enum SqlDbType {
    VARCHAR, INTEGER, DATETIME, BOOLEAN;

    // Para converter SqlDbType para o tipo JDBC
    public int getJdbcType() {
        switch (this) {
            case VARCHAR:
                return java.sql.Types.VARCHAR;
            case INTEGER:
                return java.sql.Types.INTEGER;
            case DATETIME:
                return java.sql.Types.TIMESTAMP;
            case BOOLEAN:
                return java.sql.Types.BOOLEAN;
            default:
                return java.sql.Types.OTHER;
        }
    }
}
