package interpreter;

import storageManager.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderClause {
    public static void sortRelation(Relation relation, String column, MainMemory mem, Disk disk, SchemaManager schema_manager) {
        // Apply one-pass algorithm if the entire relation fits in Main Memory else use Two Pass algorithm
        int blocksInRel = relation.getNumOfBlocks();

        if (blocksInRel <= mem.getMemorySize()) {
            // One pass
            relation.getBlocks(0, 0, blocksInRel);
            ArrayList<Tuple> temp = mem.getTuples(0, blocksInRel);
            if (relation.getSchema().getFieldType(column) == FieldType.INT) {
                temp.sort( (Tuple t1, Tuple t2) -> t1.getField(column).integer - t2.getField(column).integer );
            } else {
                temp.sort( (Tuple t1, Tuple t2) -> t1.getField(column).str.compareTo(t2.getField(column).str) );
            }
            mem.setTuples(0, temp);
            relation.setBlocks(0,0, blocksInRel);

        } else {
            // Two pass
        }
    }
}
