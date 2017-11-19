package interpreter;

import parser.SelectStatement;
import storageManager.*;

import java.util.ArrayList;

public class SelectProc extends Procedures {
    public SelectProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public ArrayList<Tuple> selectTuples(SelectStatement stmt) {
        ArrayList<Tuple> result = null;
        // Basic query Select * From SingleTable
        if ("*".equals(stmt.getColumns().get(0)) && stmt.getTables().size()==1) {
            result = new ArrayList<>();
            Relation relation = schema_manager.getRelation(stmt.getTables().get(0));
            int blocksInRel = relation.getNumOfBlocks();
            for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                // reading blocks from disk in accordance to memory capacity
                relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize())?
                        mem.getMemorySize(): blocksInRel - i);


                result.addAll(mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize())?
                        mem.getMemorySize(): blocksInRel - i));

            }
        }
        System.out.println("Select Procedure Result size \n" + result.size());
        System.out.println("Select Procedure Result \n" + result.toString());
        return result;
    }
}
