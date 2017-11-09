import parser.*;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Create {
    public static void main(String[] args) {
        System.out.println("Let's Begin Create Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/create_test.txt"));

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Table Name :"+((CreateStatement)t1).getTableName());
                System.out.println("Field names :"+((CreateStatement)t1).getAttributes().keySet());
                System.out.println("Data Types :"+((CreateStatement)t1).getAttributes().values());
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
