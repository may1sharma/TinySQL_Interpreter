package interpreter;

import com.sun.istack.internal.NotNull;
import storageManager.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class OrderClause {
    public static ArrayList<Tuple> sortRelation(Relation relation, String column, MainMemory mem, Disk disk,
                                                SchemaManager schema_manager) {
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
            return temp;

        } else {     // Two pass algorithm

            // Temporary relation to store sorted buckets in disk
            Relation temp_relation = schema_manager.createRelation("tempOrder", relation.getSchema());

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

            // Second pass will sort the above buckets using min heap / Priority Queue
            ArrayList<Tuple> result = new ArrayList<>();
            ArrayList<ArrayList<Tuple>> buckets = new ArrayList<>();

            for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                // reading blocks from disk in accordance to memory capacity
                temp_relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i);

                buckets.add(mem.getTuples(0,
                        (blocksInRel - i >= mem.getMemorySize()) ? mem.getMemorySize() : blocksInRel - i));
            }


            PriorityQueue<ArrayContainer> queue;
            if (relation.getSchema().getFieldType(column) == FieldType.INT) {
                queue = new PriorityQueue<ArrayContainer>((o1, o2) -> o1.arr.get(o1.index).getField(column).integer -
                        o2.arr.get(o2.index).getField(column).integer );
            } else {
                queue = new PriorityQueue<ArrayContainer>((o1, o2) -> o1.arr.get(o1.index).getField(column).str
                        .compareTo(o2.arr.get(o2.index).getField(column).str) );
            }

            //add arrays to heap
            for (int i = 0; i < buckets.size(); i++) {
                queue.add(new ArrayContainer(buckets.get(i), 0));
            }

            // retrieving the minimum valued Tuple and appending it to result
            while(!queue.isEmpty()){
                ArrayContainer ac = queue.poll();
                result.add(ac.arr.get(ac.index));

                if(ac.index < ac.arr.size()-1){
                    queue.add(new ArrayContainer(ac.arr, ac.index+1));
                }
            }

            // Delete Temporary Tables
            temp_relation.deleteBlocks(0);
            schema_manager.deleteRelation("tempOrder");

            return result;
        }
    }

    static class ArrayContainer {
        ArrayList<Tuple> arr;
        int index;

        public ArrayContainer(@NotNull ArrayList<Tuple> arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        @Override
        public String toString() {
            return "ArrayContainer{" +
                    "arr=" + arr +
                    ", index=" + index +
                    '}';
        }
    }
}
