<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calc JSP</title>
</head>
<body>
<%@page import="java.util.Date" %>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%= new Date() %>
<br>
<br>
<%
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date now = new Date();
String date = dateFormat.format(now);
out.println(date);
%>
<form action="<%= request.getContextPath()%>/calc.jsp?post=true" method="post">
    <label for="Kwota pożyczki"></label><input type="text" id="kwota" name="kwota"><br><br>
    <label for="Procent roczny"></label><input type="text" id="procent" name="procent"><br><br>
     <label for="Liczba rat"></label><input type="text" id="liczbaRat" name="liczbaRat"><br><br>
    <input type="submit" value="Submit" >
</form>
<% if (request.getParameter("post")!=null){
 String res="";
 try {
 Double k = Double.parseDouble(request.getParameter("kwota"));
 Double p = Double.parseDouble(request.getParameter("procent"))/12;
 Double r = Double.parseDouble(request.getParameter("liczbaRat"));
 Double result = (k*p) /  ( 1-  (  1 / (Math.pow((1+p), r))) );
 res = String.valueOf(result);
 }
 catch (Exception ex) { }
 out.println(res);
 }
%>
</body>
</html>