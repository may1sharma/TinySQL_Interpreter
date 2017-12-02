package parser;

import java.util.List;

public class DeleteStatement implements Statement {
    @Override
    public StatementType getType() {
        return StatementType.DELETE;
    }

    String tableName;
    List<String> condition;

    public DeleteStatement(String tableName, List<String> condition) {
        this.tableName = tableName;
        this.condition = condition;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void setCondition(List<String> condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Delete Statement :" + "\n" +
                "tableName = '" + tableName + '\'' + "\n" +
                "condition = " + condition + "\n" ;
    }
}
