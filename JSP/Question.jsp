<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lab 15</title>
</head>
<body>
<p>${currentQuestion.description}</p>
<p>1. ${currentQuestion.answerA}</p>
<p>2. ${currentQuestion.answerB}</p>
<p>3. ${currentQuestion.answerC}</p>
<p>Correct Answer: ${currentQuestion.correctAnswer}</p><br>
<a href="DrivingTestBrowser?index=${index+1}">Next</a>


</body>
</html>