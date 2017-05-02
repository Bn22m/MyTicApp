<%-- 
    Document   : welcome
    Created on : 26 Mar 2017, 1:55:05 PM
    Author     : Brian
--%>
<%@page import="myticapp.Tic"%>
<%@page import="myticapp.Test"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" data-ng-app="myTicApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTicApp</title>
        <link rel="stylesheet" href="css/app.css"/>
        <link rel="stylesheet" href="css/bootstrap.css"/>
        <style type="text/css">
            body 
            {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <link href="css/bootstrap-responsive.css" rel="stylesheet">
        <script src="js/controllers.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="header">
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">
                        <ul class="nav">
                            <li><a class="active" href="#/info"><img src="img/myticapp.png"></a></li>
                            <li><a href="#/doc">Info.</a></li>
                            <li><a href="administration.jsp">Administration</a></li>
                            <li><a href="#/new">New Accounts</a></li>
                            <li><a href="#/myticapp">Services</a></li> 
                            <li><a href="#/help">Products</a></li>
                            <li data-ng-controller="CartStatusController"><a href="#/admin"><img src="img/trolley.png"/> {{content}}</a></li>
                            <li><a href="login.html">Login</a></li>
                        </ul>
                    </div>
                </div>    
            </div>      
        </div>
        <div class="xtic"
            <%!
            static int ticn = 0;   
            static String cName = " ";  
            static final int DATAL = 63; 
            static String nTic = "###"; 
            static myticapp.Test test;
            static myticapp.Tic tic;
            static String feedBack ="OK";
            static int ticx = 0;
            %>
        </div>
        <%      
         String currentName=request.getParameter("yname");
         ticn++;
         test = new Test();
         int data = DATAL + ticn;
         int ticket = test.Ticket();
         feedBack = test.FeedBack();
         if(feedBack == "OK!")
         {
             data = ticket;
             ticx = data;
         }
         else
         {
             tic = new Tic();
             ticx = tic.ReadFile();
         }
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
         out.print("Welcome "+currentName+"<br>");
         cName = currentName;  
         nTic = data+"/"+java.util.Calendar.getInstance().getTime()+"/"+cName+"#"+ticx ;
         this.log(nTic);
         nTic = ticx+"/"+java.time.Instant.now()+"/"+cName;
        %>
        <hr>
        <div data-ng-view></div>
        <hr>
        <form action="message.jsp" name="form3">
            <br><br>    
            Line Number:<input type="number" value="<%=ticn%>" name="ynumber" />     
            <br>  
            Your Ticket Number:<input type="text" value="<%=nTic%>" name="tnumber" />     
            <br>
            Your Email:<input  type="email"  name="yemail" />
            <br>
            Your Message:<textarea name="message" rows="8" cols="25" wrap="virtual" ></textarea>
            <br>
            <button type="submit">Send Message</button>
            <br><br>    
        <ul>
            <li>
              <b><a href="message.html">Query for new messages.</a></b>
            </li>
        </ul>
        </form>
        <ul>
            <li>
                 Upload site picture:    
             </li>
        </ul>
        <form action="upload.jsp" method="post" enctype="multipart/form-data"> Select File:<input type="file" name="fname"/><br/> 
            <ul>
                <li>
                    <input type="image" alt="Send." src="img/send.png"/> 
                </li>
            </ul>   
        </form> 
        <div id="notes">    
            <br><hr><br>  
            Notes Ref : <%=feedBack%><br> 
            MyTicApp <%=java.time.Year.now()%>.<br>
            <hr><br>
        </div>
        <script src="lib/angular/angular.js"></script>
        <script src="lib/angular/angular-resource.js"></script>
        <script src="lib/jquery/jquery-1.8.0.js"></script>
        <script src="lib/bootstrap/bootstrap.js"></script>
        <script src="js/app.js"></script>
        <script src="js/services.js"></script>
        <script src="js/controllers.js"></script>
        <script src="js/filters.js"></script>
        <script src="js/directives.js"></script>
    </body>
</html>
