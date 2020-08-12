<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- true면 저장완료 !붙이면 false-->
<c:if test="${chk }">저장완료</c:if>
<c:if test="${!chk }">저장실패!</c:if>
</body>
</html>