package interpreter;

import parser.DeleteStatement;
import storageManager.*;

import java.util.ArrayList;
import java.util.Iterator;

public class DeleteProc extends Procedures {
    public DeleteProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public void deleteTuples(DeleteStatement stmt) {
        ArrayList<String> clause = WhereClause.convertToPostFix((ArrayList<String>) stmt.getCondition());
        Relation relation = schema_manager.getRelation(stmt.getTableName());
        if (relation == null)
            throw new RuntimeException(stmt.getTableName()+ " Table does not exists");

        int blocksInRel = relation.getNumOfBlocks();

        if (clause == null) {
            // Delete all Tuples if Table exists
            relation.deleteBlocks(0);
        } else {
            // Delete tuples satisfying the given WHERE clause
            int validBlocks = 0;
            // Reading blocks from disk in accordance to memory capacity
            for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);

                ArrayList<Tuple> temp = mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);

                Iterator<Tuple> iter = temp.iterator();
                while (iter.hasNext()) {
                    Tuple tup = iter.next();
                    if (WhereClause.evaluatePostfix(clause, tup))
                        iter.remove();
                }

                // Rearrange to avoid holes
                if (!temp.isEmpty()) {
                    mem.setTuples(0, temp);
//                    System.out.println("Main Memory \n" + mem);
                    int numBlocks = (int) Math.ceil((double)temp.size() / temp.get(0).getTuplesPerBlock());
                    relation.setBlocks(validBlocks, 0, numBlocks);
                    validBlocks += numBlocks;
                }
            }
            // Delete redundant blocks at the end having indices staring from validBlocks count
            relation.deleteBlocks(validBlocks);
        }
        System.out.println("\n Delete Procedure \n" + relation);
    }
}
