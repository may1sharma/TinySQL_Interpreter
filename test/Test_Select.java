import interpreter.WhereClause;
import parser.CreateStatement;
import parser.SelectStatement;
import parser.Statement;
import parser.TinyParser;

import java.io.FileReader;
import java.util.ArrayList;

public class Test_Select {
    public static void main(String[] args) {
        System.out.println("Let's Begin Select Test");

        try{
            TinyParser parser = new TinyParser (new FileReader("test/select_test.txt"));

            ArrayList<Statement> tableList = parser.init();
            for(Statement t1 : tableList)
            {  System.out.println("--------------------------");
                System.out.println("Has DISTINCT :"+((SelectStatement)t1).isHasDistinct());
                System.out.println("Column names :"+((SelectStatement)t1).getColumns());
                System.out.println("Tables :"+((SelectStatement)t1).getTables());
                ArrayList<String> whr = (ArrayList<String>) ((SelectStatement)t1).getCondition();
                System.out.println("WHERE :" + whr);
                if (whr != null && !whr.isEmpty())
                    System.out.println("PostFix expression :"+ WhereClause.convertToPostFix(whr));
                System.out.println("ORDER BY :"+((SelectStatement)t1).getOrderColumn());
                System.out.println("--------------------------");
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}
