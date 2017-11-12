package parser;

import java.util.List;

public class InsertStatement implements Statement {
    @Override
    public StatementType getType() {
        return StatementType.INSERT;
    }

    String tableName;
    List<String> attributes;
    List<String> values;
    SelectStatement selectStatement;

    public InsertStatement(String tableName, List<String> attributes, List<String> values) {
        this.tableName = tableName;
        this.attributes = attributes;
        this.values = values;
    }

    public InsertStatement(String tableName, List<String> attributes, SelectStatement selectStatement) {
        this.tableName = tableName;
        this.attributes = attributes;
        this.selectStatement = selectStatement;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public SelectStatement getSelectStatement() {
        return selectStatement;
    }

    public void setSelectStatement(SelectStatement selectStatement) {
        this.selectStatement = selectStatement;
    }

    @Override
    public String toString() {
        return "InsertStatement{" +
                "tableName='" + tableName + '\'' +
                ", attributes=" + attributes +
                ", values=" + values +
                ", selectStatement=" + selectStatement +
                '}';
    }
}
