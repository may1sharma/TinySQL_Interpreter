package interpreter;

import parser.DropStatement;
import storageManager.Disk;
import storageManager.MainMemory;
import storageManager.Relation;
import storageManager.SchemaManager;

public class DropTableProc extends Procedures {
    public DropTableProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public void dropRelation(DropStatement stmt) {
        Relation relation = schema_manager.getRelation(stmt.getTableName());
        relation.deleteBlocks(0);
        System.out.println("Drop Procedure \n" + relation);
        schema_manager.deleteRelation(stmt.getTableName());
    }
}
