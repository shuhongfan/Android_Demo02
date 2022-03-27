<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>Hello World!</h2>

<%
    Thread.sleep(2000);
%>

<h3>
    请求方式:
    <%=request.getMethod()%>
</h3>

<h3>
    Hello:
    <%=request.getParameter("name")%>
    ,你的年龄是:
    <%=request.getParameter("age")%>
</h3>
</body>
</html>
