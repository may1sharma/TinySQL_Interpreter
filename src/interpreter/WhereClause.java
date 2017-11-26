package interpreter;

import storageManager.FieldType;
import storageManager.Schema;
import storageManager.Tuple;

import java.util.ArrayList;
import java.util.Stack;

public class WhereClause {

    static int Precedence(String op) {
        switch (op)
        {
            case "*":
            case "/":
                return 5;
            case "+":
            case "-":
                return 4;
            case "=":
            case ">":
            case "<":
            case ">=":
            case "<=":
                return 3;
            case "NOT":
                return 2;
            case "AND":
            case "OR":
                return 1;
            case "[":
            case "]":
            case "(":
            case ")":
                return 0;
        }
        return -1;
    }

    public static ArrayList<String> convertToPostFix(ArrayList<String> clause) {
        if (clause == null || clause.isEmpty())
            return null;

        ArrayList<String> result = new ArrayList<>();

        // initializing empty stack
        Stack<String> stack = new Stack<>();

        for (int i = 0; i<clause.size(); ++i)
        {
            String s = clause.get(i);
            int prec = Precedence(s.trim().toUpperCase());
            // If the scanned string is an operand, add it to result.
            if (prec == -1)
                result.add(s);
            // If the scanned string is an "(" or "[", push it to the stack.
            else if (s == "(" || s == "[")
                stack.push(s);

            //  If the scanned character is an ")", pop and output from the stack
            // until an "(" is encountered. Similarly for "]"
            else if (s == ")" || s == "]")
            {
                while (!(stack.isEmpty() || stack.peek() == "(" || stack.peek() == "["))
                    result.add(stack.pop());

                if (stack.isEmpty()) {
                    System.out.println(result);
                    throw new RuntimeException("Invalid Expression : " + clause); // invalid expression
                } else
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && prec <= Precedence(stack.peek().trim().toUpperCase()))
                    result.add(stack.pop());
                stack.push(s);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty())
            result.add(stack.pop());

        return result;
    }

    public static boolean evaluatePostfix(ArrayList<String> clause, Tuple tuple) {
        // initializing empty stack
        Stack<String> stack = new Stack<>();
//        System.out.println("Tuple : " + tuple);

        for (int i = 0; i<clause.size(); ++i) {
//            System.out.println("Stack : " + stack);
            String s = clause.get(i);
            int prec = Precedence(s.trim().toUpperCase());
            // If the scanned string is an operand, add it to stack.
            if (prec == -1) {
                stack.push(s);
            } else {
                Schema schema = tuple.getSchema();
                double v1 = 0,  v2 = 0;

                switch(s.trim().toUpperCase()) {
                    case "*":
                        String s2 = stack.pop();
                        String s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1*v2));
                        break;

                    case "/":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1/v2));
                        break;

                    case "+":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1+v2));
                        break;

                    case "-":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1-v2));
                        break;

                    case ">":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1>v2));
                        break;

                    case "<":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1<v2));
                        break;

                    case ">=":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1>=v2));
                        break;

                    case "<=":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        if (schema.fieldNameExists(s1)) {
                            v1 = tuple.getField(s1).integer;
                        } else {
                            v1 = Double.parseDouble(s1);
                        }
                        if (schema.fieldNameExists(s2)) {
                            v2 = tuple.getField(s2).integer;
                        } else {
                            v2 = Double.parseDouble(s2);
                        }
                        stack.push(String.valueOf(v1<=v2));
                        break;

                    case "=":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        boolean str = false;
                        if (schema.fieldNameExists(s1)) {
                            if (schema.getFieldType(s1) == FieldType.INT)
                                v1 = tuple.getField(s1).integer;
                            else {
                                s1 = tuple.getField(s1).str;
                                str = true;
                            }
                        } else {
                            try {
                                v1 = Double.parseDouble(s1);
                            } catch (NumberFormatException e) {
                                str = true;
                            }
                        }
                        if (schema.fieldNameExists(s2)) {
                            if (schema.getFieldType(s2) == FieldType.INT)
                                v2 = tuple.getField(s2).integer;
                            else {
                                if (!str) {
                                    throw new RuntimeException("Cannot compare STR20 and INT : " + s2 + " " + s1);
                                }
                                s2 = tuple.getField(s2).str;
                            }
                        } else {
                            try {
                                v2 = Double.parseDouble(s2);
                            } catch (NumberFormatException e) {
                                if (!str) {
                                    throw new RuntimeException("Cannot compare STR20 and INT : " + s2 + " " + s1);
                                }
                            }
                        }

                        if (str) {
                            stack.push(String.valueOf(s1.equals(s2)));
                        } else {
                            stack.push(String.valueOf(v1 == v2));
                        }
                        break;

                    case "NOT":
                        s2 = stack.pop();
                        boolean b = Boolean.parseBoolean(s2);
                        stack.push(String.valueOf( !b ));
                        break;

                    case "AND":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        boolean b1 = Boolean.parseBoolean(s1);
                        boolean b2 = Boolean.parseBoolean(s2);
                        stack.push(String.valueOf(b1 && b2));
                        break;

                    case "OR":
                        s2 = stack.pop();
                        s1 = stack.pop();
                        b1 = Boolean.parseBoolean(s1);
                        b2 = Boolean.parseBoolean(s2);
                        stack.push(String.valueOf(b1 || b2));
                        break;

                }
            }
        }

//        System.out.println("Stack : " + stack);
        boolean result = Boolean.parseBoolean(stack.pop());
        if (stack.empty())
            return result;
        else
            throw new RuntimeException("Something fishy. Condition not evaluated correctly.");
    }
}
