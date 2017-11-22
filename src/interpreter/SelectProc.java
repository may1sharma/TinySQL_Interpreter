package interpreter;

import parser.SelectStatement;
import storageManager.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SelectProc extends Procedures {
    public SelectProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public ArrayList<Tuple> selectTuples(SelectStatement stmt) {
        ArrayList<Tuple> result = new ArrayList<>();
        if (stmt.getTables().size()==1) {
            Relation relation = schema_manager.getRelation(stmt.getTables().get(0));
            int blocksInRel = relation.getNumOfBlocks();
            // Basic query Select * From SingleTable
            if ("*".equals(stmt.getColumns().get(0))) {
                for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                    // reading blocks from disk in accordance to memory capacity
                    relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize())?
                            mem.getMemorySize(): blocksInRel - i);

                    result.addAll(mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize())?
                            mem.getMemorySize(): blocksInRel - i));
                }
            } else {
                // Create temporary schema and relation to store Tuple results
                ArrayList<String> temp_Fnames = new ArrayList<String>();
                ArrayList<FieldType> temp_Ftypes = new ArrayList<FieldType>();
                Schema schema = relation.getSchema();
                Schema temp_schema = null;

                stmt.getColumns().forEach(s -> {
                    if (s.contains(".")){
                        s = s.substring(s.lastIndexOf('.') + 1);
                    }
                    if (!schema.fieldNameExists(s)) {
                        throw new RuntimeException("No field " + s + " in Relation " + relation.getRelationName());
                    }
                    temp_Fnames.add(s);
                    temp_Ftypes.add(schema.getFieldType(s));
                });

                if (!temp_Fnames.isEmpty()) {
                    temp_schema = new Schema(temp_Fnames, temp_Ftypes);
                }
                Relation temp_relation = schema_manager.createRelation("tempSelect", temp_schema);

                // reading blocks from disk in accordance to memory capacity
                for (int i=0; i<blocksInRel; i += mem.getMemorySize()) {
                    // reading blocks from disk in accordance to memory capacity
                    relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize())?
                            mem.getMemorySize(): blocksInRel - i);

                    ArrayList<Tuple> tempResult = result;
                    mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize())?
                            mem.getMemorySize(): blocksInRel - i).forEach( tuple -> {
                        Tuple temp_Tuple = temp_relation.createTuple();
                        // set up the temporary tuple
                        stmt.getColumns().forEach( s -> {
                            if (schema.getFieldType(s) == FieldType.INT) {
                                temp_Tuple.setField(s, tuple.getField(s).integer);
                            } else {
                                temp_Tuple.setField(s, tuple.getField(s).str);
                            }
                        });
                        // Add this to final list
                        tempResult.add(temp_Tuple);
                    });
                }
            }
        }


        // Checking DISTINCT clause
        if (stmt.isHasDistinct()) {
            System.out.println("In DISTINCT clause ");
            Set<Tuple> hs = new LinkedHashSet<>();
            hs.addAll(result);
//            System.out.println(hs);
            result.clear();
            result.addAll(hs);
        }

        System.out.println("Select Procedure Result size \n" + result.size());
        System.out.println("Select Procedure Result \n" + result.toString());
        return result;
    }
}
