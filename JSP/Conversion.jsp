<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conversion - Lab 16</title>
</head>
<body>

	<p>${amount}
		${c1} =
		<fmt:formatNumber type="number" maxIntegerDigits="3" value="${result}" />
		${c2}
	</p>
	<p>
		<a href='CurrencyConverter'>Back</a>
	</p>

</body>
</html>