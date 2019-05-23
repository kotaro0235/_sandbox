<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>TASK管理表</title>
</head>
<body>
	<div id="input">
		<form method="post" action="./register">		
			<h1>タスク登録</h1>
			<table>
				<tr>
					<th>タスク</th>
					<td>
						<input type="text" name="task" size="50"/>
					</td>
				</tr>
				<tr>
					<th>作業量</th>
					<td>
						<input type="text" name="size" size="15"/>
						<select name="sizeType">
							<option value="ページ">ページ</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>予定工数</th>
					<td>
						<input type="text" name="estimate" size="4"/>
					</td>
				</tr>
				<tr>
					<th>期限</th>
					<td>
						<input type="text" name="limit" size="8"/>
					</td>
				</tr>
			</table>
			<button type="submit">登録</button>
		</form>
	</div>

</body>
</html>