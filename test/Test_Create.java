import interpreter.CreateTableProc;
import parser.*;
import storageManager.Relation;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Create {
    public static void main(String[] args) {
        System.out.println("Let's Begin Create Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/create_test.txt"));
            CreateTableProc proc = new CreateTableProc(null,null,null);

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Table Name :"+((CreateStatement)t1).getTableName());
                System.out.println("Field names :"+((CreateStatement)t1).getAttributes().keySet());
                System.out.println("Data Types :"+((CreateStatement)t1).getAttributes().values());
                System.out.println("--------------------------");

                Relation relation_reference = proc.createRelation((CreateStatement) t1);

                // Print the information about the Relation
                System.out.print("The table has name " + relation_reference.getRelationName() + "\n");
                System.out.print("The table has schema:" + "\n");
                System.out.print(relation_reference.getSchema() + "\n");
                System.out.print("The table currently have " + relation_reference.getNumOfBlocks() + " blocks" + "\n");
                System.out.print("The table currently have " + relation_reference.getNumOfTuples() + " tuples" + "\n" + "\n");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
