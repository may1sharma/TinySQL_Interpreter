import parser.Create;
import parser.TableStruct;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Create {
    public static void main(String[] args) {
        System.out.println("Let's Begin");
        try{
            Create parser = new Create (new FileReader("test/create_test.txt"));

            ArrayList<TableStruct> tableList = parser.init();
            for(TableStruct t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Table Name :"+t1.TableName);
                System.out.println("Field names :"+t1.Variables.keySet());
                System.out.println("Data Types :"+t1.Variables.values());
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
