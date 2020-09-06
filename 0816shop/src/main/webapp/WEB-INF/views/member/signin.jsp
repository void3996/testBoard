<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Valpago</title>
</head>
<body>

<div id="root">
	<!-- header 시작  -->
	<header id="header">
		<div id="header_box">
			<%@ include file="../include/header.jsp" %>
		</div>
	</header>
	<!-- hearder 끝 -->	
	<nav id="nav">
		<div id="nav_box">
			<%@ include file="../include/nav.jsp" %>
		</div>
	</nav>
	
	<section id="container">
		<div id="container_box">
	
<section id="content">
 <form role="form" method="post" autocomplete="off">
 
  <div class="input_area">
   <label for="memId">아이디</label>
   <input type="text" id="memId" name="memId" required="required" placeholder="ID"/>      
  </div>
  
  <div class="input_area">
   <label for="memPassword">패스워드</label>
   <input type="password" id="memPassword" name="memPassword" required="required" placeholder="Password"/>      
  </div>
       
  <button type="submit" id="signin_btn" name="signin_btn">로그인</button>
  <button type="button">아이디/비밀번호 찾기</button>
  
  <c:if test="${msg == false}">
   <p style="color:#f00;">로그인에 실패했습니다.</p>
  </c:if>
  
 </form>   
</section>

		</div>
	</section>
	<!-- container 끝 -->

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>		
	</footer>
	
</div>
</body>
</html>