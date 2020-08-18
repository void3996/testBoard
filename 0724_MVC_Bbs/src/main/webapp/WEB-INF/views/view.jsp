<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin-left:10px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	
	.odd {background:silver}
	
		
</style>

</head>
<body onload="init()">
	<div id="bbs">
	<form method="post" >
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<colgroup>
				<col width="90"/> 
				<col width="*"/> 
				<col width="80"/> 
				<col width="80"/> 
			</colgroup>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${vo.subject }</td><!-- 제목이 찍혀야함  -->
					<th>조회수: </th>
					<td>${vo.hit }</td>
				</tr>
				<c:if test="${vo.file_name != null and fn:length(vo.file_name) > 4}">
				<tr><!-- 첨부파일이 있으면 나오고 없을 경우 출력되면 안됨  -->
					<th>첨부파일:</th>
					<td colspan="3"><a href="javascript:fDown('${vo.file_name }')">
						${vo.file_name }
					</a></td><!-- a태그는 함수를 기본적으로 인식 못한다 그래서 javascript로 처리-->
					
				</tr>
				</c:if>
				<tr>
					<th>이름:</th>
					<td colspan="3">${vo.writer }</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td colspan="3">${vo.content }</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="button" value="수정" 
						onclick="goEdit()"/>
						<input type="button" value="삭제"/>
						<input type="button" value="목록" 
						onclick="goList()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<form method="post" action="ans_write.jsp">
		이름:<input type="text" name="writer"/><br/>
		내용:<textarea rows="4" cols="55" name="comm"></textarea><br/>
		비밀번호:<input type="password" name="pwd"/><br/>
		
		
		<input type="hidden" name="b_idx" value="">
		<input type="hidden" name="index" value=""/>
		<input type="submit" value="저장하기"/> 
	</form>
	
	댓글들<hr/>
	
		<div><!-- 댓글들이 여러개 나와야함 -->
			이름:글쓴이 &nbsp;&nbsp;
			날짜:날짜<br/>
			내용:댓글 내용
		</div>

	</div>
	
	<form method="post" name="f">
		<input type="hidden" id="b_idx" name="b_idx" value="${vo.b_idx }"/>
		<input type="hidden" id="nowPage" name="nowPage" value="${nowPage }"/>
		<input type="hidden" id="bname" name="bname" value="${vo.bname }"/>
		<input type="hidden" id="f_name" name="f_name"/>
		
	</form>
	<input type="hidden" id="ch" name="ch" value="${ch }"/>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
		function goEdit() {
			//document.f.method = "get";
			document.f.action = "edit";
			document.f.submit();
		}
		
		function goList() {
			// $("#nowPage").val(nowPage); 
			document.f.action = "list";
			document.f.submit();
		}
		
		function fDown(fname) {
			//alert(fname);
			//인자로 넘어온 파일명을 현재 문서에 f라는 이름을 가진 form 객체에서
			//이름이 f_name인 요소의 값으로 지정하여 서버로 보낸다.
			//그래서 서버가 가지는 파일을 그라이언트에게 다운 받도록
			//유도한다.
			document.f.f_name.value = fname;
			document.f.action = "FileDownload";
			document.f.submit();
		}
		function init() {
			//현재 문서에서 (document)에서 아이디가 ch인 요소를 검색한다.
			var ch = document.getElementById("ch");
			
			if(ch.value == 'true')
				alert('수정 완료!');
		}
	</script>
</body>
</html>