package parser;

public class DropStatement implements Statement {
    @Override
    public StatementType getType() {
        return StatementType.DROP;
    }

    String tableName;

    public DropStatement(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "DropStatement{" +
                "tableName='" + tableName + '\'' +
                '}';
    }
}
