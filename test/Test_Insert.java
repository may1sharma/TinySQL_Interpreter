import parser.DeleteStatement;
import parser.InsertStatement;
import parser.Statement;
import parser.TinyParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Insert {
    public static void main(String[] args) {
        System.out.println("Let's Begin Insert Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/insert_test.txt"));

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Table :"+((InsertStatement)t1).getTableName());
                System.out.println("Attributes :"+((InsertStatement)t1).getAttributes());
                System.out.println("Values :"+((InsertStatement)t1).getValues());
                System.out.println("SELECT :"+((InsertStatement)t1).getSelectStatement());
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
