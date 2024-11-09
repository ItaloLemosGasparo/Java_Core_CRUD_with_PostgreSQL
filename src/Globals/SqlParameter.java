package Globals;

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