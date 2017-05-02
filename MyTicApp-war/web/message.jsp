<%-- 
    Document   : message
    Created on : 23 Apr 2017, 4:29:32 PM
    Author     : Brian
--%>
<%@page import="java.time.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="myticapp.Test"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyTicApp</title>
    </head>
    <body>
        <h1>MyTicApp</h1>
        <div class="tic"
        <%!static myticapp.Test test; %>
        </div>
        <script type="text/javascript">
            function getXmlHttpRequest() 
            {
                try
                {
                    // Firefox, Opera 8.0+, Safari
                    return new XMLHttpRequest();
                }
                catch (e)
                {
                    // Internet Explorer
                    try
                    {
                        return new ActiveXObject("Msxml2.XMLHTTP");
                    }
                    catch (e)
                    {
                        try
                        {
                            return new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        catch (e)
                        {
                            alert("Your browser does not support AJAX!");
                            return null;
                        }
                    }
                }
            }
            function getMessages()
            {
                var xmlHttp = getXmlHttpRequest();
                xmlHttp.onreadystatechange=function()
                {
                    if(xmlHttp.readyState==4)
                    {
                        document.getElementById("query").innerHTML="GET app/messages";
                        document.getElementById("output").innerHTML=xmlHttp.responseText;
                    }
                }
                xmlHttp.open("GET","app/messages/",true);
                xmlHttp.send(null);
            }
            function getMessage(index)
            {
                var xmlHttp = getXmlHttpRequest();
                xmlHttp.onreadystatechange=function()
                {
                    if(xmlHttp.readyState==4)
                    {
                        document.getElementById("query").innerHTML="GET app/messages/" + index;
                        if(xmlHttp.responseText.indexOf("<body>") != -1) {
                            s = xmlHttp.responseText.substring(xmlHttp.responseText.indexOf("<body>") + 6, xmlHttp.responseText.indexOf("</body>"))
                            document.getElementById("output").innerHTML=s;
                        } 
                        else 
                        {
                            document.getElementById("output").innerHTML=xmlHttp.responseText;
                        }   
                    }
                }
                xmlHttp.open("GET","app/messages/" + index,true);
                xmlHttp.send(null);
            }
            function deleteMessage(index)
            {
                var xmlHttp = getXmlHttpRequest();
                xmlHttp.onreadystatechange=function()
                {
                    if(xmlHttp.readyState==4)
                    {
                        document.getElementById("query").innerHTML="DELETE app/messages/" + index;
                        if(xmlHttp.responseText.indexOf("<body>") != -1) 
                        {
                            s = xmlHttp.responseText.substring(xmlHttp.responseText.indexOf("<body>") + 6, xmlHttp.responseText.indexOf("</body>"))
                            document.getElementById("output").innerHTML=s;
                        } 
                        else 
                        {
                            document.getElementById("output").innerHTML=xmlHttp.responseText;
                        }
                    }
                };
                xmlHttp.open("DELETE","app/messages/" + index,true);
                xmlHttp.send(null);
            }

            function addMessage(message)
            {
                var xmlHttp = getXmlHttpRequest();
                xmlHttp.onreadystatechange=function()
                {
                    if(xmlHttp.readyState==4)
                    {
                        document.getElementById("query").innerHTML="POST app/messages";
                        document.getElementById("output").innerHTML="";
                    }
                };
                xmlHttp.open("POST","app/messages/",true);
                xmlHttp.send(message);
            }
        </script>
        <p><img src="img/myticapp.png" alt="myticapp">
        <a href="welcome.jsp?yname=#info">Info.</a>    
        <h1>Message Board</h1>
        <form name="form0">
            <button type="button" name="GET0" onclick="getMessages()">LIST ALL MESSAGES</button>
        </form>
        <form name="form1">
            Message id#: <input style="width: 4em" type="text" name="messageNumber" />
            <button type="button" name="GET1" onclick="getMessage(document.form1.messageNumber.value)">GET MESSAGE</button>    
        </form>

        <form name="form2">
            Message id#: <input style="width: 4em" type="text" name="messageNumber" />
            <button type="button" name="GET2" onclick="deleteMessage(document.form2.messageNumber.value)">DELETE MESSAGE</button>
        </form>

        <form name="form3">
            Message: <input type="text" name="messageText" />
            <button type="button" name="GET2" onclick="addMessage(document.form3.messageText.value)">ADD MESSAGE</button>
        </form>

        <h2>Message Board</h2>
        <div id="query" style="font-weight: bold"></div>
        <h3>result</h3>
        <br>
            Your Message:<textarea id="output" rows="10" cols="50" ></textarea>
            <br><br>
        <div id="demo" >
        <%
            String u = request.getParameter("yname");
            if(u != null)
            {
                String m = request.getParameter("message");
                java.util.Date d = new java.util.Date();
                test = new Test();
                int xt = test.Ticket();
                out.println("\n<br>User : "+u+"\n<br>Date : "+d+
                        "\n<br>Message : "+m+"\n<br>Ticket# : "+xt+"\n<br>");
                test.Message(xt, m, u.substring(1, u.length()-1));
            }
            else
            {
                out.println("Tracking#01 : "+u+" pending...!");
            }
            
        %>
        </div>
        <div id="track" >
        <%
            String xx = request.getParameter("xnumber");
            if(xx != null)
            {
                String x = request.getParameter("xname");
                test = new Test();
                int n = Integer.valueOf(xx);
                xx = test.Tracker(n);
                out.println("\n<br>Track#: "+n+" : \n"+xx+"\n<br>Name : "+x+"\n<br>");
            }
            else
            {
                out.println("Tracking#02 : "+xx+" pending...!");
            }
        %>
        </div>
        <div id="picture">
            <%
                String p = request.getServletPath();
                String pp = request.getParameter("xpicture");
                out.println("\n<br>Path : "+p+"\n<br>Picture : "+pp+"\n<br>");
            %>
        </div>
        <div>
            <%
                String xp = request.getParameter("xpicturex");
                if(pp != null)
                {
                   out.println("<br><p><img src="+pp+" alt="+pp+"><br>");
                }
                if(xp != null)
                {
                   out.println("<br><p><img src="+xp+" alt="+xp+"><br>");
                }
            %>
        </div>
        <div>
            <form action="message.jsp" name="formp">
                <br>  
                    Site picture :<input type="text" value="img/site/myticapp.png" name="xpicturex" />     
                <br>
                <br>
                    <button type="submit">View Picture</button>
                <br>
                
            </form>
        </div>
        <script type="text/javascript">
           addMessage(document.getElementById("demo").innerHTML);
           addMessage(document.getElementById("track").innerHTML);
           addMessage(document.getElementById("picture").innerHTML);
           getMessages();
           //document.getElementById("demo").hidden = true;
           //window.close();
        </script>
        <br><br>
    </body>
</html>
