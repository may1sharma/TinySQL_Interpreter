package interpreter;

import parser.CreateStatement;
import storageManager.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTableProc extends Procedures {
    public CreateTableProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }

    public Relation createRelation(CreateStatement stmt, FileOutputStream out) throws IOException {
//        System.out.print("Creating a schema" + "\n");
        ArrayList<String> field_names=new ArrayList<String>();
        ArrayList<FieldType> field_types=new ArrayList<FieldType>();

        stmt.getAttributes().forEach((k,v) -> {
            field_names.add(k);
            if ("INT".equalsIgnoreCase(v)) {
                field_types.add(FieldType.INT);
            } else if ("STR20".equalsIgnoreCase(v)){
                field_types.add(FieldType.STR20);
            } else {
                throw new RuntimeException("Unknown Field Type " + v);
            }
        });

        Schema schema=new Schema(field_names,field_types);
//        System.out.print("Creating table " + stmt.getTableName() + "\n");
        Relation relation = schema_manager.createRelation(stmt.getTableName(), schema);

//        System.out.println("\n Create Procedure Result \n" + relation);
        out.write(("Successfully created relation " + stmt.getTableName() + " \r\n").getBytes());
        return relation;
    }
}
