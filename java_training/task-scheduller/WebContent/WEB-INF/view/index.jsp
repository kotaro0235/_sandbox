<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World</title>
</head>
<body>
	<% String userName = (String) request.getAttribute("userName"); %>
	こんにちは、<c:out value="${userName}"/>さん！
	
	<% if ("Guest".equals(userName)) { %>
	<form method="post" action="./HelloServlet">
		名前を入力してください：<input type="text" name="name">
		<button type="submit">送信</button>
	</form>
	<% } %>
</body>
</html>