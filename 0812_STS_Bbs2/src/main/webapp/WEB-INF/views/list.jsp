<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %> 
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="resources/css/text.css" rel="stylesheet" type="text/css">

</head>
<body topmargin=0 leftmargin=0 marginwidth="0" marginheight="0"> 

<!--주요내용시작 -->
<form name="ff2" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tbody>
	  <tr>
		<td valign="top">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			  <td align="center" height="10"></td>
			</tr>
			<tr>
			  <td align="center"><u><b>BBS 목록</b><u></td>
			</tr>
			<tr>
			  <td align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td>&nbsp;</td>
				  </tr>
				</table>
				<table width="556" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="2" bgcolor="#C3C3C3"></td>
				  </tr>
				  <tr>
					<td bgcolor="#E5E5E5">
					<table width="100%" border="0" cellspacing="1" cellpadding="2">
						<tr>
						  <td height="20" align="center" bgcolor="#669AB3" width="56"><font color="#FFFFFF">번호</font></td>
						  <td height="20" align="center" bgcolor="#669AB3" width="270"><font color="#FFFFFF">제목</font></td>
						  <td height="20" align="center" bgcolor="#669AB3" width="80"><font color="#FFFFFF">글쓴이</font></td>
						  <td height="20" align="center" bgcolor="#669AB3" width="100"><font color="#FFFFFF">날짜</font></td>
						  <td height="20" align="center" bgcolor="#669AB3" width="50"><font color="#FFFFFF">조회수</font></td>
						</tr>
		   <c:forEach items="${list }" varStatus="st" var="vo">
						<tr>
						  <td bgcolor="#F2F7F9">
							${rowTotal -((nowPage-1)*blockList+st.index) }
						  </td>
						  <td bgcolor="#F2F7F9" style="text-align:left">
						<%--
							step값 만큼 들여쓰기 하는 반복문
						 --%>	
						 <c:forEach begin="1" end="${vo.step }">
						 	<c:out value="&nbsp;&nbsp;" escapeXml="false"/>
						 </c:forEach>
						 
						<%-- step이 0이 아니면 화살표 이미지 출력 --%>
						<c:if test="${vo.step ne 0 }">
							<img src="resources/images/arrow.JPG"/>
						</c:if>	
							<a href="view?seq=${vo.seq }&nowPage=${nowPage}"> 
								${vo.title }
							</a>
						  </td>
						  <td bgcolor="#F2F7F9">${vo.writer }</td>
						  <td bgcolor="#F2F7F9">${vo.regdate }</td>
						  <td bgcolor="#F2F7F9">${vo.hit }</td>
						</tr>
		   </c:forEach>
		   
		   <%--게시물이 없는 경우 --%>
		   <c:if test="${list eq null or empty list }">
					<tr>
					  <td bgcolor="#F2F7F9" colspan="5" height="70" align="center">등록된 게시물이 없습니다.</td>
					</tr>
		   </c:if>
					  </table></td>
				  </tr>
				</table>
				<table width="556" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<td height="20" valign="middle"><img src="resources/images/sub_it/point_line.gif" width="556" height="3"></td>
				  </tr>
				  <tr>
					<td align="right"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
						  <td width="315" align="left">
							  ${pageCode }
						  </td>
						  <td width="241" align="right"> <img src="resources/images/but_write.gif" width="56" height="21" style="cursor:pointer" 
						  	onClick="writeForm()">
						  </td>
						</tr>
					  </table></td>
				  </tr>
				</table></td>
			</tr>
			<tr>
			  <td height="19">&nbsp;</td>
			</tr>
		  </table>
		</td>
	  </tr>
  </tbody>
</table>
</form>

 <!--주요내용끝 -->
 <form name="frm" method="post">
 	<input type="hidden" name="cPage" 
 		value="${nowPage }"/>
 </form>
 
 <script>
 	function writeForm(){
 		document.frm.action = "writeForm";
 		document.frm.submit();
 	}
 </script>
 
</body>
</html>
    