package interpreter;

import parser.*;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("------------------------ Welcome to TinySQL Interpreter ------------------------");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("");

        Procedures procedures = new Procedures(null, null, null);
        TinyParser parser = new TinyParser(new StringReader("Dummy"));
        ArrayList<Statement> statements;
        String input = null;

        while (true) {
            System.out.println("\n Input Options: \n 1. Single Query \n 2. File upload " +
                    "\n Press 0 to exit \n \n Choose (1/2)?");
            try {
                switch (new Scanner(System.in).nextInt()) {
                    case 0:
                        System.out.println(" Thanks for using TinySQL interpreter");
                        System.exit(0);
                        break;
                    case 1:
                        System.out.println(" Enter a TinySQL query:");
                        input = new Scanner(System.in).nextLine();
//                        System.out.println("Output " + input);
                        parser.ReInit(new StringReader(input));
                        statements = parser.init();
                        processStatements(statements, procedures);
                        break;
                    case 2:
                        System.out.println(" Enter full path to the input file:");
                        input = new Scanner(System.in).nextLine();
//                        System.out.println("Output " + input);
                        parser = new TinyParser (new FileReader(input));
//                        parser = new TinyParser (new FileReader("test/flow_test.txt"));
                        statements = parser.init();
                        processStatements(statements, procedures);
                        break;
                    default:
                        throw new IllegalStateException();
                }
            } catch (ParseException e) {
                System.out.println("\nError in parsing the query ");
                e.printStackTrace();
            } catch (FileNotFoundException fnf) {
                System.out.println("\nCould not locate the given file \n" + input);
            } catch (NoSuchElementException | IllegalStateException nse) {
                System.out.println("\nInvalid Input. Either choose option 1 or option 2");
            }catch (Exception ex) {
                ex.printStackTrace() ;
            }
        }


    }

    static void processStatements(ArrayList<Statement> statements, Procedures procedures) throws IOException {
        System.out.println(" Processing input ...");
        CreateTableProc create_proc = new CreateTableProc(procedures.getMem(), procedures.getDisk(),
                procedures.getSchema_manager());
        InsertProc insert_proc = new InsertProc(procedures.getMem(), procedures.getDisk(),
                procedures.getSchema_manager());
        DropTableProc drop_proc = new DropTableProc(procedures.getMem(), procedures.getDisk(),
                procedures.getSchema_manager());
        SelectProc select_proc = new SelectProc(procedures.getMem(), procedures.getDisk(),
                procedures.getSchema_manager());
        DeleteProc delete_proc = new DeleteProc(procedures.getMem(), procedures.getDisk(),
                procedures.getSchema_manager());
        FileOutputStream out = null;

        try {
            out = new FileOutputStream("Result.txt");
            for(Statement stmt : statements) {
                out.write((stmt.toString() + "\r\n").getBytes());
                procedures.getDisk().resetDiskTimer();
                procedures.getDisk().resetDiskIOs();
                long start = System.currentTimeMillis();

                switch (stmt.getType()) {
                    case CREATE:
                        create_proc.createRelation((CreateStatement) stmt, out);
                        break;
                    case INSERT:
                        insert_proc.insertTuples((InsertStatement) stmt, out);
                        break;
                    case DROP:
                        drop_proc.dropRelation((DropStatement) stmt, out);
                        break;
                    case SELECT:
                        select_proc.selectTuples((SelectStatement) stmt, out);
                        break;
                    case DELETE:
                        delete_proc.deleteTuples((DeleteStatement) stmt, out);
                }

                long elapsedTimeMillis = System.currentTimeMillis() - start;
                out.write(("\r\nSystem elapse time = " + elapsedTimeMillis + " ms" + "\r\n").getBytes());
                out.write(("Calculated Disk elapse time = " + procedures.getDisk().getDiskTimer() + " ms" + "\r\n").getBytes());
                out.write(("Calculated Disk I/Os = " + procedures.getDisk().getDiskIOs() + "\r\n").getBytes());
                out.write(("--------------------------------------------------------------------------------------------\r\n").getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                System.out.println("\n Output is logged in file " +  System.getProperty("user.dir") + "\\Result.txt");
                out.close();
            }
        }
    }
}