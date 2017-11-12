package parser;

import java.util.HashMap;

public class CreateStatement implements Statement {
    @Override
    public StatementType getType() {
        return StatementType.CREATE;
    }

    String tableName;
    // HashMap of Attribute name and Data StatementType
    HashMap<String,String> attributes = new HashMap<>();

    public CreateStatement(String tableName, HashMap<String, String> attributes) {
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

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
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
