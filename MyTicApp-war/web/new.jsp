<%-- 
    Document   : new
    Created on : 20 Apr 2017, 4:46:53 AM
    Author     : Brian
--%>
<%@page import="myticapp.Test"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTicApp</title>
    </head>
    <body>
        <p><img src="img/myticapp.png" alt="myticapp">
        <a href="welcome.jsp?yname=#info">Info.</a> 
        <div class="tic"
          <%!static int ticn = 0; %>  
          <%!static String cName = " "; 
          static String eMail = " ";
          static String address = " ";
          static String phone = " ";
          static String mobile = " ";
          static String fax = " ";
          static String userName = " ";
          static String password = " ";
          %> 
          <%!static final int DATAL = 50; %>
          <%!static String nTic = "###"; %>
          <%!static myticapp.Test test; %>
        </div>
        <%      
         String currentName=request.getParameter("inputName");
         if(currentName.trim() =="")
         {
          response.sendRedirect("welcome.jsp?yname=#new");
         }
         eMail = request.getParameter("inputEmail");
         address = request.getParameter("inputAddress");
         phone = request.getParameter("inputPhone");
         mobile = request.getParameter("inputMobile");
         fax = request.getParameter("inputFax");
         userName = request.getParameter("inputCC");
         password = request.getParameter("inputPassword");
         ticn++;
         int data = DATAL + ticn;
         if(currentName == null)
           {
            currentName=request.getParameter("uname"); 
            if(currentName == null)
                {
                  currentName=request.getParameter("rname");  
                  if(currentName == null)
                     {
                      currentName = " To MyTicApp, ";
                     }
                }
          }
         out.print("<br>Welcome "+currentName+"<br><hr>");
         out.println("<table border='1' width='100%'>");
         out.println("<tr>");
         out.println("<th>Email</th>"+
                     "<th>Address</th>"+
                     "<th>Phone</th>"+
                     "<th>Mobile</th>"+
                     "<th>Fax</th>"+
                     "<th>UserName</th>"+
                     "<th>PassWord</th>"+
                     "</tr>");
         out.println("<tr>");
         out.println("<td>"+eMail+"</td>" +
                     "<td>"+address+"</td>" +
                     "<td>"+phone+"</td>" +
                     "<td>"+mobile+ "</td>" +
                     "<td>"+fax+ "</td>" +
                     "<td>"+userName+ "</td>" +
                     "<td>"+password+ "</td>");
         out.println("</tr></table>");
         cName = currentName;  
         nTic = data+"/"+java.util.Calendar.getInstance().getTime()+"/"+cName ;
         this.log(nTic);
          %>
        <script type="text/javascript">
        function getMessages()
        {    
        document.writeln("Connecting to server....");
        var myObj = { "name":"Postgres...", "ver":2017, "city":"New York" };
        document.writeln(myObj.name);
        }
        </script>
        <br><br>
        Message:<div id="demo" >
        <%
           test = new Test();
           out.println("<br>Total Time : "+test.TotalTime()+" Seconds.<br>");
           test.NewAcc(cName,eMail,address,phone,mobile,fax,userName,password);
           out.println("<br>FeedBack : "+test.FeedBack()+"<br>");   
        %>
        </div>
        <br>
        <script type="text/javascript">
           getMessages();
        </script>
    </body>
</html>