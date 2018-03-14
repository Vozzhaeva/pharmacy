<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 22.03.2017
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error</title>
</head>
<body>
<center>
    <h1>Упс...</h1>
    <h2><%=exception.getMessage() %><br/> </h2>
</center>
</body>
</html>