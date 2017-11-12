import parser.CreateStatement;
import parser.Statement;
import parser.TinyParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_All {
    public static void main(String[] args) {
        System.out.println("Let's Begin FULL Parser Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/TinySQL-TextWin.txt"));

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Type :"+ t1.getType());
                System.out.println("Statement :"+ t1);
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
