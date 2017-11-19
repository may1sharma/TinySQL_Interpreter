package parser;

public interface Statement {

    enum StatementType {
        CREATE,
        DROP,
        SELECT,
        INSERT,
        DELETE
    }

    StatementType getType();
}

