<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
	#bbs table th,#bbs table td {
	    text-align:center;
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
	
	/* paging */
	
	table tfoot ol.paging {
	    list-style:none;
	}
	
	table tfoot ol.paging li {
	    float:left;
	    margin-right:8px;
	}
	
	table tfoot ol.paging li a {
	    display:block;
	    padding:3px 7px;
	    border:1px solid #00B3DC;
	    color:#2f313e;
	    font-weight:bold;
	}
	
	table tfoot ol.paging li a:hover {
	    background:#00B3DC;
	    color:white;
	    font-weight:bold;
	}
	
	.disable {
	    padding:3px 7px;
	    border:1px solid silver;
	    color:silver;
	}
	
	.now {
	   padding:3px 7px;
	    border:1px solid #ff4aa5;
	    background:#ff4aa5;
	    color:white;
	    font-weight:bold;
	}
	
	.empty {
		height: 40px;
		text-align: center;
	 }
		
</style>
</head>
<body>
	<div id="bbs">
		<table summary="게시판 목록">
			<caption>게시판 목록</caption>
			<thead>
				<tr class="title">
					<th class="no">번호</th>
					<th class="subject">제목</th>
					<th class="writer">글쓴이</th>
					<th class="reg">날짜</th>
					<th class="hit">조회수</th>
				</tr>
			</thead>
				
			<!-- jstl 을 이용해서  -->
			<tfoot>
                      <tr>
                          <td colspan="4">
                              <%--페이징 코드 --%>
                                  ${pageCode }
               

<%-- 
	<ol class="paging">
	<li><a href="#">&lt;</a></li>

	<li class="now">1</li>
         
	<li><a href="#">2</a></li>


 
		<li><a href="#">&gt;</a></li> --%>
	
                              </ol>
                          </td>
						  <td>
							<input type="button" value="글쓰기"
			onclick="javascript:location.href='write'"/>
						  </td>
                      </tr>
                  </tfoot>
			<tbody>
						<!-- list가 null이 아닐때, null 값 체크 중요 없을경우 nullpointExeption 발생 가능성 높다-->
			<c:if test="${list ne null}"> 
				<c:forEach items="${list }" var="vo" varStatus="st">
				<tr>
					<!-- 오름차순이다  -->
					<td>${rowTotal - ((nowPage-1)*blockList+st.index) }</td>
					<td style="text-align: left"><!-- 이부분 수정해야함  -->
						<a href="javascript:viewData('${vo.b_idx }','${nowPage}','BBS')">
						${vo.subject }
						</a><!-- post 방식으로 보내려면 a태그에 함수를 넣어야함 submit방식 -->
					</td>
					<td>${vo.writer }</td>
					<td>${fn:substring(vo.write_date, 0, 10) }</td>
					<td>${vo.hit }</td>
				</tr>
				</c:forEach>
			</c:if>
			<!-- list가 null과 같거나 list가 비어있다. ListController에 mv.addObject("list", ar); 가 없을경우-->
			<c:if test="${list eq null or empty list}">
				<tr>
					<!-- 비어있는 형태를 어떤식으로 표현할건지 -->
					<td colspan="5" class="empty">
						등록된 게시물이 없습니다.
					</td>
				</tr>
			</c:if>
			</tbody>
			
			
		</table>
		
	</div>
	
	<%-- 보기 기능을 위한 폼 --%>
	<form action="view" method="post" name="f">
		<input type="hidden" id="b_idx" name="b_idx">
		<input type="hidden" id="nowPage" name="nowPage">
		<input type="hidden" id="bname" name="bname">
	</form>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

	<script>
		function viewData(b_idx, nowPage, bname) {
			//3가지 방법
			
			document.f.b_idx.value = b_idx;
			/* id로 찾은다음 벨류를 지정해라 = nowpage */
			document.getElementById("nowPage").value = nowPage;
			//jQuery로 접근하는 방법
			
			$("#bname").val(bname);
			
			document.f.submit();
		}
	</script>
</body>
</html>
