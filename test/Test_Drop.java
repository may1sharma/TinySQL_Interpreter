import parser.DropStatement;
import parser.Statement;
import parser.TinyParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Drop {
    public static void main(String[] args) {
        System.out.println("Let's Begin Drop Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/drop_test.txt"));

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Table Name :"+((DropStatement)t1).getTableName());
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
