package interpreter;

import parser.InsertStatement;
import storageManager.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InsertProc extends Procedures {
    public InsertProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public void insertTuple(InsertStatement stmt) {
        Relation relation_ref = schema_manager.getRelation(stmt.getTableName());
        Tuple tuple = relation_ref.createTuple();
        Schema schema = relation_ref.getSchema();

        List<String> attr = stmt.getAttributes();
        List<String> vals = stmt.getValues();

        if (attr != null && vals != null) {
            for(int i=0; i<attr.size(); i++) {
                if(schema.getFieldType(attr.get(i)) == FieldType.INT){
                    tuple.setField(attr.get(i), Integer.parseInt(vals.get(i)));
                } else {
                    tuple.setField(attr.get(i), vals.get(i));
                }
            }
            appendTupleToRelation(relation_ref, 0, tuple);

        } else if (stmt.getSelectStatement() != null) {
            ArrayList<Tuple> tuples = new SelectProc(mem, disk, schema_manager).selectTuples(stmt.getSelectStatement());
            if (relation_ref.getNumOfBlocks() == 0) {
                appendTuplesToRelation(relation_ref, tuples);
            } else {
                relation_ref.getBlock(relation_ref.getNumOfBlocks()-1, 0);
                Block block_ref = mem.getBlock(0);
                if (block_ref.isFull()) {
                    appendTuplesToRelation(relation_ref, tuples);
                } else {
                    boolean hasCapacity = true;
                    // Fill the last block till it is Full, to avoid holes
                    while (hasCapacity) {
                        tuple = tuples.remove(0);
                        block_ref.appendTuple(tuple);
                        hasCapacity = !block_ref.isFull();
                    }
                    relation_ref.setBlock(relation_ref.getNumOfBlocks()-1,0);

                    appendTuplesToRelation(relation_ref, tuples);
                }
            }
        }

        System.out.println("Insert Procedure Result \n" + relation_ref);
    }

    // This method appends new tuple blocks at the end of relation
    private void appendTuplesToRelation(Relation relation_ref, ArrayList<Tuple> tuples) {
        if (tuples == null || tuples.isEmpty()) {
            return;
        }
        int tuplesPerBlock = tuples.get(0).getTuplesPerBlock();
        for (int i=0; i<tuples.size(); i += mem.getMemorySize()*tuplesPerBlock) {
            int endIndex = (tuples.size() >= i + mem.getMemorySize()*tuplesPerBlock)?
                    i + mem.getMemorySize()*tuplesPerBlock : tuples.size();
            ArrayList<Tuple> subList = new ArrayList<Tuple>(tuples.subList(i, endIndex));
            mem.setTuples(0, subList);
            relation_ref.setBlocks(relation_ref.getNumOfBlocks(),0,
                    (int)Math.ceil(subList.size()/tuplesPerBlock));
        }
    }

    private void appendTupleToRelation(Relation relation_reference, int memory_block_index, Tuple tuple) {
        Block block_reference;
        if (relation_reference.getNumOfBlocks()==0) {
            //The relation is empty
            //Get the handle to the memory block and clear it
            block_reference=mem.getBlock(memory_block_index);
            block_reference.clear(); //clear the block
            block_reference.appendTuple(tuple); // append the tuple
            //Write to the first block of the relation
            relation_reference.setBlock(relation_reference.getNumOfBlocks(),memory_block_index);
        } else {
            //Read the last block of the relation into memory block
            relation_reference.getBlock(relation_reference.getNumOfBlocks()-1, memory_block_index);
            block_reference=mem.getBlock(memory_block_index);

            if (block_reference.isFull()) {
                //The block is full: Clear the memory block and append the tuple
                block_reference.clear(); //clear the block
                block_reference.appendTuple(tuple); // append the tuple
                //Write to a new block at the end of the relation
                relation_reference.setBlock(relation_reference.getNumOfBlocks(),memory_block_index); //write back to the relation
            } else {
                //The block is not full: Append it directly
                block_reference.appendTuple(tuple); // append the tuple
                //Write to the last block of the relation
                relation_reference.setBlock(relation_reference.getNumOfBlocks()-1,memory_block_index); //write back to the relation
            }
        }
    }
}
