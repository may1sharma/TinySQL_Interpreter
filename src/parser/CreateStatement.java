package parser;

import java.util.LinkedHashMap;

public class CreateStatement implements Statement {
    @Override
    public StatementType getType() {
        return StatementType.CREATE;
    }

    String tableName;
    // LinkedHashMap of Attribute name and Data StatementType
    LinkedHashMap<String,String> attributes = new LinkedHashMap<>();

    public CreateStatement(String tableName, LinkedHashMap<String, String> attributes) {
        this.tableName = tableName;
        this.attributes = attributes;
    }

    public CreateStatement(String tableName) {
        this.tableName = tableName;
    }

    public CreateStatement() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LinkedHashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkedHashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "CreateStatement{" +
                "tableName='" + tableName + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
