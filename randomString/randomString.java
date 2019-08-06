/** File name: randomString.java
 *  Date:      04-Feb-2013
 *  Author:    Upsorn Praphamontripong & Jeff Offutt
 */

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;
import java.util.*;
import java.lang.*;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

//randomString class
//   Allow users to enter a list of strings to be randomly chosen
//   with two options: with replacement or without replacement (default)

//  Is multiple chosen allowed? -- provide a textbox to enter the number of strings needed
//  then, clicking a submit button results in multiple returns of string chosen.

//CONSTRUCTOR: no constructor specified (default)
//
//****************  Methods description  *******************************
//void doPost ()    --> Main method for gathering data and sending back
//void doGet ()     --> Not used.
//***********************************************************************

public class randomString extends HttpServlet
{
   private ArrayList<String> strList; // list of strings extracted from the data entry
   private ArrayList<String> result = new ArrayList<String>();  // list of strings chosen
   private String randomString = new String();
   // private String actionLocation = "http://localhost:8080/432sample/randomString";
   //private String actionLocation = "http://cs.gmu.edu:8080/offutt/servlet/randomString";
   private String actionLocation = "http://cs.gmu.edu:8080/uprapham/servlet/randomString";

   public void doGet (HttpServletRequest req,
                      HttpServletResponse res)
          throws ServletException, IOException
   {
      res.setContentType("text/html");
      //Get the response's PrintWriter to return text to the client.
      PrintWriter out = res.getWriter();

      out.println("<!DOCTYPE HTML>");
      out.println("<html>");
      PrintHead(out);
      PrintBody(out);
      out.println("</html>");

      out.close();
   }  

   public void PrintHead(PrintWriter out)
   {
      out.println("<head>");
      out.println("  <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >");
      out.println("    <title>String Randomization</title>");
      out.println("</head>");
   }

   public void PrintBody(PrintWriter out)
   {
      out.println("<body onload=\"setFocus()\">");
      PrintJS(out);

      out.println("<form method=\"POST\"" );
      out.println("      action=\"" + actionLocation + "\">"); // Defined above (Jeff Offutt)
      out.println("    <!-- start main -->");
      out.println("<table border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"70%\" align=\"center\">");
      out.println("<tr>");
      out.println("  <td width=\"90%\" colspan=\"8\">");
      out.println("    <h2 align=\"center\">String Randomization</h2>");
      out.println("    <hr>");
      out.println("  </td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <td bgcolor=\"#CCFFFF\" align=\"center\" width=\"35%\" colspan=\"2\"><b> Enter strings to be chosen </b></br>");
      out.println("    <I>Separate multiple strings by new lines.</I>");
      out.println("  </td>");
      out.println("  <td width=\"2%\" colspan=\"1\">&nbsp;&nbsp;</td>");
      out.println("  <td bgcolor=\"#EEEEEE\" align=\"center\" width=\"35%\" colspan=\"2\">String chosen: ");

      if (randomString.length() > 0)
      {
          out.print("    <input type=\"text\" border=\"0\" readonly=\"readonly\" name=\"randomString\" id=\"randomString\" size=\"30\" value=\"");
          out.println(randomString + "\">");
          out.println("</input>");
      }
      else
         out.println("    <input type=\"text\" border=\"0\" readonly=\"readonly\" name=\"randomString\" id=\"randomString\" size=\"30\"></input>");

      out.println("  </td>");
      out.println("  <td width=\"2%\" colspan=\"1\">&nbsp;&nbsp;</td>");
      out.println("  <td align=\"center\" width=\"25%\" colspan=\"2\">&nbsp;</td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <td bgcolor=\"#CCFFFF\" align=\"center\" width=\"35%\" colspan=\"2\">");

      out.println("    <textarea rows=\"20\" name=\"strEnter\" id=\"strEnter\" cols=\"25\" autofocus=true>");
      if (strList != null && strList.size() > 0)   // there are strings to be chosen
      {
         for (int k=0; k<strList.size(); k++)
         {
            if (strList.get(k).equals('\n') == false && strList.get(k).equals(" ") == false)
               out.println(strList.get(k));
         }
      }
      out.println("</textarea>");
      out.println("  </td>");

      out.println("  <td width=\"2%\" colspan=\"1\">&nbsp;&nbsp;</td>");
      out.println("  <td bgcolor=\"#EEEEEE\" align=\"center\" width=\"35%\" colspan=\"2\">");

      out.println("    <textarea rows=\"20\" name=\"strChosen\" id=\"strChosen\" cols=\"25\" readonly=\"readonly\" >");
      if (result != null && result.size() > 0)   // there are strings that have been chosen
      {
         for (int i=0; i<result.size(); i++)
            out.println(result.get(i));
      }
      out.println("</textarea>");
      out.println("  </td>");

      out.println("  <td width=\"2%\" colspan=\"1\">&nbsp;&nbsp;</td>");
      out.println("  <td width=\"25%\" colspan=\"2\" align=\"center\">");
      out.println("    <table border=\"0\">");
      out.println("      <tr>");
      out.println("        <td>");
      out.println("          <input type=\"radio\" value=\"WR\" id = \"replacementOption\" name=\"replacementOption\">With replacement");
      out.println("        </td>");
      out.println("      </tr>");
      out.println("      <tr>");
      out.println("        <td>  <!--  this should be set as default option -->");
      out.println("          <input checked type=\"radio\" value=\"WO\" id = \"replacementOption\" name=\"replacementOption\">Without replacement");
      out.println("        </td>");
      out.println("      </tr>");
      out.println("      <tr cellpadding=\"2\">");
      out.println("        <td align=\"center\">");
      out.println("          <input type=\"submit\" value=\"Submit\" Name=\"btn\" onClick=\"return submitIt(this.form)\">");
      out.println("        </td>");
      out.println("      </tr>");
      out.println("      <tr cellpadding=\"2\">");
      out.println("        <td align=\"center\">&nbsp;</td>");
      out.println("      </tr>");
      out.println("      <tr cellpadding=\"2\">");
      out.println("        <td align=\"center\">&nbsp;</td>");
      out.println("      </tr>");
      out.println("      <tr>");
      out.println("        <td align=\"center\">");
      out.println("          <button type=\"submit\" name=\"btn\" value=\"Restart\">Restart</button>");
      out.println("        </td>");
      out.println("      </tr>");
      out.println("    </table>");
      out.println("  </td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <td bgcolor=\"#CCFFFF\" align=\"center\" width=\"35%\" colspan=\"2\">&nbsp;</td>");
      out.println("  <td width=\"2%\" colspan=\"1\">&nbsp;&nbsp;</td>");
      out.println("  <td bgcolor=\"#EEEEEE\" align=\"center\" width=\"35%\" colspan=\"2\">");
      out.println("     <button type=\"submit\" name=\"btn\" value=\"Clear\">Clear Chosen Strings</button>");
      out.println("  </td>");
      out.println("  <td width=\"2%\" colspan=\"1\">&nbsp;&nbsp;</td>");
      out.println("  <td align=\"center\" width=\"25%\" colspan=\"2\">&nbsp;</td>");
      out.println("</tr>");

      out.println("<tr>");
      out.println("  <td colspan=\"8\"><hr></td>");
      out.println("</tr>");

      out.println("</table>");
      out.println("    <!-- end main -->");
      out.println("</form>");
      out.println("</body>");
   }

