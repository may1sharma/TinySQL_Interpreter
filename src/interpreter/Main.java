package interpreter;

import parser.*;

import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Let's Begin");
        try{
            TinyParser parser = new TinyParser (new FileReader("test/flow_test.txt"));
            Procedures procedure = new Procedures(null, null, null);
            CreateTableProc create_proc = new CreateTableProc(procedure.getMem(), procedure.getDisk(),
                    procedure.getSchema_manager());
            InsertProc insert_proc = new InsertProc(procedure.getMem(), procedure.getDisk(),
                    procedure.getSchema_manager());
            DropTableProc drop_proc = new DropTableProc(procedure.getMem(), procedure.getDisk(),
                    procedure.getSchema_manager());
            SelectProc select_proc = new SelectProc(procedure.getMem(), procedure.getDisk(),
                    procedure.getSchema_manager());
            DeleteProc delete_proc = new DeleteProc(procedure.getMem(), procedure.getDisk(),
                    procedure.getSchema_manager());

            ArrayList<Statement> tableList = parser.init();
            for(Statement t : tableList)
            {
                switch (t.getType()) {
                    case CREATE:
                        create_proc.createRelation((CreateStatement) t);
                        break;
                    case INSERT:
                        insert_proc.insertTuples((InsertStatement) t);
                        break;
                    case DROP:
                        drop_proc.dropRelation((DropStatement) t);
                        break;
                    case SELECT:
                        select_proc.selectTuples((SelectStatement) t);
                        break;
                    case DELETE:
                        delete_proc.deleteTuples((DeleteStatement) t);
                }
            }
        }catch (Exception ex)
        {ex.printStackTrace() ;}
    }
}