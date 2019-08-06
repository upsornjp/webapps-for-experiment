/** *****************************************************************
    check24online.java
     Servlet version of check24.

        @author Jeff Offutt
        @version 1.0 (5/2/2005)
********************************************************************* */
// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;
import java.util.StringTokenizer;

public class check24online extends HttpServlet
{

/** *****************************************************
 *  Replaces main() in the command-line version
 *  Gets the four values from the form and calls
 *  the method checkSolutions() to get all solutions.
********************************************************* */
public void doPost (HttpServletRequest req, HttpServletResponse res)
   throws ServletException, IOException
{
   int Ain=1, Bin=1, Cin=1, Din=1;
   res.setContentType ("TEXT/HTML");
   PrintWriter out = res.getWriter ();

   PrintHead (out);

   String val1AsStr = req.getParameter ("value1");
   String val2AsStr = req.getParameter ("value2");
   String val3AsStr = req.getParameter ("value3");
   String val4AsStr = req.getParameter ("value4");

   try {
      Ain = Integer.parseInt (val1AsStr);
      Bin = Integer.parseInt (val2AsStr);
      Cin = Integer.parseInt (val3AsStr);
      Din = Integer.parseInt (val4AsStr);
   } catch (NumberFormatException e)
   {
      // nonnumeric, ask the user to try again.
      out.println ("<DIV Style=\"font-size:115%; color:red; text-align:justify\">");
      out.println ("One or more of your entries was not numeric.");
      out.println ("Please use only integers, no characters or spaces.");
      out.println ("</DIV>");
      out.println ("<A href=\"http://cs.gmu.edu:8080/offutt/servlet/check24online\">Return to game</A>.");
      //out.println ("<A href=\"http://cs.gmu.edu:8080/uprapham/servlet/check24online\">Return to game</A>.");
      out.println ("</BODY>");
      out.println ("</HTML>");
      out.close ();
      return;
   }

   out.println ("<FONT Color=blue><B>Searching for solutions ...</B></FONT>");
   out.println ("<BR>");
   out.println ("<HR>");
   out.println ("<BR>");

   // Try with all combinations.
   checkSolutions (Ain, Bin, Cin, Din, out);
   checkSolutions (Ain, Bin, Din, Cin, out);
   checkSolutions (Ain, Cin, Bin, Din, out);
   checkSolutions (Ain, Cin, Din, Bin, out);
   checkSolutions (Ain, Din, Bin, Cin, out);
   checkSolutions (Ain, Din, Cin, Bin, out);

   checkSolutions (Bin, Ain, Cin, Din, out);
   checkSolutions (Bin, Ain, Din, Cin, out);
   checkSolutions (Bin, Cin, Ain, Din, out);
   checkSolutions (Bin, Cin, Din, Ain, out);
   checkSolutions (Bin, Din, Ain, Cin, out);
   checkSolutions (Bin, Din, Cin, Ain, out);

   checkSolutions (Cin, Ain, Bin, Din, out);
   checkSolutions (Cin, Ain, Din, Bin, out);
   checkSolutions (Cin, Bin, Ain, Din, out);
   checkSolutions (Cin, Bin, Din, Ain, out);
   checkSolutions (Cin, Din, Ain, Bin, out);
   checkSolutions (Cin, Din, Bin, Ain, out);

   checkSolutions (Din, Ain, Cin, Bin, out);
   checkSolutions (Din, Ain, Bin, Cin, out);
   checkSolutions (Din, Cin, Ain, Bin, out);
   checkSolutions (Din, Cin, Bin, Ain, out);
   checkSolutions (Din, Bin, Ain, Cin, out);
   checkSolutions (Din, Bin, Cin, Ain, out);

   out.println ("<HR>");
   out.println ("<FONT Color=blue><B>Try another ...</B></FONT><BR>");
   PrintForm (out);
   out.println ("</BODY>");
   out.println ("</HTML>");
   out.close ();
}

/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Reprints the HTML page, blank.
********************************************************* */
public void doGet (HttpServletRequest req,
                    HttpServletResponse res)
   throws ServletException, IOException
{  // Prints the form, added for completeness.
   res.setContentType ("TEXT/HTML");
   PrintWriter out = res.getWriter ();
   PrintHead (out);
   PrintForm (out);
   PrintTail (out);
}

/** *****************************************************
 *  Prints the head of the HTML response page
********************************************************* */
private void PrintHead (PrintWriter out)
   throws IOException
{
   out.println ("<HTML>");
   out.println ("<HEAD>");
   out.println (" <TITLE>The Card Game of 24</TITLE>");
   out.println (" <STYLE>");
   out.println ("  P{text-align:justify}");
   out.println (" </STYLE>");

   out.println (" <SCRIPT LANGUAGE=\"JavaScript\">");
   out.println ("");
   out.println (" <!--");
   out.println (" // Function borrowed from Thilo Rusche");
   out.println (" function ClearForm ()");
   out.println (" {  // Set all the form values to blank.");
   out.println ("    var form = document.forms[0];");
   out.println ("    for (i=0; i < form.elements.length; i++)");
   out.println ("    {");
   out.println ("       if (form.elements[i].type != \"submit\" &&");
   out.println ("           form.elements[i].type != \"reset\")");
   out.println ("           form.elements[i].type != \"clear\")");
   out.println ("          form.elements[i].value = \"\";");
   out.println ("    }");
   out.println ("  return false;");
   out.println (" } // end ClearForm()");
   out.println (" function setFocus()");
   out.println (" {");
   out.println ("    document.check24form.value1.focus();");
   out.println (" }");

   out.println (" //-->");
   out.println (" </SCRIPT>");

   out.println ("</HEAD>");
   out.println ("<BODY  onLoad=\"setFocus()\" BGCOLOR=\"#DDEEDD\"> <!-- Very gentle green -->");
   out.println ("   ");
   out.println ("<CENTER><H2>The Card Game of 24</H2></CENTER>");
   out.println ("<HR>");
}

/** *****************************************************
 *  Prints the form of the HTML page.
 *  Also prints out the bottom and closes the PrintWriter.
********************************************************* */
private void PrintForm (PrintWriter out)
   throws IOException
{  // Reprints the form, just like the original HTML
   out.println ("<FORM METHOD=\"post\" ACTION=\"http://cs.gmu.edu:8080/offutt/servlet/check24online\" name=\"check24form\">");
   //out.println ("<FORM METHOD=\"post\" ACTION=\"http://cs.gmu.edu:8080/uprapham/servlet/check24online\" name=\"check24form\">");	
   out.println ("<P>");
   out.println ("Please enter four integer values between 1 and 24 inclusive.");
   out.println ("This program will return all expressions using the four operators");
   out.println ("+, -, * and / that use each value exactly once and that have the result 24.");
   out.println ("There may be a lot of what looks like duplicates,");
   out.println ("but they are actually different ways of rearranging the numbers.");
   out.println ("(This program can actually handle all integers.)");
   out.println ("");
   out.println ("<P>");
   out.println ("<TABLE CELLSPACING=0 CELLPADDING=5 BORDER=0 ALIGN=center>");
   out.println ("");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Value 1:</B> <INPUT TYPE=\"text\" NAME=\"value1\" SIZE=3></TD>");
   out.println ("  <TD><B>Value 2:</B> <INPUT TYPE=\"text\" NAME=\"value2\" SIZE=3></TD>");
   out.println ("  <TD><B>Value 3:</B> <INPUT TYPE=\"text\" NAME=\"value3\" SIZE=3></TD>");
   out.println ("  <TD><B>Value 4:</B> <INPUT TYPE=\"text\" NAME=\"value4\" SIZE=3></TD>");
   out.println (" </TR>");
   out.println (" </TABLE>");
   out.println ("");
   out.println (" <TABLE CELLSPACING=0 CELLPADDING=0 BORDER=0 ALIGN=center WIDTH=\"50%\">");
   out.println ("  <TR ALIGN=center>");
   out.println ("   <TD><INPUT NAME=\"submit\" TYPE=\"submit\" value=\"Check 24\"></TD>");
   out.println ("   <TD><INPUT NAME=\"clear\" TYPE=\"button\" value=\"Clear Form\"");
   out.println ("              onClick=\"ClearForm()\"></TD>");
   out.println ("  </TR>");
   out.println (" </TABLE>");
   out.println ("");
   out.println ("</FORM>");
   out.println ("");
}

