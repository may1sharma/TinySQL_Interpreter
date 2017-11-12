package parser;

import java.util.ArrayList;
import java.util.List;

public class SelectStatement implements Statement {
    @Override
    public StatementType getType() {
        return StatementType.SELECT;
    }

    boolean hasDistinct = false;
    List<String> columns;
    List<String> tables;
    List<String> condition;
    String orderColumn;

    public SelectStatement(List<String> columns, List<String> tables) {
        this.columns = columns;
        this.tables = tables;
    }

    public SelectStatement(Object distinct, List<String> columns, List<String> tables, List<String> condition,
                           String orderColumn) {
        this.hasDistinct = distinct!=null;
        this.columns = columns;
        this.tables = tables;
        this.condition = condition;
        if (!orderColumn.isEmpty()) {this.orderColumn = orderColumn;}
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public boolean isHasDistinct() {
        return hasDistinct;
    }

    public void setHasDistinct(boolean hasDistinct) {
        this.hasDistinct = hasDistinct;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public List<String> getCondition() {
        return condition;
    }

    public void setCondition(List<String> condition) {
        this.condition = condition;
    }
}
