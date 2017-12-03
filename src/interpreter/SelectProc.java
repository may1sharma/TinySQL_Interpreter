package interpreter;

import parser.SelectStatement;
import storageManager.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SelectProc extends Procedures {
    public SelectProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public ArrayList<Tuple> selectTuples(SelectStatement stmt, FileOutputStream out) throws IOException {
        ArrayList<Tuple> result = new ArrayList<>();
        ArrayList<String> outColumns = null;

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
                outColumns = relation.getSchema().getFieldNames();
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
                    outColumns = temp_Fnames;
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

        } else {    // Multiple Table queries

            // Create temporary schema and relation to store the combined tuple with fields from all relations
            ArrayList<String> temp_Fnames = new ArrayList<String>();
            ArrayList<FieldType> temp_Ftypes = new ArrayList<FieldType>();
            Schema join_schema = null;

            stmt.getTables().forEach(rel -> {
                Schema schema = schema_manager.getRelation(rel).getSchema();
                schema.getFieldNames().forEach(fname -> {
                    temp_Fnames.add(rel +"." + fname);
                    temp_Ftypes.add(schema.getFieldType(fname));
                });
            });

            if (!temp_Fnames.isEmpty()) {
                join_schema = new Schema(temp_Fnames, temp_Ftypes);
                outColumns = temp_Fnames;
            }
            Relation join_relation = schema_manager.createRelation("joinSelect", join_schema);


            // Check for Order By clause. If present, sort the given relation and then apply cross product with other
            // relations. Always use this relation or its result in Outer loop so as to maintain the sorted order.
            if (!(orderCol == null || orderCol.isEmpty())) {
                int idx = orderCol.lastIndexOf('.');
                if (idx != -1) {
                    String orderRelName = orderCol.substring(0, idx);
                    List<String> names = stmt.getTables();
                    // put the order relation name at first
                    names.remove(orderRelName);
                    names.add(0, orderRelName);
                    stmt.setTables(names);
                    // get the sorted tuples
                    result = OrderClause.sortRelation(schema_manager.getRelation(orderRelName),
                            orderCol.substring(idx + 1), mem, disk, schema_manager);
                    // eliminate duplicates if required so as to reduce time taken by cross join
                    if (stmt.isHasDistinct()) {
                        Set<Tuple> hs = new LinkedHashSet<>();
                        hs.addAll(result);
                        result.clear();
                        result.addAll(hs);
                    }
                    result = crossProduct(join_relation, orderRelName, result, stmt.getTables().get(1),
                            loadTuples(stmt.getTables().get(1), stmt.isHasDistinct()));
                } else {
                    throw new RuntimeException("Relation name not specified in OrderBy clause");
                }
            } else {
                result = crossProduct(join_relation,
                        stmt.getTables().get(0), loadTuples(stmt.getTables().get(0), stmt.isHasDistinct()),
                        stmt.getTables().get(1), loadTuples(stmt.getTables().get(1), stmt.isHasDistinct()));
            }
            for(int i=2; i<stmt.getTables().size(); i++) {
                ArrayList<Tuple> loadedTuples = loadTuples(stmt.getTables().get(i), stmt.isHasDistinct());
                result = crossProduct(join_relation, null, result, stmt.getTables().get(i), loadedTuples);
            }

            // Check for where clause. Eliminate tuple not confirming Where clause, if any
            if (clause != null) {
                Iterator<Tuple> iter = result.iterator();
                while (iter.hasNext()) {
                    Tuple tup = iter.next();
                    if (!WhereClause.evaluatePostfix(clause, tup))
                        iter.remove();
                }
            }

            // Projection
            if (!"*".equals(stmt.getColumns().get(0))) {
                ArrayList<String> tmp_Fnames = new ArrayList<String>();
                ArrayList<FieldType> tmp_Ftypes = new ArrayList<FieldType>();
                Schema tmp_schema = null;

                Schema finalJoin_schema = join_schema;
                stmt.getColumns().forEach(s -> {
                    if (s.lastIndexOf('.') == -1){
                        throw new RuntimeException("Relation name NOT specified for column " + s);
                    } else if (!finalJoin_schema.fieldNameExists(s)) {
                        throw new RuntimeException("No such field " + s + " exists ");
                    }
                    tmp_Fnames.add(s);
                    tmp_Ftypes.add(finalJoin_schema.getFieldType(s));
                });

                if (!tmp_Fnames.isEmpty()) {
                    tmp_schema = new Schema(tmp_Fnames, tmp_Ftypes);
                    outColumns = tmp_Fnames;
                }
                Relation tmp_relation = schema_manager.createRelation("tmpSelect", tmp_schema);

                ArrayList<Tuple> tmpResult = result;
                result = new ArrayList<>();
                ArrayList<Tuple> finalResult2 = result;
                tmpResult.forEach(tuple -> {
                    Tuple tmp_Tuple = tmp_relation.createTuple();
                    stmt.getColumns().forEach(s -> {
                        if (finalJoin_schema.getFieldType(s) == FieldType.INT) {
                            tmp_Tuple.setField(s, tuple.getField(s).integer);
                        } else {
                            tmp_Tuple.setField(s, tuple.getField(s).str);
                        }
                    });
                    finalResult2.add(tmp_Tuple);
                });

                // Delete temporary relation
                schema_manager.deleteRelation("tmpSelect");
            }

            // Delete Temporary Table
            schema_manager.deleteRelation("joinSelect");
        }

        // Check for eliminating duplicates
        if (stmt.isHasDistinct()) {
            Set<Tuple> hs = new LinkedHashSet<>();
            hs.addAll(result);
            result.clear();
            result.addAll(hs);
        }

//        System.out.println("Select Procedure Result size \n" + result.size());
//        System.out.println("Select Procedure Result \n" + result.toString());
        out.write(("Selected Tuple Count : " + result.size() + "\r\n").getBytes());
        if (result.size() != 0) {
            out.write(("Selected Tuples : \r\n").getBytes());
            out.write(("Columns " + outColumns + "\r\n").getBytes());
            for (Tuple tuple : result) {
                out.write((tuple + "\r\n").getBytes());
            }
        }
        return result;
    }

    public ArrayList<Tuple> crossProduct(Relation relation, String relationName1, ArrayList<Tuple> r1,
                                         String relationName2, ArrayList<Tuple> r2) {
        ArrayList<Tuple> result = new ArrayList<>();
        Schema schema1 = r1.get(0).getSchema();
        Schema schema2 = r2.get(0).getSchema();

        r1.forEach(tup1 -> r2.forEach(tup2 -> {
            Tuple tuple = relation.createTuple();
            schema1.getFieldNames().forEach(s -> {
                if (schema1.getFieldType(s) == FieldType.INT) {
                    tuple.setField((relationName1 == null)? s: relationName1 + "." + s, tup1.getField(s).integer);
                } else {
                    tuple.setField((relationName1 == null)? s: relationName1 + "." + s, tup1.getField(s).str);
                }
            });
            schema2.getFieldNames().forEach(s -> {
                if (schema2.getFieldType(s) == FieldType.INT) {
                    tuple.setField((relationName2 == null)? s: relationName2 + "." + s, tup2.getField(s).integer);
                } else {
                    tuple.setField((relationName2 == null)? s: relationName2 + "." + s, tup2.getField(s).str);
                }
            });
            result.add(tuple);
        }));
        return result;
    }

    public ArrayList<Tuple> loadTuples(String relationName, boolean distinct) {
        ArrayList<Tuple> result = new ArrayList<>();
        Set<Tuple> hashSet = new LinkedHashSet<>();
        Relation relation = schema_manager.getRelation(relationName);
        int blocksInRel = relation.getNumOfBlocks();
        for (int i = 0; i < blocksInRel; i += mem.getMemorySize()) {
            // reading blocks from disk in accordance to memory capacity
            relation.getBlocks(i, 0, (blocksInRel - i >= mem.getMemorySize()) ?
                    mem.getMemorySize() : blocksInRel - i);
            if (distinct) {
                hashSet.addAll(mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i));
            } else {
                result.addAll(mem.getTuples(0, (blocksInRel - i >= mem.getMemorySize()) ?
                        mem.getMemorySize() : blocksInRel - i));
            }
        }
        if (distinct) {
            result.addAll(hashSet);
        }
        return result;
    }
}
