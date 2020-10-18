<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Currency Converter - Lab 16</title>
</head>
<body>

	<form action='CurrencyConverter' method='post'>

		<input type='text' name='amount' /> <select name="c1">
			<c:forEach items="${data}" var="c">
				<option>${c.key}</option>
			</c:forEach>
		</select> = ? <select name="c2">
			<c:forEach items="${data}" var="c">
				<option>${c.key}</option>
			</c:forEach>
		</select> <input type='submit' name='convert' value='Convert' />

	</form>


</body>
</html>