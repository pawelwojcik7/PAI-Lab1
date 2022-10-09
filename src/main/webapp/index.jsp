<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br>
<a href="first-servlet">First Servlet</a>
<br>
<h2>Podaj dane do operacji</h2>
<form action="<%= request.getContextPath()%>/calc-servlet" method="post">
    <label for="parameter1"></label><input type="text" id="parameter1" name="parameter1"><br><br>
    <label for="operation"></label><select id="operation" name="operation">
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="/">/</option>
        <option value="*">*</option>
    </select>
    <br>
    <br>
    <label for="parameter2"></label><input type="text" id="parameter2" name="parameter2"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>