   public void PrintJS(PrintWriter out)
   {
      out.println("  <!-- start js -->");
      out.println("  <script>");
      out.println("    <!-- hide code");
      out.println("    function submitIt(form)");
      out.println("    {");
      out.println("       errCount=0;");
      out.println("       errorMsg=\"\";");
      out.println("       aVal = form.strEnter.value");
      out.println("       if ( aVal == \"\") ");
      out.println("       {");
      out.println("          errorMsg += \"\\n\\n\"+ \" Enter strings to be chosen.\";");
      out.println("          errCount++;");
      out.println("       }");
      out.println("       if (errCount > 0) ");
      out.println("       {");
      out.println("          alert (\"Please correct the following \" + errCount + \" fields: \" + errorMsg);");
      out.println("          if ( aVal == \"\") ");
      out.println("          {");
      out.println("             document.getElementById(\"strEnter\").focus();");
      out.println("          }");
      out.println("          return false;");
      out.println("       }");
      out.println("       else {");
      out.println("          return true;");
      out.println("       }");
      out.println("    }");

      out.println("    function clearFields() ");
      out.println("    {");
      out.println("      if (confirm(\"Are you sure?\")) ");
      out.println("      {");
      out.println("        document.getElementById(\"strEnter\").value = \"\";");
      out.println("        document.getElementById(\"randomString\").value = \"\";");
      out.println("        document.getElementById(\"strChosen\").value = \"\";");
      out.println("      }");
      out.println("    }");

      out.println("    function clearResults() ");
      out.println("    {");
      out.println("        document.getElementById(\"randomString\").value = \"\";");
      out.println("        document.getElementById(\"strChosen\").value = \"\";");
      out.println("    }");

      out.println("    function setFocus() ");
      out.println("    {");
      out.println("        document.getElementById(\"strEnter\").focus();");
      out.println("    }");

      out.println("    // end hiding code -->");
      out.println("  </script>");
      out.println("  <!-- end js -->");
   }


   /** **********************************************************
   *  doPost()
   *  gathering data and sending back to browser
   ************************************************************ */
   public void doPost (HttpServletRequest req, HttpServletResponse res)
          throws ServletException, IOException
   {
      String data = req.getParameter("strEnter");
      String option = req.getParameter("replacementOption");
      String str = new String();
      strList = new ArrayList<String>();

      // extract data entry --> list of strings
      // extract data entry -- multiple strings are separated by carriage return
      // reformat the data entry into a list of strings
      // split() and ArrayList() added by Jeff
      strList = new ArrayList (Arrays.asList (data.split ("[\\r\\n]+")));

      String button = req.getParameter("btn");
      if (button.equals("Restart"))
      {
         strList = new ArrayList<String>();
         result = new ArrayList<String>();
         randomString = new String();
      }
      else if (button.equals("Clear"))
      {
         result = new ArrayList<String>();
         randomString = new String();
      }
      else if (button.equals("Submit"))
      {
         // if a string is chosen without replacement,
         // a chosen string X will be remove from data
         //     data' = data - {X}
         // if a string is chosen with replacement, data' = data

         if (strList != null && strList.size() > 0)
         {
            ArrayList<String> tempList = strList;
            int n = tempList.size();
            Random generator = new Random();
            if (n > 0)   // there are n strings to be chosen
            {
               int randomIndex = generator.nextInt(n); // index indicating which string will be chosen from a list
               randomString = tempList.get(randomIndex);
               if (randomString.length() > 0)
                  result.add(randomString);

               if (option.equals("WR"))
               {
                  // do nothing to entries, i.e., a list of string remains the same
               }
               else if (option.equals("WO"))
               {
                  tempList.remove(randomIndex);
                  strList = tempList;
               }
            }
         }
      }

      doGet(req, res);
   } 


}
