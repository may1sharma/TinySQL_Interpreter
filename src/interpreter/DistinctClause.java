package interpreter;

import storageManager.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class DistinctClause {
    public ArrayList<Tuple> eliminateDuplicates(Relation relation, MainMemory mem, Disk disk, SchemaManager schema_manager) {
        // Apply one-pass algorithm if the entire relation fits in Main Memory else use Two Pass algorithm
        int blocksInRel = relation.getNumOfBlocks();
        Set<Tuple> hashSet = new LinkedHashSet<>();
        ArrayList<Tuple> result = new ArrayList<>();

        if (blocksInRel <= mem.getMemorySize()) {
            // One pass
            relation.getBlocks(0, 0, blocksInRel);
            hashSet.addAll(mem.getTuples(0, blocksInRel));
            result.addAll(hashSet);
            return result;

        } else {     // Two pass algorithm

            // Temporary relation to store sorted buckets in disk
            Relation temp_relation = schema_manager.createRelation("tempDistinct", relation.getSchema());
            String column = relation.getSchema().getFieldNames().get(0);
            // First pass will sort tuples in buckets of size of main memory capacity
            for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                // reading blocks from disk in accordance to memory capacity
                relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);

                ArrayList<Tuple> temp = mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);
                if (relation.getSchema().getFieldType(column) == FieldType.INT) {
                    temp.sort( (Tuple t1, Tuple t2) -> t1.getField(column).integer - t2.getField(column).integer );
                } else {
                    temp.sort( (Tuple t1, Tuple t2) -> t1.getField(column).str.compareTo(t2.getField(column).str) );
                }
                mem.setTuples(0, temp);
                temp_relation.setBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);
            }

            // Second pass will eliminate duplicates from the above buckets

            for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                // reading blocks from disk in accordance to memory capacity
                temp_relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);

                hashSet.addAll(mem.getTuples(0,
                        (blocksInRel - i >= mem.getMemorySize()) ? mem.getMemorySize() : blocksInRel - i));
            }
            result.addAll(hashSet);

            // Delete Temporary Tables
            temp_relation.deleteBlocks(0);
            schema_manager.deleteRelation("tempDistinct");

            return result;
        }
    }
}
