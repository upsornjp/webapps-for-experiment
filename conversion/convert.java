/** *****************************************************************
    convert.java   SWE 642 servlet assignment 2, Hwk 7.
     Modification of JConvert by Offutt and Li.

        @author Jeff Offutt

        @version 1.0    (10/24/00)
********************************************************************* */
// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

// convert class
//
// CONSTRUCTOR: no constructor specified (default)
//
// ****************  PUBLIC OPERATIONS  **********************************
// void doPost ()       --> Main servlet method for handling form
// void doGet ()        --> Calls PrintHead and PrintForm (if called from a link)
// void PrintHead ()    --> Regenerates the head of the web page
// void PrintForm ()    --> Regenerates the form of the web page
//*************************************************************************
//
// The possible IOException on the PrintWriter is thrown up.

public class convert extends HttpServlet
{

/** *****************************************************
 *  Overrides HttpServlet's doPost().
 *  Converts each entry in the form and prints the results
 *  at the top of an HTML page.
 *  The new values are printed in red (#FF0000).
********************************************************* */
public void doPost (HttpServletRequest req,
                    HttpServletResponse res)
   throws ServletException, IOException
{
   res.setContentType ("TEXT/HTML");
   PrintWriter out = res.getWriter ();

   PrintHead (res);

   String FAsStr   = req.getParameter ("F");
   String CAsStr   = req.getParameter ("C");
   String inAsStr  = req.getParameter ("in");
   String cmAsStr  = req.getParameter ("cm");
   String ftAsStr  = req.getParameter ("ft");
   String mAsStr   = req.getParameter ("m");
   String miAsStr  = req.getParameter ("mi");
   String kmAsStr  = req.getParameter ("km");
   String galAsStr = req.getParameter ("gal");
   String LAsStr   = req.getParameter ("L");
   String ozAsStr  = req.getParameter ("oz");
   String gAsStr   = req.getParameter ("g");
   String lbAsStr  = req.getParameter ("lb");
   String kgAsStr  = req.getParameter ("kg");

   int n;
   float num1, num2;

   out.println ("<P>");
   out.print   ("<TABLE CELLSPACING=0 CELLPADDING=5 BORDER=0");
   out.println ("ALIGN=center>");

   if  (FAsStr != null && FAsStr.length ()>0)
   {  // Convert farenheit
      num1 =  (Float.valueOf (FAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  ( ( (num1-32.0)*5.0)/9.0);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" F&#186;</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> C&#186;</TD></TR>");
   }

   if  (CAsStr != null && CAsStr.length ()>0)
   {  // Convert Celsius
      num1 =  (Float.valueOf (CAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  ( (num1*9.0/5.0)+32.0);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" C&#186;</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> F&#186;</TD></TR>");
   }

   if  (inAsStr != null && inAsStr.length ()>0)
   {  // Convert inches
      num1 =  (Float.valueOf (inAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*2.54);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" in</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT COLOR=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> cm</TD></TR>");
   }

   if  (cmAsStr != null && cmAsStr.length ()>0)
   {  // Convert centimeters
      num1 =  (Float.valueOf (cmAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*0.3937);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" cm</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> in</TD></TR>");
   }

   if  (ftAsStr != null && ftAsStr.length ()>0)
   {  // Convert feet
      num1 =  (Float.valueOf (ftAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*0.3048);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" ft</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> m</TD></TR>");
   }

   if  (mAsStr != null && mAsStr.length ()>0)
   {  // Convert meters
      num1 =  (Float.valueOf (mAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1/0.3048);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" m</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> ft</TD></TR>");
   }

   if  (miAsStr != null && miAsStr.length ()>0)
   {  // Convert Miles
      num1 =  (Float.valueOf (miAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*1.609);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" mi</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> km</TD></TR>");
   }

   if  (kmAsStr != null && kmAsStr.length ()>0)
   {  // Convert kilometers
      num1 =  (Float.valueOf (kmAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*0.6214);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" km</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> mi</TD></TR>");
   }

   if (galAsStr != null && galAsStr.length ()>0)
   {  // Convert gallons
      num1 =  (Float.valueOf (galAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*3.785);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" gal</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> L</TD></TR>");
   }

   if  (LAsStr != null && LAsStr.length ()>0)
   {  // Convert Liters
      num1 =  (Float.valueOf (LAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1/3.785);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" L</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> gal</TD></TR>");
   }

   if  (ozAsStr != null && ozAsStr.length ()>0)
   {  // Convert ounces
      num1 =  (Float.valueOf (ozAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*28.35);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" oz</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> g</TD></TR>");
   }

   if  (gAsStr != null && gAsStr.length ()>0)
   {  // Convert grams
      num1 =  (Float.valueOf (gAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1/28.35);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" g</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> oz</TD></TR>");
   }

   if (lbAsStr != null && lbAsStr.length ()>0)
   {  // Convert pounds
      num1 =  (Float.valueOf (lbAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*0.4536);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" lb</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> kg</TD></TR>");
   }

   if (kgAsStr != null && kgAsStr.length ()>0)
   {  // Convert Kilograms
      num1 =  (Float.valueOf (kgAsStr).floatValue ());
      n = Math.round (num1* (float)100.0);
      num1 =  (float)  (n/ (float)100.0);
      num2 =  (float)  (num1*2.205);
      n = Math.round (num2* (float)100.0);
      num2 =  (float)  (n/ (float)100.0);
      out.println ("<TR><TD ALIGN=right>");
      out.println (num1);
      out.println (" kg</TD> <TD ALIGN=center>=</TD><TD ALIGN=left>");
      out.println ("<FONT color=\"#FF0000\">");
      out.println (num2);
      out.println ("</FONT> lb</TD></TR>");
   }


   /*   }
   Ren's error handling was commented out ...
   catch  (NumberFormatException e)
   {
      handleError (new NumberFormatException  (
      "Please check that the values entered are numeric"), res);
      return;
   }
        catch  (Exception e)
   {
      handleError (e, res);
      return;
   }*/

   out.println ("</TABLE>");

   out.println ("<HR>");
   PrintForm (res);

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
   PrintHead (res);
   PrintForm (res);
}

/** *****************************************************
 *  Prints the head of the HTML page, without the form.
********************************************************* */
private void PrintHead (HttpServletResponse res)
   throws ServletException, IOException
{
   PrintWriter out=res.getWriter ();
   out.println ("<HTML>");
   out.println ("<HEAD>");
   out.println ("<TITLE>SWE 642: Measurement Conversion</TITLE>");
   out.println ("</HEAD>");
   out.println ("<BODY>");
   out.println ("<CENTER><H2>On-line Measurement Conversion</H2></CENTER>");
   out.println ("Jeff Offutt and Ren Li");
   out.println ("<HR>");
}

/** *****************************************************
 *  Prints the form of the HTML page.
 *  Also prints out the bottom and closes the PrintWriter.
********************************************************* */
private void PrintForm (HttpServletResponse res)
   throws ServletException, IOException
{  // Reprints the form, just like the original HTML
   res.setContentType ("text/html");
   PrintWriter out=res.getWriter ();

   out.print   ("<FORM METHOD=\"post\"");
//   out.println (" ACTION=\"http://cs.gmu.edu:8080/offutt/servlet/convert\">");
   out.println (" ACTION=\"http://cs.gmu.edu:8080/uprapham/servlet/convert\">");
   out.println (" <P>");
   out.println (" <TABLE CELLSPACING=0 CELLPADDING=5 BORDER=0");
   out.print   ("  ALIGN=center>");
   out.println (" <TR ALIGN=right>");
   out.print   ("  <TD><B>Fahrenheit  (F&#186;):</B> ");
   out.println ("  <INPUT TYPE=\"text\" NAME=\"F\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Celsius  (C&#186;):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"C\" SIZE=6></TD></TR>");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Inch  (in):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"in\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Centimeter  (cm):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"cm\" SIZE=6></TD></TR>");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Feet  (ft):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"ft\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Meter  (m):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"m\" SIZE=6></TD></TR>");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Mile  (mi):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"mi\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Kilometer  (km):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"km\" SIZE=6></TD></TR>");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Gallon  (gal):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"gal\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Liter  (L):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"L\" SIZE=6></TD></TR>");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Ounce  (oz):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"oz\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Gram  (g):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"g\" SIZE=6></TD></TR>");
   out.println (" <TR ALIGN=right>");
   out.println ("  <TD><B>Pound  (lb):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"lb\" SIZE=6></TD>");
   out.println ("  <TD>to/from</TD>");
   out.println ("  <TD><B>Kilogram  (kg):</B> ");
   out.println ("  <INPUT type=\"text\" NAME=\"kg\" SIZE=6></TD></TR>");
   out.println (" </TABLE>");
   out.print   (" <TABLE CELLSPACING=0 CELLPADDING=10 ");
   out.println ("   BORDER=0 ALIGN=center WIDTH=\"50%\">");
   out.println ("  <TR ALIGN=center>");
   out.println ("   <TD><INPUT type=\"reset\" value=\"Reset Form\"></TD>");
   out.println ("   <TD><INPUT type=\"submit\" value=\"Convert\"></TD>");
   out.println ("  </TR>");
   out.println (" </TABLE>");
   out.println ("</FORM>");

   out.println ("<HR>");


   out.println ("</BODY>");
   out.println ("</HTML>");
   out.close ();
}

}
