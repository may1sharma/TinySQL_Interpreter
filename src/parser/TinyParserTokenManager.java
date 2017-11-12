/* Generated By:JavaCC: Do not edit this line. TinyParserTokenManager.java */
package parser;
import java.util.ArrayList;
import java.util.HashMap;

/** Token Manager. */
public class TinyParserTokenManager implements TinyParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 9:
         jjmatchedKind = 5;
         return jjMoveNfa_0(2, 0);
      case 10:
         jjmatchedKind = 1;
         return jjMoveNfa_0(2, 0);
      case 13:
         jjmatchedKind = 2;
         return jjMoveStringLiteralDfa1_0(0x8L);
      case 32:
         jjmatchedKind = 6;
         return jjMoveNfa_0(2, 0);
      case 34:
         jjmatchedKind = 39;
         return jjMoveNfa_0(2, 0);
      case 40:
         jjmatchedKind = 23;
         return jjMoveNfa_0(2, 0);
      case 41:
         jjmatchedKind = 24;
         return jjMoveNfa_0(2, 0);
      case 42:
         jjmatchedKind = 33;
         return jjMoveNfa_0(2, 0);
      case 43:
         jjmatchedKind = 31;
         return jjMoveNfa_0(2, 0);
      case 44:
         jjmatchedKind = 29;
         return jjMoveNfa_0(2, 0);
      case 45:
         jjmatchedKind = 32;
         return jjMoveNfa_0(2, 0);
      case 46:
         jjmatchedKind = 30;
         return jjMoveNfa_0(2, 0);
      case 47:
         jjmatchedKind = 34;
         return jjMoveNfa_0(2, 0);
      case 60:
         jjmatchedKind = 35;
         return jjMoveNfa_0(2, 0);
      case 61:
         jjmatchedKind = 37;
         return jjMoveNfa_0(2, 0);
      case 62:
         jjmatchedKind = 36;
         return jjMoveNfa_0(2, 0);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x1900L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 78:
         return jjMoveStringLiteralDfa1_0(0x10000020000L);
      case 79:
         return jjMoveStringLiteralDfa1_0(0x40000010000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 86:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 87:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 91:
         jjmatchedKind = 27;
         return jjMoveNfa_0(2, 0);
      case 92:
         jjmatchedKind = 4;
         return jjMoveNfa_0(2, 0);
      case 93:
         jjmatchedKind = 28;
         return jjMoveNfa_0(2, 0);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x1900L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 123:
         jjmatchedKind = 25;
         return jjMoveNfa_0(2, 0);
      case 125:
         jjmatchedKind = 26;
         return jjMoveNfa_0(2, 0);
      default :
         return jjMoveNfa_0(2, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 0);
   }
   switch(curChar)
   {
      case 10:
         if ((active0 & 0x8L) != 0L)
         {
            jjmatchedKind = 3;
            jjmatchedPos = 1;
         }
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0xa00L);
      case 72:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 78:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000400L);
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000000L);
      case 82:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000014180L);
      case 85:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0xa00L);
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x14180L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 1);
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 2;
         }
         break;
      case 68:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000010000L);
      case 69:
         return jjMoveStringLiteralDfa3_0(active0, 0x8080L);
      case 76:
         return jjMoveStringLiteralDfa3_0(active0, 0x22a00L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x4100L);
      case 83:
         return jjMoveStringLiteralDfa3_0(active0, 0x1400L);
      case 84:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000000L);
      case 100:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x8080L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x22a00L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x4100L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x1400L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 2);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 2);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 2);
   }
   switch(curChar)
   {
      case 32:
         if ((active0 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 3;
         }
         else if ((active0 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 41;
            jjmatchedPos = 3;
         }
         break;
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 69:
         return jjMoveStringLiteralDfa4_0(active0, 0x10e00L);
      case 76:
         if ((active0 & 0x20000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 3;
         }
         break;
      case 77:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 3;
         }
         break;
      case 80:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 82:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 84:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 85:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x10e00L);
      case 108:
         if ((active0 & 0x20000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 3;
         }
         break;
      case 109:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 3;
         }
         break;
      case 112:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 3);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 3);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 3);
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa5_0(active0, 0x100L);
      case 67:
         return jjMoveStringLiteralDfa5_0(active0, 0x200L);
      case 69:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 15;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 73:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      case 82:
         return jjMoveStringLiteralDfa5_0(active0, 0x10400L);
      case 84:
         return jjMoveStringLiteralDfa5_0(active0, 0x880L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x200L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 15;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x10400L);
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x880L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 4);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 4);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 4);
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      case 69:
         return jjMoveStringLiteralDfa6_0(active0, 0x880L);
      case 78:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000L);
      case 83:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 13;
            jjmatchedPos = 5;
         }
         break;
      case 84:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active0, 0x500L);
      case 101:
         return jjMoveStringLiteralDfa6_0(active0, 0x880L);
      case 110:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000L);
      case 115:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 13;
            jjmatchedPos = 5;
         }
         break;
      case 116:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 5;
         }
         return jjMoveStringLiteralDfa6_0(active0, 0x500L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 5);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 5);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 5);
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa7_0(active0, 0xc80L);
      case 65:
         return jjMoveStringLiteralDfa7_0(active0, 0x100L);
      case 66:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 67:
         return jjMoveStringLiteralDfa7_0(active0, 0x1000L);
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x100L);
      case 98:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x1000L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 6);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 6);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 6);
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa8_0(active0, 0x100L);
      case 70:
         return jjMoveStringLiteralDfa8_0(active0, 0x800L);
      case 73:
         return jjMoveStringLiteralDfa8_0(active0, 0x400L);
      case 84:
         if ((active0 & 0x1000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active0, 0x80L);
      case 89:
         if ((active0 & 0x10000L) != 0L)
         {
            jjmatchedKind = 16;
            jjmatchedPos = 7;
         }
         break;
      case 98:
         return jjMoveStringLiteralDfa8_0(active0, 0x100L);
      case 102:
         return jjMoveStringLiteralDfa8_0(active0, 0x800L);
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x400L);
      case 116:
         if ((active0 & 0x1000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 7;
         }
         return jjMoveStringLiteralDfa8_0(active0, 0x80L);
      case 121:
         if ((active0 & 0x10000L) != 0L)
         {
            jjmatchedKind = 16;
            jjmatchedPos = 7;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(2, 7);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 7);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 7);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa9_0(active0, 0x80L);
      case 76:
         return jjMoveStringLiteralDfa9_0(active0, 0x100L);
      case 78:
         return jjMoveStringLiteralDfa9_0(active0, 0x400L);
      case 82:
         return jjMoveStringLiteralDfa9_0(active0, 0x800L);
      case 97:
         return jjMoveStringLiteralDfa9_0(active0, 0x80L);
      case 108:
         return jjMoveStringLiteralDfa9_0(active0, 0x100L);
      case 110:
         return jjMoveStringLiteralDfa9_0(active0, 0x400L);
      case 114:
         return jjMoveStringLiteralDfa9_0(active0, 0x800L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 8);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 8);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 8);
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa10_0(active0, 0x80L);
      case 69:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 8;
            jjmatchedPos = 9;
         }
         break;
      case 79:
         return jjMoveStringLiteralDfa10_0(active0, 0x800L);
      case 84:
         return jjMoveStringLiteralDfa10_0(active0, 0x400L);
      case 98:
         return jjMoveStringLiteralDfa10_0(active0, 0x80L);
      case 101:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 8;
            jjmatchedPos = 9;
         }
         break;
      case 111:
         return jjMoveStringLiteralDfa10_0(active0, 0x800L);
      case 116:
         return jjMoveStringLiteralDfa10_0(active0, 0x400L);
      default :
         break;
   }
   return jjMoveNfa_0(2, 9);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 9);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 9);
   }
   switch(curChar)
   {
      case 76:
         return jjMoveStringLiteralDfa11_0(active0, 0x80L);
      case 77:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 10;
         }
         break;
      case 79:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 10;
            jjmatchedPos = 10;
         }
         break;
      case 108:
         return jjMoveStringLiteralDfa11_0(active0, 0x80L);
      case 109:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 10;
         }
         break;
      case 111:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 10;
            jjmatchedPos = 10;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(2, 10);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(2, 10);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(2, 10);
   }
   switch(curChar)
   {
      case 69:
         if ((active0 & 0x80L) != 0L)
         {
            jjmatchedKind = 7;
            jjmatchedPos = 11;
         }
         break;
      case 101:
         if ((active0 & 0x80L) != 0L)
         {
            jjmatchedKind = 7;
            jjmatchedPos = 11;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(2, 11);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int strKind = jjmatchedKind;
   int strPos = jjmatchedPos;
   int seenUpto;
   input_stream.backup(seenUpto = curPos + 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { throw new Error("Internal Error"); }
   curPos = 0;
   int startsAt = 0;
   jjnewStateCnt = 13;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 19)
                        kind = 19;
                     jjCheckNAdd(10);
                  }
                  else if ((0x7000000000000000L & l) != 0L)
                  {
                     if (kind > 38)
                        kind = 38;
                  }
                  break;
               case 3:
                  if (curChar == 48 && kind > 18)
                     kind = 18;
                  break;
               case 4:
                  if (curChar == 50)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 8:
                  if ((0x7000000000000000L & l) != 0L)
                     kind = 38;
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAdd(10);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 20)
                     kind = 20;
                  jjCheckNAdd(10);
                  break;
               case 12:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjstateSet[jjnewStateCnt++] = 12;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 2:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 21)
                        kind = 21;
                     jjCheckNAdd(12);
                  }
                  if ((0x8000000080000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 6;
                  else if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 0:
                  if ((0x10000000100000L & l) != 0L && kind > 18)
                     kind = 18;
                  break;
               case 1:
                  if ((0x400000004000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 5:
                  if ((0x4000000040000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if ((0x10000000100000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if ((0x8000000080000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 11:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 21)
                     kind = 21;
                  jjCheckNAdd(12);
                  break;
               case 12:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 22)
                     kind = 22;
                  jjCheckNAdd(12);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 13 - (jjnewStateCnt = startsAt)))
         break;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { break; }
   }
   if (jjmatchedPos > strPos)
      return curPos;

   int toRet = Math.max(curPos, seenUpto);

   if (curPos < toRet)
      for (i = toRet - Math.min(curPos, seenUpto); i-- > 0; )
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) { throw new Error("Internal Error : Please send a bug report."); }

   if (jjmatchedPos < strPos)
   {
      jjmatchedKind = strKind;
      jjmatchedPos = strPos;
   }
   else if (jjmatchedPos == strPos && jjmatchedKind > strKind)
      jjmatchedKind = strKind;

   return toRet;
}
static final int[] jjnextStates = {
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, "\50", "\51", "\173", 
"\175", "\133", "\135", "\54", "\56", "\53", "\55", "\52", "\57", "\74", "\76", "\75", 
null, "\42", "\116\117\124\40", "\101\116\104\40", "\117\122\40", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7ffffffff81L, 
};
static final long[] jjtoSkip = {
   0x7eL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[13];
private final int[] jjstateSet = new int[26];
protected char curChar;
/** Constructor. */
public TinyParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public TinyParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 13; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
