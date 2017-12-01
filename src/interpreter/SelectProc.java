package interpreter;

import parser.SelectStatement;
import storageManager.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class SelectProc extends Procedures {
    public SelectProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public ArrayList<Tuple> selectTuples(SelectStatement stmt) {
        ArrayList<Tuple> result = new ArrayList<>();

        // ORDER BY clause
        String orderCol = stmt.getOrderColumn();

        // Where clause conversion to PostFix expression
        final ArrayList<String> clause =  WhereClause.convertToPostFix((ArrayList<String>) stmt.getCondition());
//        System.out.println(" WHERE Clause : " + clause);

        if (stmt.getTables().size()==1) {   // Single Relation queries
            Relation relation = schema_manager.getRelation(stmt.getTables().get(0));
            int blocksInRel = relation.getNumOfBlocks();
            // Basic query Select * From SingleTable
            if ("*".equals(stmt.getColumns().get(0))) {
                if (!(orderCol == null || orderCol.isEmpty())) {
                    int idx = orderCol.lastIndexOf('.');
                    if (idx != -1){
                        result = OrderClause.sortRelation(schema_manager.getRelation(orderCol.substring(0, idx)),
                                orderCol.substring(idx+1), mem, disk, schema_manager);
                    } else {
                        result = OrderClause.sortRelation(schema_manager.getRelation(stmt.getTables().get(0)),
                                orderCol, mem, disk, schema_manager);
                    }

                    // eliminate tuple not confirming Where clause, if any
                    if (clause != null) {
                        Iterator<Tuple> iter = result.iterator();
                        while (iter.hasNext()) {
                            Tuple tup = iter.next();
                            if (!WhereClause.evaluatePostfix(clause, tup))
                                iter.remove();
                        }
                    }

                } else {
                    for (int i = 0; i < blocksInRel; i += mem.getMemorySize()) {
                        // reading blocks from disk in accordance to memory capacity
                        relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                                mem.getMemorySize() : blocksInRel - i);

                        ArrayList<Tuple> temp = mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize()) ?
                                mem.getMemorySize() : blocksInRel - i);
                        // WHERE clause check
                        if (clause == null) {
                            result.addAll(temp);
                        } else {
                            ArrayList<Tuple> finalResult = result;
                            temp.forEach(tuple -> {
                                if (WhereClause.evaluatePostfix(clause, tuple))
                                    finalResult.add(tuple);
                            });
                        }
                    }
                }

            } else {
                // Create temporary schema and relation to store Tuple results
                ArrayList<String> temp_Fnames = new ArrayList<String>();
                ArrayList<FieldType> temp_Ftypes = new ArrayList<FieldType>();
                Schema schema = relation.getSchema();
                Schema temp_schema = null;

                stmt.getColumns().forEach(s -> {
                    int idx = s.lastIndexOf('.');
                    if (idx != -1){
                        if (!relation.getRelationName().equalsIgnoreCase(s.substring(0, idx))) {
                            throw new RuntimeException("No such column " + s + " in Relation " + relation.getRelationName());
                        }
                        s = s.substring(idx + 1);
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

                if (!(orderCol == null || orderCol.isEmpty())) {
                    ArrayList<Tuple> orderedTuples;
                    int idx = orderCol.lastIndexOf('.');
                    if (idx != -1){
                        if (!stmt.getTables().get(0).equalsIgnoreCase(orderCol.substring(0, idx))) {
                            throw new RuntimeException("Given relation and OrderBy relation name do not match");
                        }
                        orderedTuples = OrderClause.sortRelation(schema_manager.getRelation(orderCol.substring(0, idx)),
                                orderCol.substring(idx+1), mem, disk, schema_manager);
                    } else {
                        orderedTuples = OrderClause.sortRelation(schema_manager.getRelation(stmt.getTables().get(0)),
                                orderCol, mem, disk, schema_manager);
                    }

                    // eliminate tuple not confirming Where clause, if any
                    if (clause != null) {
                        Iterator<Tuple> iter = orderedTuples.iterator();
                        while (iter.hasNext()) {
                            Tuple tup = iter.next();
                            if (!WhereClause.evaluatePostfix(clause, tup))
                                iter.remove();
                        }
                    }

                    // Projection
                    ArrayList<Tuple> finalResult1 = result;
                    orderedTuples.forEach(tuple -> {
                        Tuple temp_Tuple = temp_relation.createTuple();
                        stmt.getColumns().forEach(s -> {
                            int idx1 = s.lastIndexOf('.');
                            if (idx1 != -1) {
                                if (!relation.getRelationName().equalsIgnoreCase(s.substring(0, idx1))) {
                                    throw new RuntimeException("No such column " + s + " in Relation " +
                                            relation.getRelationName());
                                }
                                s = s.substring(idx1 + 1);
                            }

                            if (schema.getFieldType(s) == FieldType.INT) {
                                temp_Tuple.setField(s, tuple.getField(s).integer);
                            } else {
                                temp_Tuple.setField(s, tuple.getField(s).str);
                            }
                        });
                        finalResult1.add(temp_Tuple);
                    });

                } else {
                    // reading blocks from disk in accordance to memory capacity
                    for (int i = 0; i < blocksInRel; i += mem.getMemorySize()) {
                        // reading blocks from disk in accordance to memory capacity
                        relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                                mem.getMemorySize() : blocksInRel - i);

                        ArrayList<Tuple> tempResult = result;
                        mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize()) ?
                                mem.getMemorySize() : blocksInRel - i).forEach(tuple -> {
                            // checking WHERE clause
                            if (clause == null || WhereClause.evaluatePostfix(clause, tuple)) {
                                Tuple temp_Tuple = temp_relation.createTuple();
                                // Projection: set up the temporary tuple
                                stmt.getColumns().forEach(s -> {
                                    int idx = s.lastIndexOf('.');
                                    if (idx != -1) {
                                        if (!relation.getRelationName().equalsIgnoreCase(s.substring(0, idx))) {
                                            throw new RuntimeException("No such column " + s + " in Relation " +
                                                    relation.getRelationName());
                                        }
                                        s = s.substring(idx + 1);
                                    }

                                    if (schema.getFieldType(s) == FieldType.INT) {
                                        temp_Tuple.setField(s, tuple.getField(s).integer);
                                    } else {
                                        temp_Tuple.setField(s, tuple.getField(s).str);
                                    }
                                });
                                tempResult.add(temp_Tuple);
                            }
                        });

                    }
                }

                // Delete Temporary Tables
                schema_manager.deleteRelation("tempSelect");
            }

            // Checking DISTINCT clause
            if (stmt.isHasDistinct()) {
//                        System.out.println("In DISTINCT clause ");
                Set<Tuple> hs = new LinkedHashSet<>();
                hs.addAll(result);
//                        System.out.println(hs);
                result.clear();
                result.addAll(hs);
            }
        } else {    // Multiple Table queries

        }


        System.out.println("Select Procedure Result size \n" + result.size());
        System.out.println("Select Procedure Result \n" + result.toString());
        return result;
    }
}
