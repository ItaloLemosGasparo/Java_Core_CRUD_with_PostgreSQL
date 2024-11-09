package Globals;

public enum SqlDbType {
    VARCHAR, INTEGER, DATETIME, BOOLEAN, DECIMAL;

    // Para converter Globals.SqlDbType para o tipo JDBC
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
            case DECIMAL:
                return java.sql.Types.DECIMAL;
            default:
                return java.sql.Types.OTHER;
        }
    }
}