/** *****************************************************
 *  Prints the tail of the HTML page.
 *  Prints out the bottom and closes the PrintWriter.
********************************************************* */
private void PrintTail (PrintWriter out)
   throws IOException
{
   out.println ("<BR>");
   out.println ("<BR>");
   out.println ("<BR>");
   out.println ("<BR>");
   out.println ("<BR>");
   out.println ("<BR>");
   out.println ("<BR>");
   out.println ("");
   out.println ("<HR>");
   out.println ("<P>");
   out.println ("<U>About 24</U>:");
   out.println ("<P>");
   out.println ("<DIV Align=\"right\" Style=\"font-size:75%; text-align:justify\">");
   out.println ("The basic game of 24 is generations old.");
   out.println ("It has been played in Asian countries, in particular China,");
   out.println ("since at least the mid-20th century.");
   out.println ("There, it was usually played with regular playing (poker) cards and");
   out.println ("using numbers from 1 (aces) through 13 (kings).");
   out.println ("Robert Sun created a variant of the game that");
   out.println ("used specialized cards with four numbers per card,");
   out.println ("each of which is guaranteed to have a solution.");
   out.println ("He also created other variations and markets it in the USA");
   out.println ("with his company Suntex");
   out.println ("(<A href=\"http://www.24game.com/\">http://www.24game.com/</A>)");
   out.println ("as a teaching tool and for competition among elementary school students.");
   out.println ("Their website begins the description of the game as follows:");
   out.println ("");
   out.println ("<UL>");
   out.println ("<I>");
   out.println ("\"In 1988, successful inventor Robert Sun embarked on a journey to teach");
   out.println ("children the relationship between numbers through a game. The result of");
   out.println ("his efforts was the 24&reg game, a unique mathematics teaching tool that");
   out.println ("has proven to successfully engage students in grades 1 through 9 ...\"");
   out.println ("</I>");
   out.println ("</UL>");
   out.println ("");
   out.println ("<P>");
   out.println ("Mathematically, the game comes out of number theory,");
   out.println ("specifically the theory of \"<I>abundant numbers</I>\".");
   out.println ("An abundant number is one for which the sum of its divisors are greater");
   out.println ("than the number itself.");
   out.println ("So, &Sigma divisors(24) = 2+3+4+6+8+12 = 35.");
   out.println ("This makes 24 a great candidate for this game");
   out.println ("(although 12, 36, and 48 might be reasonable alternatives).");
   out.println ("More details on abundant numbers can be found on");
   out.println ("<A href=\"http://mathworld.wolfram.com/AbundantNumber.html\">mathworld</A>.");
   out.println ("<HR>");

   out.println ("</BODY>");
   out.println ("</HTML>");
   out.close ();
}


