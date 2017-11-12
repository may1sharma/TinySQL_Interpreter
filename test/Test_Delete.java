import parser.DeleteStatement;
import parser.Statement;
import parser.TinyParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Delete {
    public static void main(String[] args) {
        System.out.println("Let's Begin Delete Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/delete_test.txt"));

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Table :"+((DeleteStatement)t1).getTableName());
                System.out.println("WHERE :"+((DeleteStatement)t1).getCondition());
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
