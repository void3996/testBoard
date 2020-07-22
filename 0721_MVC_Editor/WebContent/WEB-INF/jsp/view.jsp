<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
<script type="text/javascript">
	function down(fn){
		//현재문서에서 이름이 frm이라는 폼을 검색하여 그 안에 있는
		//fname의 값을 fn의 값으로 변경한다.
		document.frm.fname.value = fn;
		document.frm.action = "download.jsp";
		
		document.frm.submit();
	}
	
	function goList(){
		document.frm.action = "list.jsp";
		document.frm.submit();
	}
	
	function modify(ff){
		ff.action = "edit.jsp";
		ff.submit();
	}
	
	function del(ff){
		if(confirm("정말 삭제 하시겠습니까?")){
			ff.action = "delete.jsp";
			ff.submit();
		}
	}
	
	function delWin(){
		alert("삭제하지 못했습니다.");
	}
	
	function delComm(ff){
		if(confirm("정말 삭제 하시겠습니까?")){
			ff.action = "del_comm.jsp";
			ff.submit();
		}
	}
</script>
</head>
<body >
	<div id="bbs">
	<form method="post" name="frm">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td></td>
				</tr>

				<tr>
					<th>첨부파일:</th>
					<td>
					
						<a href="">
							
						</a>
						()
					
						<input type="hidden" name="fname"/>
						<input type="hidden" name="cPage"
							value=""/>
						<input type="hidden" name="b_idx"
							value=""/>
					</td>
				</tr>
				
				<tr>
					<th>이름:</th>
					<td></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td></td>
				</tr>
				
				<tr>
					<td colspan="2">
					
						<input type="button" value="수정" 
						 onclick="modify(this.form)"/>
						<input type="button" value="삭제" 
						 onclick="del(this.form)"/>
					
						<input type="button" value="목록"
							onclick="goList()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	
	</div>
</body>
</html>











