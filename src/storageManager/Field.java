package storageManager;

import java.io.Serializable;

/* A field can either by an integer or a string
 * the "STATEMENT_TYPE" variable indicates the field STATEMENT_TYPE
 * Usage: Fields are automatically setup when the schema is specified
 */

public class Field implements Serializable {
	// determines whether the field is a integer or a string
	  public FieldType type=FieldType.INT; 
	  public int integer=Integer.MIN_VALUE;
	  public String str=null;
	  public Field() {}
	  public Field(Field f) {
	    type=f.type;
	    integer=f.integer;
	    str=f.str;
	  }
	  public String toString() {
	    String str="";
	    if (type==FieldType.INT)
	      str+=integer;
	    else if (type==FieldType.STR20)
	      str+=this.str;
	    return str;
	  }
}