// **********************************************
// 936 combinations - strictly brute force.
// All operators in all orders, with all parenthizations 
// Uses exception block around all divides 
// Checks for roundoff errors by comparing with a float value
// (that is, 23.8 is not really a solution) 
//
// I can't guarantee all cases are correct - think about
// trying to branch test this program!!
// **********************************************
private static void checkSolutions (int a, int b, int c, int d, PrintWriter out)
{
   int rslt;

   /**** + + + ***/
   rslt = a+b+c+d;
   if (rslt == 24 &&  rslt == (float)a+(float)b+(float)c+(float)d)
      out.println ("Solution found: "+a+"+"+b+"+"+c+"+"+d+" = 24<BR>");

   /**** + + ? ***/
   rslt = a+b+c-d;
   if (rslt == 24 &&  rslt == (float)a+(float)b+(float)c-(float)d)
      out.println ("Solution found: "+a+"+"+b+"+"+c+"-"+d+" = 24<BR>");

   rslt = a+b+c*d;
   if (rslt == 24 &&  rslt == (float)a+(float)b+(float)c*(float)d)
      out.println ("Solution found: "+a+"+"+b+"+"+c+"*"+d+" = 24<BR>");

   rslt = (a+b+c)*d;
   if (rslt == 24 &&  rslt == ((float)a+(float)b+(float)c)*(float)d)
      out.println ("Solution found: ("+a+"+"+b+"+"+c+")*"+d+" = 24<BR>");

   try {
      rslt = a+b+c/d;
      if (rslt == 24 &&  rslt == (float)a+(float)b+(float)c/(float)d)
         out.println ("Solution found: "+a+"+"+b+"+"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b+c)/d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b+(float)c)/(float)d)
         out.println ("Solution found: ("+a+"+"+b+"+"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** + - ? ***/
   rslt = a+b-c+d;
   if (rslt == 24 &&  rslt == (float)a+(float)b-(float)c+(float)d)
      out.println ("Solution found: "+a+"+"+b+"-"+c+"+"+d+" = 24<BR>");

   rslt = a+b-c-d;
   if (rslt == 24 &&  rslt == (float)a+(float)b-(float)c-(float)d)
      out.println ("Solution found: "+a+"+"+b+"-"+c+"-"+d+" = 24<BR>");

   rslt = a+b-c*d;
   if (rslt == 24 &&  rslt == (float)a+(float)b-(float)c*(float)d)
      out.println ("Solution found: "+a+"+"+b+"-"+c+"*"+d+" = 24<BR>");

   rslt = (a+b-c)*d;
   if (rslt == 24 &&  rslt == ((float)a+(float)b-(float)c)*(float)d)
      out.println ("Solution found: ("+a+"+"+b+"-"+c+")*"+d+" = 24<BR>");

   try {
      rslt = a+b-c/d;
      if (rslt == 24 &&  rslt == (float)a+(float)b-(float)c/(float)d)
         out.println ("Solution found: "+a+"+"+b+"-"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b-c)/d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b-(float)c)/(float)d)
         out.println ("Solution found: ("+a+"+"+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** + * ? ***/
   rslt = a+b*c+d;
   if (rslt == 24 &&  rslt == (float)a+(float)b*(float)c+(float)d)
      out.println ("Solution found: "+a+"+"+b+"*"+c+"+"+d+" = 24<BR>");

   rslt = (a+b)*(c+d);
   if (rslt == 24 &&  rslt == ((float)a+(float)b)*((float)c+(float)d))
      out.println ("Solution found: ("+a+"+"+b+")*("+c+"+"+d+") = 24<BR>");

   rslt = a+b*c-d;
   if (rslt == 24 &&  rslt == (float)a+(float)b*(float)c-(float)d)
      out.println ("Solution found: "+a+"+"+b+"*"+c+"-"+d+" = 24<BR>");

   rslt = (a+b)*(c-d);
   if (rslt == 24 &&  rslt == ((float)a+(float)b)*((float)c-(float)d))
      out.println ("Solution found: ("+a+"+"+b+")*("+c+"-"+d+") = 24<BR>");

   rslt = a+b*c*d;
   if (rslt == 24 &&  rslt == (float)a+(float)b*(float)c*(float)d)
      out.println ("Solution found: "+a+"+"+b+"*"+c+"*"+d+" = 24<BR>");

   rslt = (a+b)*c*d;
   if (rslt == 24 &&  rslt == ((float)a+(float)b)*(float)c*(float)d)
      out.println ("Solution found: ("+a+"+"+b+")*"+c+"*"+d+" = 24<BR>");

   try {
      rslt = a+b*c/d;
      if (rslt == 24 &&  rslt == (float)a+(float)b*(float)c/(float)d)
         out.println ("Solution found: "+a+"+"+b+"*"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)*c/d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b)*(float)c/(float)d)
         out.println ("Solution found: ("+a+"+"+b+")*"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b*(c/d);
      if (rslt == 24 &&  rslt == (float)a+(float)b*((float)c/(float)d))
         out.println ("Solution found: "+a+"+"+b+"*("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)*(c/d);
      if (rslt == 24 &&  rslt == ((float)a+(float)b)*((float)c/(float)d))
         out.println ("Solution found: ("+a+"+"+b+")*("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** + / ? ***/
   try {
      rslt = a+b/c+d;
      if (rslt == 24 &&  rslt == (float)a+(float)b/(float)c+(float)d)
         out.println ("Solution found: "+a+"+"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/(c+d);
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/((float)c+(float)d))
         out.println ("Solution found: ("+a+"+"+b+")/("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/c+d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/(float)c+(float)d)
         out.println ("Solution found: ("+a+"+"+b+")/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/(c+d);
      if (rslt == 24 &&  rslt == (float)a+(float)b/((float)c+(float)d))
         out.println ("Solution found: "+a+"+"+b+"/("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/c-d;
      if (rslt == 24 &&  rslt == (float)a+(float)b/(float)c-(float)d)
         out.println ("Solution found: "+a+"+"+b+"/"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/(c-d);
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/((float)c-(float)d))
         out.println ("Solution found: ("+a+"+"+b+")/("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/(c-d);
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/((float)c-(float)d))
         out.println ("Solution found: ("+a+"+"+b+")/("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/c-d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/(float)c-(float)d)
         out.println ("Solution found: ("+a+"+"+b+")/"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/(c-d);
      if (rslt == 24 &&  rslt == (float)a+(float)b/((float)c-(float)d))
         out.println ("Solution found: "+a+"+"+b+"/("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/c*d;
      if (rslt == 24 &&  rslt == (float)a+(float)b/(float)c*(float)d)
         out.println ("Solution found: "+a+"+"+b+"/"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/(c*d);
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/((float)c*(float)d))
         out.println ("Solution found: ("+a+"+"+b+")/("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/c*d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/(float)c*(float)d)
         out.println ("Solution found: ("+a+"+"+b+")/"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/(c*d);
      if (rslt == 24 &&  rslt == (float)a+(float)b/((float)c*(float)d))
         out.println ("Solution found: "+a+"+"+b+"/("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/c/d;
      if (rslt == 24 &&  rslt == (float)a+(float)b/(float)c/(float)d)
         out.println ("Solution found: "+a+"+"+b+"/"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/(c/d);
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/((float)c/(float)d))
         out.println ("Solution found: ("+a+"+"+b+")/("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = (a+b)/c/d;
      if (rslt == 24 &&  rslt == ((float)a+(float)b)/(float)c/(float)d)
         out.println ("Solution found: ("+a+"+"+b+")/"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a+b/(c/d);
      if (rslt == 24 &&  rslt == (float)a+(float)b/((float)c/(float)d))
         out.println ("Solution found: "+a+"+"+b+"/("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }


   /**** - + + ***/
   rslt = a-b+c+d;
   if (rslt == 24 &&  rslt == (float)a-(float)b+(float)c+(float)d)
      out.println ("Solution found: "+a+"-"+b+"+"+c+"+"+d+" = 24<BR>");
   rslt = a-(b+c+d);
   if (rslt == 24 &&  rslt == (float)a-((float)b+(float)c+(float)d))
      out.println ("Solution found: "+a+"-("+b+"+"+c+"+"+d+") = 24<BR>");

   /**** - + ? ***/
   rslt = a-b+c-d;
   if (rslt == 24 &&  rslt == (float)a-(float)b+(float)c-(float)d)
      out.println ("Solution found: "+a+"-"+b+"+"+c+"-"+d+" = 24<BR>");
   rslt = a-(b+c)-d;
   if (rslt == 24 &&  rslt == (float)a-((float)b+(float)c)-(float)d)
      out.println ("Solution found: "+a+"-("+b+"+"+c+")-"+d+" = 24<BR>");
   rslt = a-((b+c)-d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b+(float)c)-(float)d))
      out.println ("Solution found: "+a+"-(("+b+"+"+c+")-"+d+") = 24<BR>");

   rslt = a-b+c*d;
   if (rslt == 24 &&  rslt == (float)a-(float)b+(float)c*(float)d)
      out.println ("Solution found: "+a+"-"+b+"+"+c+"*"+d+" = 24<BR>");
   rslt = (a-b+c)*d;
   if (rslt == 24 &&  rslt == ((float)a-(float)b+(float)c)*(float)d)
      out.println ("Solution found: ("+a+"-"+b+"+"+c+")*"+d+" = 24<BR>");
   rslt = (a-(b+c))*d;
   if (rslt == 24 &&  rslt == ((float)a-((float)b+(float)c))*(float)d)
      out.println ("Solution found: ("+a+"-("+b+"+"+c+"))*"+d+" = 24<BR>");

   try {
      rslt = a-b+c/d;
      if (rslt == 24 &&  rslt == (float)a-(float)b+(float)c/(float)d)
         out.println ("Solution found: "+a+"-"+b+"+"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b+c)/d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b+(float)c)/(float)d)
         out.println ("Solution found: ("+a+"-"+b+"+"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b+c))/d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b+(float)c))/(float)d)
         out.println ("Solution found: ("+a+"-("+b+"+"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** - - ? ***/
   rslt = a-b-c+d;
   if (rslt == 24 &&  rslt == (float)a-(float)b-(float)c+(float)d)
      out.println ("Solution found: "+a+"-"+b+"-"+c+"+"+d+" = 24<BR>");
   rslt = a-(b-c)+d;
   if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)+(float)d)
      out.println ("Solution found: "+a+"-("+b+"-"+c+")+"+d+" = 24<BR>");
   rslt = a-b-(c+d);
   if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c+(float)d))
      out.println ("Solution found: "+a+"-"+b+"-("+c+"+"+d+") = 24<BR>");
   rslt = a-(b-(c+d));
   if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c+(float)d)))
      out.println ("Solution found: "+a+"-("+b+"-("+c+"+"+d+")) = 24<BR>");
   rslt = a-((b-c)+d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)+(float)d))
      out.println ("Solution found: "+a+"-(("+b+"-"+c+")+"+d+") = 24<BR>");

   rslt = a-b-c-d;
   if (rslt == 24 &&  rslt == (float)a-(float)b-(float)c-(float)d)
      out.println ("Solution found: "+a+"-"+b+"-"+c+"-"+d+" = 24<BR>");
   rslt = a-(b-c)-d;
   if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)-(float)d)
      out.println ("Solution found: "+a+"-("+b+"-"+c+")-"+d+" = 24<BR>");
   rslt = a-b-(c-d);
   if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c-(float)d))
      out.println ("Solution found: "+a+"-"+b+"-("+c+"-"+d+") = 24<BR>");
   rslt = a-(b-(c-d));
   if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c-(float)d)))
      out.println ("Solution found: "+a+"-("+b+"-("+c+"-"+d+")) = 24<BR>");
   rslt = a-((b-c)-d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)-(float)d))
      out.println ("Solution found: "+a+"-(("+b+"-"+c+")-"+d+") = 24<BR>");

   rslt = a-b-c*d;
   if (rslt == 24 &&  rslt == (float)a-(float)b-(float)c*(float)d)
      out.println ("Solution found: "+a+"-"+b+"-"+c+"*"+d+" = 24<BR>");
   rslt = a-(b-c)*d;
   if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)*(float)d)
      out.println ("Solution found: "+a+"-("+b+"-"+c+")*"+d+" = 24<BR>");
   rslt = a-b-(c*d);
   if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c*(float)d))
      out.println ("Solution found: "+a+"-"+b+"-("+c+"*"+d+") = 24<BR>");
   rslt = a-(b-(c*d));
   if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c*(float)d)))
      out.println ("Solution found: "+a+"-("+b+"-("+c+"*"+d+")) = 24<BR>");
   rslt = a-((b-c)*d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)*(float)d))
      out.println ("Solution found: "+a+"-(("+b+"-"+c+")*"+d+") = 24<BR>");
   rslt = (a-b-c)*d;
   if (rslt == 24 &&  rslt == ((float)a-(float)b-(float)c)*(float)d)
      out.println ("Solution found: ("+a+"-"+b+"-"+c+")*"+d+" = 24<BR>");
   rslt = (a-(b-c))*d;
   if (rslt == 24 &&  rslt == ((float)a-((float)b-(float)c))*(float)d)
      out.println ("Solution found: ("+a+"-("+b+"-"+c+"))*"+d+" = 24<BR>");

   try {
      rslt = a-b-c/d;
      if (rslt == 24 &&  rslt == (float)a-(float)b-(float)c/(float)d)
         out.println ("Solution found: "+a+"-"+b+"-"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b-c)/d;
      if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)/(float)d)
         out.println ("Solution found: "+a+"-("+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-b-(c/d);
      if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c/(float)d))
         out.println ("Solution found: "+a+"-"+b+"-("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b-(c/d));
      if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c/(float)d)))
         out.println ("Solution found: "+a+"-("+b+"-("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-((b-c)/d);
      if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)/(float)d))
         out.println ("Solution found: "+a+"-(("+b+"-"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b-c)/d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b-(float)c)/(float)d)
         out.println ("Solution found: ("+a+"-"+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b-c))/d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b-(float)c))/(float)d)
         out.println ("Solution found: ("+a+"-("+b+"-"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** - * ? ***/
   rslt = a-b*c+d;
   if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c+(float)d)
      out.println ("Solution found: "+a+"-"+b+"*"+c+"+"+d+" = 24<BR>");
   rslt = a-(b*c)+d;
   if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)+(float)d)
      out.println ("Solution found: "+a+"-("+b+"*"+c+")+"+d+" = 24<BR>");
   rslt = a-b*(c+d);
   if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c+(float)d))
      out.println ("Solution found: "+a+"-"+b+"*("+c+"+"+d+") = 24<BR>");
   rslt = a-(b*(c+d));
   if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c+(float)d)))
      out.println ("Solution found: "+a+"-("+b+"*("+c+"+"+d+")) = 24<BR>");
   rslt = a-((b*c)+d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)+(float)d))
      out.println ("Solution found: "+a+"-(("+b+"*"+c+")+"+d+") = 24<BR>");
   rslt = (a-b*c)+d;
   if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)+(float)d)
      out.println ("Solution found: ("+a+"-"+b+"*"+c+")+"+d+" = 24<BR>");
   rslt = (a-(b*c))+d;
   if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))+(float)d)
      out.println ("Solution found: ("+a+"-("+b+"*"+c+"))+"+d+" = 24<BR>");

   rslt = a-b*c-d;
   if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c-(float)d)
      out.println ("Solution found: "+a+"-"+b+"*"+c+"-"+d+" = 24<BR>");
   rslt = a-(b*c)-d;
   if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)-(float)d)
      out.println ("Solution found: "+a+"-("+b+"*"+c+")-"+d+" = 24<BR>");
   rslt = a-b*(c-d);
   if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c-(float)d))
      out.println ("Solution found: "+a+"-"+b+"*("+c+"-"+d+") = 24<BR>");
   rslt = a-(b*(c-d));
   if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c-(float)d)))
      out.println ("Solution found: "+a+"-("+b+"*("+c+"-"+d+")) = 24<BR>");
   rslt = a-((b*c)-d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)-(float)d))
      out.println ("Solution found: "+a+"-(("+b+"*"+c+")-"+d+") = 24<BR>");
   rslt = (a-b*c)-d;
   if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)-(float)d)
      out.println ("Solution found: ("+a+"-"+b+"*"+c+")-"+d+" = 24<BR>");
   rslt = (a-(b*c))-d;
   if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))-(float)d)
      out.println ("Solution found: ("+a+"-("+b+"*"+c+"))-"+d+" = 24<BR>");

   rslt = a-b*c*d;
   if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c*(float)d)
      out.println ("Solution found: "+a+"-"+b+"*"+c+"*"+d+" = 24<BR>");
   rslt = a-(b*c)*d;
   if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)*(float)d)
      out.println ("Solution found: "+a+"-("+b+"*"+c+")*"+d+" = 24<BR>");
   rslt = a-b*(c*d);
   if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c*(float)d))
      out.println ("Solution found: "+a+"-"+b+"*("+c+"*"+d+") = 24<BR>");
   rslt = a-(b*(c*d));
   if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c*(float)d)))
      out.println ("Solution found: "+a+"-("+b+"*("+c+"*"+d+")) = 24<BR>");
   rslt = a-((b*c)*d);
   if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)*(float)d))
      out.println ("Solution found: "+a+"-(("+b+"*"+c+")*"+d+") = 24<BR>");
   rslt = (a-b*c)*d;
   if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)*(float)d)
      out.println ("Solution found: ("+a+"-"+b+"*"+c+")*"+d+" = 24<BR>");
   rslt = (a-(b*c))*d;
   if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))*(float)d)
      out.println ("Solution found: ("+a+"-("+b+"*"+c+"))*"+d+" = 24<BR>");

   try {
      rslt = a-b*c/d;
      if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c/(float)d)
         out.println ("Solution found: "+a+"-"+b+"*"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b*c)/d;
      if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)/(float)d)
         out.println ("Solution found: "+a+"-("+b+"*"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-b*(c/d);
      if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c/(float)d))
         out.println ("Solution found: "+a+"-"+b+"*("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b*(c/d));
      if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c/(float)d)))
         out.println ("Solution found: "+a+"-("+b+"*("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-((b*c)/d);
      if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)/(float)d))
         out.println ("Solution found: "+a+"-(("+b+"*"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b*c)/d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)/(float)d)
         out.println ("Solution found: ("+a+"-"+b+"*"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b*c))/d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))/(float)d)
         out.println ("Solution found: ("+a+"-("+b+"*"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** - / ? ***/
   try {
      rslt = a-b/c+d;
      if (rslt == 24 &&  rslt == (float)a-(float)b/(float)c+(float)d)
         out.println ("Solution found: "+a+"-"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/c)+d;
      if (rslt == 24 &&  rslt == (float)a-((float)b/(float)c)+(float)d)
         out.println ("Solution found: "+a+"-("+b+"/"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-b/(c+d);
      if (rslt == 24 &&  rslt == (float)a-(float)b/((float)c+(float)d))
         out.println ("Solution found: "+a+"-"+b+"/("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/(c+d));
      if (rslt == 24 &&  rslt == (float)a-((float)b/((float)c+(float)d)))
         out.println ("Solution found: "+a+"-("+b+"/("+c+"+"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-((b/c)+d);
      if (rslt == 24 &&  rslt == (float)a-(((float)b/(float)c)+(float)d))
         out.println ("Solution found: "+a+"-(("+b+"/"+c+")+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b/c)+d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b/(float)c)+(float)d)
         out.println ("Solution found: ("+a+"-"+b+"/"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b/c))+d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b/(float)c))/(float)d)
         out.println ("Solution found: ("+a+"-("+b+"/"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a-b/c-d;
      if (rslt == 24 &&  rslt == (float)a-(float)b/(float)c-(float)d)
         out.println ("Solution found: "+a+"-"+b+"/"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/c)-d;
      if (rslt == 24 &&  rslt == (float)a-((float)b/(float)c)-(float)d)
         out.println ("Solution found: "+a+"-("+b+"/"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-b/(c-d);
      if (rslt == 24 &&  rslt == (float)a-(float)b/((float)c-(float)d))
         out.println ("Solution found: "+a+"-"+b+"/("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/(c-d));
      if (rslt == 24 &&  rslt == (float)a-((float)b/((float)c-(float)d)))
         out.println ("Solution found: "+a+"-("+b+"/("+c+"-"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-((b/c)-d);
      if (rslt == 24 &&  rslt == (float)a-(((float)b/(float)c)-(float)d))
         out.println ("Solution found: "+a+"-(("+b+"/"+c+")-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b/c)-d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b/(float)c)-(float)d)
         out.println ("Solution found: ("+a+"-"+b+"/"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b/c))-d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b/(float)c))-(float)d)
         out.println ("Solution found: ("+a+"-("+b+"/"+c+"))-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a-b/c*d;
      if (rslt == 24 &&  rslt == (float)a-(float)b/(float)c*(float)d)
         out.println ("Solution found: "+a+"-"+b+"/"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/c)*d;
      if (rslt == 24 &&  rslt == (float)a-((float)b/(float)c)*(float)d)
         out.println ("Solution found: "+a+"-("+b+"/"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-b/(c*d);
      if (rslt == 24 &&  rslt == (float)a-(float)b/((float)c*(float)d))
         out.println ("Solution found: "+a+"-"+b+"/("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/(c*d));
      if (rslt == 24 &&  rslt == (float)a-((float)b/((float)c*(float)d)))
         out.println ("Solution found: "+a+"-("+b+"/("+c+"*"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-((b/c)*d);
      if (rslt == 24 &&  rslt == (float)a-(((float)b/(float)c)*(float)d))
         out.println ("Solution found: "+a+"-(("+b+"/"+c+")*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b/c)*d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b/(float)c)*(float)d)
         out.println ("Solution found: ("+a+"-"+b+"/"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b/c))*d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b/(float)c))*(float)d)
         out.println ("Solution found: ("+a+"-("+b+"/"+c+"))*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a-b/c/d;
      if (rslt == 24 &&  rslt == (float)a-(float)b/(float)c/(float)d)
         out.println ("Solution found: "+a+"-"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/c)/d;
      if (rslt == 24 &&  rslt == (float)a-((float)b/(float)c)/(float)d)
         out.println ("Solution found: "+a+"-("+b+"/"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-b/(c/d);
      if (rslt == 24 &&  rslt == (float)a-(float)b/((float)c/(float)d))
         out.println ("Solution found: "+a+"-"+b+"/("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-(b/(c/d));
      if (rslt == 24 &&  rslt == (float)a-((float)b/((float)c/(float)d)))
         out.println ("Solution found: "+a+"-("+b+"/("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a-((b/c)/d);
      if (rslt == 24 &&  rslt == (float)a-(((float)b/(float)c)/(float)d))
         out.println ("Solution found: "+a+"-(("+b+"/"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-b/c)/d;
      if (rslt == 24 &&  rslt == ((float)a-(float)b/(float)c)/(float)d)
         out.println ("Solution found: ("+a+"-"+b+"/"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a-(b/c))/d;
      if (rslt == 24 &&  rslt == ((float)a-((float)b/(float)c))/(float)d)
         out.println ("Solution found: ("+a+"-("+b+"/"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** * + + ***/
   rslt = a*b+c+d;
   if (rslt == 24 &&  rslt == (float)a*(float)b+(float)c+(float)d)
      out.println ("Solution found: "+a+"*"+b+"+"+c+"+"+d+" = 24<BR>");
   rslt = a*(b+c+d);
   if (rslt == 24 &&  rslt == (float)a*((float)b+(float)c+(float)d))
      out.println ("Solution found: "+a+"*("+b+"+"+c+"+"+d+") = 24<BR>");

   /**** * + ? ***/
   rslt = a*b+c-d;
   if (rslt == 24 &&  rslt == (float)a*(float)b+(float)c-(float)d)
      out.println ("Solution found: "+a+"*"+b+"+"+c+"-"+d+" = 24<BR>");
   rslt = a*(b+c)-d;
   if (rslt == 24 &&  rslt == (float)a*((float)b+(float)c)-(float)d)
      out.println ("Solution found: "+a+"*("+b+"+"+c+")-"+d+" = 24<BR>");
   rslt = a*((b+c)-d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b+(float)c)-(float)d))
      out.println ("Solution found: "+a+"*(("+b+"+"+c+")-"+d+") = 24<BR>");

   rslt = a*b+c*d;
   if (rslt == 24 &&  rslt == (float)a*(float)b+(float)c*(float)d)
      out.println ("Solution found: "+a+"*"+b+"+"+c+"*"+d+" = 24<BR>");
   rslt = (a*b+c)*d;
   if (rslt == 24 &&  rslt == ((float)a*(float)b+(float)c)*(float)d)
      out.println ("Solution found: ("+a+"*"+b+"+"+c+")*"+d+" = 24<BR>");
   rslt = (a*(b+c))*d;
   if (rslt == 24 &&  rslt == ((float)a*((float)b+(float)c))*(float)d)
      out.println ("Solution found: ("+a+"*("+b+"+"+c+"))*"+d+" = 24<BR>");

   try {
      rslt = a*b+c/d;
      if (rslt == 24 &&  rslt == (float)a*(float)b+(float)c/(float)d)
         out.println ("Solution found: "+a+"*"+b+"+"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b+c)/d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b+(float)c)/(float)d)
         out.println ("Solution found: ("+a+"*"+b+"+"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b+c))/d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b+(float)c))/(float)d)
         out.println ("Solution found: ("+a+"*("+b+"+"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** * - ? ***/
   rslt = a*b-c+d;
   if (rslt == 24 &&  rslt == (float)a*(float)b-(float)c+(float)d)
      out.println ("Solution found: "+a+"*"+b+"-"+c+"+"+d+" = 24<BR>");
   rslt = a*(b-c)+d;
   if (rslt == 24 &&  rslt == (float)a*((float)b-(float)c)+(float)d)
      out.println ("Solution found: "+a+"*("+b+"-"+c+")+"+d+" = 24<BR>");
   rslt = a*b-(c+d);
   if (rslt == 24 &&  rslt == (float)a*(float)b-((float)c+(float)d))
      out.println ("Solution found: "+a+"*"+b+"-("+c+"+"+d+") = 24<BR>");
   rslt = a*(b-(c+d));
   if (rslt == 24 &&  rslt == (float)a*((float)b-((float)c+(float)d)))
      out.println ("Solution found: "+a+"*("+b+"-("+c+"+"+d+")) = 24<BR>");
   rslt = a*((b-c)+d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b-(float)c)+(float)d))
      out.println ("Solution found: "+a+"*(("+b+"-"+c+")+"+d+") = 24<BR>");

   rslt = a*b-c-d;
   if (rslt == 24 &&  rslt == (float)a*(float)b-(float)c-(float)d)
      out.println ("Solution found: "+a+"*"+b+"-"+c+"-"+d+" = 24<BR>");
   rslt = a*(b-c)-d;
   if (rslt == 24 &&  rslt == (float)a*((float)b-(float)c)-(float)d)
      out.println ("Solution found: "+a+"*("+b+"-"+c+")-"+d+" = 24<BR>");
   rslt = a*b-(c-d);
   if (rslt == 24 &&  rslt == (float)a*(float)b-((float)c-(float)d))
      out.println ("Solution found: "+a+"*"+b+"-("+c+"-"+d+") = 24<BR>");
   rslt = a*(b-(c-d));
   if (rslt == 24 &&  rslt == (float)a*((float)b-((float)c-(float)d)))
      out.println ("Solution found: "+a+"*("+b+"-("+c+"-"+d+")) = 24<BR>");
   rslt = a*((b-c)-d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b-(float)c)-(float)d))
      out.println ("Solution found: "+a+"*(("+b+"-"+c+")-"+d+") = 24<BR>");

   rslt = a*b-c*d;
   if (rslt == 24 &&  rslt == (float)a*(float)b-(float)c*(float)d)
      out.println ("Solution found: "+a+"*"+b+"-"+c+"*"+d+" = 24<BR>");
   rslt = a*(b-c)*d;
   if (rslt == 24 &&  rslt == (float)a*((float)b-(float)c)*(float)d)
      out.println ("Solution found: "+a+"*("+b+"-"+c+")*"+d+" = 24<BR>");
   rslt = a*b-(c*d);
   if (rslt == 24 &&  rslt == (float)a*(float)b-((float)c*(float)d))
      out.println ("Solution found: "+a+"*"+b+"-("+c+"*"+d+") = 24<BR>");
   rslt = a*(b-(c*d));
   if (rslt == 24 &&  rslt == (float)a*((float)b-((float)c*(float)d)))
      out.println ("Solution found: "+a+"*("+b+"-("+c+"*"+d+")) = 24<BR>");
   rslt = a*((b-c)*d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b-(float)c)*(float)d))
      out.println ("Solution found: "+a+"*(("+b+"-"+c+")*"+d+") = 24<BR>");
   rslt = (a*b-c)*d;
   if (rslt == 24 &&  rslt == ((float)a*(float)b-(float)c)*(float)d)
      out.println ("Solution found: ("+a+"*"+b+"-"+c+")*"+d+" = 24<BR>");
   rslt = (a*(b-c))*d;
   if (rslt == 24 &&  rslt == ((float)a*((float)b-(float)c))*(float)d)
      out.println ("Solution found: ("+a+"*("+b+"-"+c+"))*"+d+" = 24<BR>");

   try {
      rslt = a*b-c/d;
      if (rslt == 24 &&  rslt == (float)a*(float)b-(float)c/(float)d)
         out.println ("Solution found: "+a+"*"+b+"-"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b-c)/d;
      if (rslt == 24 &&  rslt == (float)a*((float)b-(float)c)/(float)d)
         out.println ("Solution found: "+a+"*("+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*b-(c/d);
      if (rslt == 24 &&  rslt == (float)a*(float)b-((float)c/(float)d))
         out.println ("Solution found: "+a+"*"+b+"-("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b-(c/d));
      if (rslt == 24 &&  rslt == (float)a*((float)b-((float)c/(float)d)))
         out.println ("Solution found: "+a+"*("+b+"-("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*((b-c)/d);
      if (rslt == 24 &&  rslt == (float)a*(((float)b-(float)c)/(float)d))
         out.println ("Solution found: "+a+"*(("+b+"-"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b-c)/d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b-(float)c)/(float)d)
         out.println ("Solution found: ("+a+"*"+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b-c))/d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b-(float)c))/(float)d)
         out.println ("Solution found: ("+a+"*("+b+"-"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** * * ? ***/
   rslt = a*b*c+d;
   if (rslt == 24 &&  rslt == (float)a*(float)b*(float)c+(float)d)
      out.println ("Solution found: "+a+"*"+b+"*"+c+"+"+d+" = 24<BR>");
   rslt = a*(b*c)+d;
   if (rslt == 24 &&  rslt == (float)a*((float)b*(float)c)+(float)d)
      out.println ("Solution found: "+a+"*("+b+"*"+c+")+"+d+" = 24<BR>");
   rslt = a*b*(c+d);
   if (rslt == 24 &&  rslt == (float)a*(float)b*((float)c+(float)d))
      out.println ("Solution found: "+a+"*"+b+"*("+c+"+"+d+") = 24<BR>");
   rslt = a*(b*(c+d));
   if (rslt == 24 &&  rslt == (float)a*((float)b*((float)c+(float)d)))
      out.println ("Solution found: "+a+"*("+b+"*("+c+"+"+d+")) = 24<BR>");
   rslt = a*((b*c)+d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b*(float)c)+(float)d))
      out.println ("Solution found: "+a+"*(("+b+"*"+c+")+"+d+") = 24<BR>");
   rslt = (a*b*c)+d;
   if (rslt == 24 &&  rslt == ((float)a*(float)b*(float)c)+(float)d)
      out.println ("Solution found: ("+a+"*"+b+"*"+c+")+"+d+" = 24<BR>");
   rslt = (a*(b*c))+d;
   if (rslt == 24 &&  rslt == ((float)a*((float)b*(float)c))+(float)d)
      out.println ("Solution found: ("+a+"*("+b+"*"+c+"))+"+d+" = 24<BR>");

   rslt = a*b*c-d;
   if (rslt == 24 &&  rslt == (float)a*(float)b*(float)c-(float)d)
      out.println ("Solution found: "+a+"*"+b+"*"+c+"-"+d+" = 24<BR>");
   rslt = a*(b*c)-d;
   if (rslt == 24 &&  rslt == (float)a*((float)b*(float)c)-(float)d)
      out.println ("Solution found: "+a+"*("+b+"*"+c+")-"+d+" = 24<BR>");
   rslt = a*b*(c-d);
   if (rslt == 24 &&  rslt == (float)a*(float)b*((float)c-(float)d))
      out.println ("Solution found: "+a+"*"+b+"*("+c+"-"+d+") = 24<BR>");
   rslt = a*(b*(c-d));
   if (rslt == 24 &&  rslt == (float)a*((float)b*((float)c-(float)d)))
      out.println ("Solution found: "+a+"*("+b+"*("+c+"-"+d+")) = 24<BR>");
   rslt = a*((b*c)-d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b*(float)c)-(float)d))
      out.println ("Solution found: "+a+"*(("+b+"*"+c+")-"+d+") = 24<BR>");
   rslt = (a*b*c)-d;
   if (rslt == 24 &&  rslt == ((float)a*(float)b*(float)c)-(float)d)
      out.println ("Solution found: ("+a+"*"+b+"*"+c+")-"+d+" = 24<BR>");
   rslt = (a*(b*c))-d;
   if (rslt == 24 &&  rslt == ((float)a*((float)b*(float)c))-(float)d)
      out.println ("Solution found: ("+a+"*("+b+"*"+c+"))-"+d+" = 24<BR>");

   rslt = a*b*c*d;
   if (rslt == 24 &&  rslt == (float)a*(float)b*(float)c*(float)d)
      out.println ("Solution found: "+a+"*"+b+"*"+c+"*"+d+" = 24<BR>");
   rslt = a*(b*c)*d;
   if (rslt == 24 &&  rslt == (float)a*((float)b*(float)c)*(float)d)
      out.println ("Solution found: "+a+"*("+b+"*"+c+")*"+d+" = 24<BR>");
   rslt = a*b*(c*d);
   if (rslt == 24 &&  rslt == (float)a*(float)b*((float)c*(float)d))
      out.println ("Solution found: "+a+"*"+b+"*("+c+"*"+d+") = 24<BR>");
   rslt = a*(b*(c*d));
   if (rslt == 24 &&  rslt == (float)a*((float)b*((float)c*(float)d)))
      out.println ("Solution found: "+a+"*("+b+"*("+c+"*"+d+")) = 24<BR>");
   rslt = a*((b*c)*d);
   if (rslt == 24 &&  rslt == (float)a*(((float)b*(float)c)*(float)d))
      out.println ("Solution found: "+a+"*(("+b+"*"+c+")*"+d+") = 24<BR>");
   rslt = (a*b*c)*d;
   if (rslt == 24 &&  rslt == ((float)a*(float)b*(float)c)*(float)d)
      out.println ("Solution found: ("+a+"*"+b+"*"+c+")*"+d+" = 24<BR>");
   rslt = (a*(b*c))*d;
   if (rslt == 24 &&  rslt == ((float)a*((float)b*(float)c))*(float)d)
      out.println ("Solution found: ("+a+"*("+b+"*"+c+"))*"+d+" = 24<BR>");

   try {
      rslt = a*b*c/d;
      if (rslt == 24 &&  rslt == (float)a*(float)b*(float)c/(float)d)
         out.println ("Solution found: "+a+"*"+b+"*"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b*c)/d;
      if (rslt == 24 &&  rslt == (float)a*((float)b*(float)c)/(float)d)
         out.println ("Solution found: "+a+"*("+b+"*"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*b*(c/d);
      if (rslt == 24 &&  rslt == (float)a*(float)b*((float)c/(float)d))
         out.println ("Solution found: "+a+"*"+b+"*("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b*(c/d));
      if (rslt == 24 &&  rslt == (float)a*((float)b*((float)c/(float)d)))
         out.println ("Solution found: "+a+"*("+b+"*("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*((b*c)/d);
      if (rslt == 24 &&  rslt == (float)a*(((float)b*(float)c)/(float)d))
         out.println ("Solution found: "+a+"*(("+b+"*"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b*c)/d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b*(float)c)/(float)d)
         out.println ("Solution found: ("+a+"*"+b+"*"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b*c))/d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b*(float)c))/(float)d)
         out.println ("Solution found: ("+a+"*("+b+"*"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** * / ? ***/
   try {
      rslt = a*b/c+d;
      if (rslt == 24 &&  rslt == (float)a*(float)b/(float)c+(float)d)
         out.println ("Solution found: "+a+"*"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/c)+d;
      if (rslt == 24 &&  rslt == (float)a*((float)b/(float)c)+(float)d)
         out.println ("Solution found: "+a+"*("+b+"/"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*b/(c+d);
      if (rslt == 24 &&  rslt == (float)a*(float)b/((float)c+(float)d))
         out.println ("Solution found: "+a+"*"+b+"/("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/(c+d));
      if (rslt == 24 &&  rslt == (float)a*((float)b/((float)c+(float)d)))
         out.println ("Solution found: "+a+"*("+b+"/("+c+"+"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*((b/c)+d);
      if (rslt == 24 &&  rslt == (float)a*(((float)b/(float)c)+(float)d))
         out.println ("Solution found: "+a+"*(("+b+"/"+c+")+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b/c)+d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b/(float)c)+(float)d)
         out.println ("Solution found: ("+a+"*"+b+"/"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b/c))+d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b/(float)c))/(float)d)
         out.println ("Solution found: ("+a+"*("+b+"/"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a*b/c-d;
      if (rslt == 24 &&  rslt == (float)a*(float)b/(float)c-(float)d)
         out.println ("Solution found: "+a+"*"+b+"/"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/c)-d;
      if (rslt == 24 &&  rslt == (float)a*((float)b/(float)c)-(float)d)
         out.println ("Solution found: "+a+"*("+b+"/"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*b/(c-d);
      if (rslt == 24 &&  rslt == (float)a*(float)b/((float)c-(float)d))
         out.println ("Solution found: "+a+"*"+b+"/("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/(c-d));
         if (rslt == 24 &&  rslt == (float)a*((float)b/((float)c-(float)d)))
         out.println ("Solution found: "+a+"*("+b+"/("+c+"-"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*((b/c)-d);
      if (rslt == 24 &&  rslt == (float)a*(((float)b/(float)c)-(float)d))
         out.println ("Solution found: "+a+"*(("+b+"/"+c+")-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b/c)-d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b/(float)c)-(float)d)
         out.println ("Solution found: ("+a+"*"+b+"/"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b/c))-d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b/(float)c))-(float)d)
         out.println ("Solution found: ("+a+"*("+b+"/"+c+"))-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a*b/c*d;
      if (rslt == 24 &&  rslt == (float)a*(float)b/(float)c*(float)d)
         out.println ("Solution found: "+a+"*"+b+"/"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/c)*d;
      if (rslt == 24 &&  rslt == (float)a*((float)b/(float)c)*(float)d)
         out.println ("Solution found: "+a+"*("+b+"/"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*b/(c*d);
      if (rslt == 24 &&  rslt == (float)a*(float)b/((float)c*(float)d))
         out.println ("Solution found: "+a+"*"+b+"/("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/(c*d));
      if (rslt == 24 &&  rslt == (float)a*((float)b/((float)c*(float)d)))
         out.println ("Solution found: "+a+"*("+b+"/("+c+"*"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*((b/c)*d);
      if (rslt == 24 &&  rslt == (float)a*(((float)b/(float)c)*(float)d))
         out.println ("Solution found: "+a+"*(("+b+"/"+c+")*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b/c)*d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b/(float)c)*(float)d)
         out.println ("Solution found: ("+a+"*"+b+"/"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b/c))*d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b/(float)c))*(float)d)
         out.println ("Solution found: ("+a+"*("+b+"/"+c+"))*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a*b/c/d;
      if (rslt == 24 &&  rslt == (float)a*(float)b/(float)c/(float)d)
         out.println ("Solution found: "+a+"*"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/c)/d;
      if (rslt == 24 &&  rslt == (float)a*((float)b/(float)c)/(float)d)
         out.println ("Solution found: "+a+"*("+b+"/"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*b/(c/d);
      if (rslt == 24 &&  rslt == (float)a*(float)b/((float)c/(float)d))
         out.println ("Solution found: "+a+"*"+b+"/("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*(b/(c/d));
      if (rslt == 24 &&  rslt == (float)a*((float)b/((float)c/(float)d)))
         out.println ("Solution found: "+a+"*("+b+"/("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a*((b/c)/d);
      if (rslt == 24 &&  rslt == (float)a*(((float)b/(float)c)/(float)d))
         out.println ("Solution found: "+a+"*(("+b+"/"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*b/c)/d;
      if (rslt == 24 &&  rslt == ((float)a*(float)b/(float)c)/(float)d)
         out.println ("Solution found: ("+a+"*"+b+"/"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a*(b/c))/d;
      if (rslt == 24 &&  rslt == ((float)a*((float)b/(float)c))/(float)d)
         out.println ("Solution found: ("+a+"*("+b+"/"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** / + + ***/
   try {
      rslt = a/b+c+d;
      if (rslt == 24 &&  rslt == (float)a/(float)b+(float)c+(float)d)
         out.println ("Solution found: "+a+"/"+b+"+"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b+c+d);
      if (rslt == 24 &&  rslt == (float)a/((float)b+(float)c+(float)d))
         out.println ("Solution found: "+a+"/("+b+"+"+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** / + ? ***/
   try {
      rslt = a/b+c-d;
      if (rslt == 24 &&  rslt == (float)a/(float)b+(float)c-(float)d)
         out.println ("Solution found: "+a+"/"+b+"+"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b+c)-d;
      if (rslt == 24 &&  rslt == (float)a/((float)b+(float)c)-(float)d)
         out.println ("Solution found: "+a+"/("+b+"+"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b+c)-d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b+(float)c)-(float)d))
         out.println ("Solution found: "+a+"/(("+b+"+"+c+")-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b+c*d;
      if (rslt == 24 &&  rslt == (float)a/(float)b+(float)c*(float)d)
         out.println ("Solution found: "+a+"/"+b+"+"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b+c)*d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b+(float)c)*(float)d)
         out.println ("Solution found: ("+a+"/"+b+"+"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b+c))*d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b+(float)c))*(float)d)
         out.println ("Solution found: ("+a+"/("+b+"+"+c+"))*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b+c/d;
      if (rslt == 24 &&  rslt == (float)a/(float)b+(float)c/(float)d)
         out.println ("Solution found: "+a+"/"+b+"+"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b+c)/d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b+(float)c)/(float)d)
         out.println ("Solution found: ("+a+"/"+b+"+"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b+c))/d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b+(float)c))/(float)d)
         out.println ("Solution found: ("+a+"/("+b+"+"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** / - ? ***/
   try {
      rslt = a/b-c+d;
      if (rslt == 24 &&  rslt == (float)a/(float)b-(float)c+(float)d)
         out.println ("Solution found: "+a+"/"+b+"-"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-c)+d;
      if (rslt == 24 &&  rslt == (float)a/((float)b-(float)c)+(float)d)
         out.println ("Solution found: "+a+"/("+b+"-"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b-(c+d);
      if (rslt == 24 &&  rslt == (float)a/(float)b-((float)c+(float)d))
         out.println ("Solution found: "+a+"/"+b+"-("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-(c+d));
      if (rslt == 24 &&  rslt == (float)a/((float)b-((float)c+(float)d)))
         out.println ("Solution found: "+a+"/("+b+"-("+c+"+"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b-c)+d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b-(float)c)+(float)d))
         out.println ("Solution found: "+a+"/(("+b+"-"+c+")+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b-c-d;
      if (rslt == 24 &&  rslt == (float)a/(float)b-(float)c-(float)d)
         out.println ("Solution found: "+a+"/"+b+"-"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-c)-d;
      if (rslt == 24 &&  rslt == (float)a/((float)b-(float)c)-(float)d)
         out.println ("Solution found: "+a+"/("+b+"-"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b-(c-d);
      if (rslt == 24 &&  rslt == (float)a/(float)b-((float)c-(float)d))
         out.println ("Solution found: "+a+"/"+b+"-("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-(c-d));
      if (rslt == 24 &&  rslt == (float)a/((float)b-((float)c-(float)d)))
         out.println ("Solution found: "+a+"/("+b+"-("+c+"-"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b-c)-d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b-(float)c)-(float)d))
         out.println ("Solution found: "+a+"/(("+b+"-"+c+")-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b-c*d;
      if (rslt == 24 &&  rslt == (float)a/(float)b-(float)c*(float)d)
         out.println ("Solution found: "+a+"/"+b+"-"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-c)*d;
      if (rslt == 24 &&  rslt == (float)a/((float)b-(float)c)*(float)d)
         out.println ("Solution found: "+a+"/("+b+"-"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b-(c*d);
      if (rslt == 24 &&  rslt == (float)a/(float)b-((float)c*(float)d))
         out.println ("Solution found: "+a+"/"+b+"-("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-(c*d));
      if (rslt == 24 &&  rslt == (float)a/((float)b-((float)c*(float)d)))
         out.println ("Solution found: "+a+"/("+b+"-("+c+"*"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b-c)*d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b-(float)c)*(float)d))
         out.println ("Solution found: "+a+"/(("+b+"-"+c+")*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b-c)*d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b-(float)c)*(float)d)
         out.println ("Solution found: ("+a+"/"+b+"-"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b-c))*d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b-(float)c))*(float)d)
         out.println ("Solution found: ("+a+"/("+b+"-"+c+"))*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b-c/d;
      if (rslt == 24 &&  rslt == (float)a/(float)b-(float)c/(float)d)
         out.println ("Solution found: "+a+"/"+b+"-"+c+"/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-c)/d;
      if (rslt == 24 &&  rslt == (float)a/((float)b-(float)c)/(float)d)
         out.println ("Solution found: "+a+"/("+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b-(c/d);
      if (rslt == 24 &&  rslt == (float)a/(float)b-((float)c/(float)d))
         out.println ("Solution found: "+a+"/"+b+"-("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b-(c/d));
      if (rslt == 24 &&  rslt == (float)a/((float)b-((float)c/(float)d)))
         out.println ("Solution found: "+a+"/("+b+"-("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b-c)/d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b-(float)c)/(float)d))
         out.println ("Solution found: "+a+"/(("+b+"-"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b-c)/d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b-(float)c)/(float)d)
         out.println ("Solution found: ("+a+"/"+b+"-"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b-c))/d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b-(float)c))/(float)d)
         out.println ("Solution found: ("+a+"/("+b+"-"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** / * ? ***/
   try {
      rslt = a/b*c+d;
      if (rslt == 24 &&  rslt == (float)a/(float)b*(float)c+(float)d)
         out.println ("Solution found: "+a+"/"+b+"*"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*c)+d;
      if (rslt == 24 &&  rslt == (float)a/((float)b*(float)c)+(float)d)
         out.println ("Solution found: "+a+"/("+b+"*"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b*(c+d);
      if (rslt == 24 &&  rslt == (float)a/(float)b*((float)c+(float)d))
         out.println ("Solution found: "+a+"/"+b+"*("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*(c+d));
      if (rslt == 24 &&  rslt == (float)a/((float)b*((float)c+(float)d)))
         out.println ("Solution found: "+a+"/("+b+"*("+c+"+"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b*c)+d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b*(float)c)+(float)d))
         out.println ("Solution found: "+a+"/(("+b+"*"+c+")+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b*c)+d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b*(float)c)+(float)d)
         out.println ("Solution found: ("+a+"/"+b+"*"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b*c))+d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b*(float)c))/(float)d)
         out.println ("Solution found: ("+a+"/("+b+"*"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b*c-d;
      if (rslt == 24 &&  rslt == (float)a/(float)b*(float)c-(float)d)
         out.println ("Solution found: "+a+"/"+b+"*"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*c)-d;
      if (rslt == 24 &&  rslt == (float)a/((float)b*(float)c)-(float)d)
         out.println ("Solution found: "+a+"/("+b+"*"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b*(c-d);
      if (rslt == 24 &&  rslt == (float)a/(float)b*((float)c-(float)d))
         out.println ("Solution found: "+a+"/"+b+"*("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*(c-d));
      if (rslt == 24 &&  rslt == (float)a/((float)b*((float)c-(float)d)))
         out.println ("Solution found: "+a+"/("+b+"*("+c+"-"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b*c)-d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b*(float)c)-(float)d))
         out.println ("Solution found: "+a+"/(("+b+"*"+c+")-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b*c)-d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b*(float)c)-(float)d)
         out.println ("Solution found: ("+a+"/"+b+"*"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b*c))-d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b*(float)c))-(float)d)
         out.println ("Solution found: ("+a+"/("+b+"*"+c+"))-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b*c*d;
      if (rslt == 24 &&  rslt == (float)a/(float)b*(float)c*(float)d)
         out.println ("Solution found: "+a+"/"+b+"*"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*c)*d;
      if (rslt == 24 &&  rslt == (float)a/((float)b*(float)c)*(float)d)
         out.println ("Solution found: "+a+"/("+b+"*"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b*(c*d);
      if (rslt == 24 &&  rslt == (float)a/(float)b*((float)c*(float)d))
         out.println ("Solution found: "+a+"/"+b+"*("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*(c*d));
      if (rslt == 24 &&  rslt == (float)a/((float)b*((float)c*(float)d)))
         out.println ("Solution found: "+a+"/("+b+"*("+c+"*"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b*c)*d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b*(float)c)*(float)d))
         out.println ("Solution found: "+a+"/(("+b+"*"+c+")*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b*c)*d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b*(float)c)*(float)d)
         out.println ("Solution found: ("+a+"/"+b+"*"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b*c))*d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b*(float)c))*(float)d)
         out.println ("Solution found: ("+a+"/("+b+"*"+c+"))*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b*c/d;
      if (rslt == 24 &&  rslt == (float)a/(float)b*(float)c/(float)d)
         out.println ("Solution found: "+a+"/"+b+"*"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*c)/d;
      if (rslt == 24 &&  rslt == (float)a/((float)b*(float)c)/(float)d)
         out.println ("Solution found: "+a+"/("+b+"*"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b*(c/d);
      if (rslt == 24 &&  rslt == (float)a/(float)b*((float)c/(float)d))
         out.println ("Solution found: "+a+"/"+b+"*("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b*(c/d));
      if (rslt == 24 &&  rslt == (float)a/((float)b*((float)c/(float)d)))
         out.println ("Solution found: "+a+"/("+b+"*("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b*c)/d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b*(float)c)/(float)d))
         out.println ("Solution found: "+a+"/(("+b+"*"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b*c)/d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b*(float)c)/(float)d)
         out.println ("Solution found: ("+a+"/"+b+"*"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b*c))/d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b*(float)c))/(float)d)
         out.println ("Solution found: ("+a+"/("+b+"*"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   /**** * / ? ***/
   try {
      rslt = a/b/c+d;
      if (rslt == 24 &&  rslt == (float)a/(float)b/(float)c+(float)d)
         out.println ("Solution found: "+a+"/"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/c)+d;
      if (rslt == 24 &&  rslt == (float)a/((float)b/(float)c)+(float)d)
         out.println ("Solution found: "+a+"/("+b+"/"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b/(c+d);
      if (rslt == 24 &&  rslt == (float)a/(float)b/((float)c+(float)d))
         out.println ("Solution found: "+a+"/"+b+"/("+c+"+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/(c+d));
      if (rslt == 24 &&  rslt == (float)a/((float)b/((float)c+(float)d)))
         out.println ("Solution found: "+a+"/("+b+"/("+c+"+"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b/c)+d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b/(float)c)+(float)d))
         out.println ("Solution found: "+a+"/(("+b+"/"+c+")+"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b/c)+d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b/(float)c)+(float)d)
         out.println ("Solution found: ("+a+"/"+b+"/"+c+")+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b/c))+d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b/(float)c))/(float)d)
         out.println ("Solution found: ("+a+"/("+b+"/"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b/c-d;
      if (rslt == 24 &&  rslt == (float)a/(float)b/(float)c-(float)d)
         out.println ("Solution found: "+a+"/"+b+"/"+c+"-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/c)-d;
      if (rslt == 24 &&  rslt == (float)a/((float)b/(float)c)-(float)d)
         out.println ("Solution found: "+a+"/("+b+"/"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b/(c-d);
      if (rslt == 24 &&  rslt == (float)a/(float)b/((float)c-(float)d))
         out.println ("Solution found: "+a+"/"+b+"/("+c+"-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/(c-d));
      if (rslt == 24 &&  rslt == (float)a/((float)b/((float)c-(float)d)))
         out.println ("Solution found: "+a+"/("+b+"/("+c+"-"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b/c)-d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b/(float)c)-(float)d))
         out.println ("Solution found: "+a+"/(("+b+"/"+c+")-"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b/c)-d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b/(float)c)-(float)d)
         out.println ("Solution found: ("+a+"/"+b+"/"+c+")-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b/c))-d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b/(float)c))-(float)d)
         out.println ("Solution found: ("+a+"/("+b+"/"+c+"))-"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b/c*d;
      if (rslt == 24 &&  rslt == (float)a/(float)b/(float)c*(float)d)
         out.println ("Solution found: "+a+"/"+b+"/"+c+"*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/c)*d;
      if (rslt == 24 &&  rslt == (float)a/((float)b/(float)c)*(float)d)
         out.println ("Solution found: "+a+"/("+b+"/"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b/(c*d);
      if (rslt == 24 &&  rslt == (float)a/(float)b/((float)c*(float)d))
         out.println ("Solution found: "+a+"/"+b+"/("+c+"*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/(c*d));
      if (rslt == 24 &&  rslt == (float)a/((float)b/((float)c*(float)d)))
         out.println ("Solution found: "+a+"/("+b+"/("+c+"*"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b/c)*d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b/(float)c)*(float)d))
         out.println ("Solution found: "+a+"/(("+b+"/"+c+")*"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b/c)*d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b/(float)c)*(float)d)
         out.println ("Solution found: ("+a+"/"+b+"/"+c+")*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b/c))*d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b/(float)c))*(float)d)
         out.println ("Solution found: ("+a+"/("+b+"/"+c+"))*"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

   try {
      rslt = a/b/c/d;
      if (rslt == 24 &&  rslt == (float)a/(float)b/(float)c/(float)d)
         out.println ("Solution found: "+a+"/"+b+"/"+c+"+"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/c)/d;
      if (rslt == 24 &&  rslt == (float)a/((float)b/(float)c)/(float)d)
         out.println ("Solution found: "+a+"/("+b+"/"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/b/(c/d);
      if (rslt == 24 &&  rslt == (float)a/(float)b/((float)c/(float)d))
         out.println ("Solution found: "+a+"/"+b+"/("+c+"/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/(b/(c/d));
      if (rslt == 24 &&  rslt == (float)a/((float)b/((float)c/(float)d)))
         out.println ("Solution found: "+a+"/("+b+"/("+c+"/"+d+")) = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = a/((b/c)/d);
      if (rslt == 24 &&  rslt == (float)a/(((float)b/(float)c)/(float)d))
         out.println ("Solution found: "+a+"/(("+b+"/"+c+")/"+d+") = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/b/c)/d;
      if (rslt == 24 &&  rslt == ((float)a/(float)b/(float)c)/(float)d)
         out.println ("Solution found: ("+a+"/"+b+"/"+c+")/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }
   try {
      rslt = (a/(b/c))/d;
      if (rslt == 24 &&  rslt == ((float)a/((float)b/(float)c))/(float)d)
         out.println ("Solution found: ("+a+"/("+b+"/"+c+"))/"+d+" = 24<BR>");
   } catch (ArithmeticException e) { }

}  // End checkSolutions()

}
