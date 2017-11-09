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

    public SelectStatement(List<String> columns, List<String> tables) {
        this.columns = columns;
        this.tables = tables;
    }

    public SelectStatement(Object distinct, List<String> columns, List<String> tables) {
        this.hasDistinct = distinct!=null;
        this.columns = columns;
        this.tables = tables;
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
}
