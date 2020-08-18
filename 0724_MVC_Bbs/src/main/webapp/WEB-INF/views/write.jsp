<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/summernote-lite.min.css">

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
<body>
	<div id="bbs">
	<form action="write" method="post" 
	encType="multipart/form-data">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="subject" size="45"/></td>
				</tr>
				<tr>
					<th>이름:</th>
					<td><input type="text" name="writer" size="12"/></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea name="content" 
					id="content"
					cols="50" rows="8"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" name="file"/></td>
				</tr>

				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12"/></td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" value="보내기"
						onclick="sendData()"/>
						<input type="button" value="다시"/>
						<input type="button" value="목록"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>	
	
<!-- summernote JS -->
<script src="resources/js/summernote-lite.min.js"></script>	
<script src="resources/js/lang/summernote-ko-KR.js"></script>	
<script>
	$(function(){
		//에디터 설정
		$("#content").summernote({
			height: 300,
			width: 450,
			lang: "ko-KR",
			callbacks:{
				onImageUpload: function(files, editor){
					//이미지가 에디터에 추가될 때마다 수행하는 곳!
					
					//이미지를 넣으면 배열로 인식된다.
					for(var i=0; i<files.length; i++)
						sendFile(files[i], editor);
				}
			}
		});
	});
	
	function sendFile(file, editor){
		//이미지를 서버로 업로드 시키기 위해 비동기식 통신을 수행하자!
		
		//파일 파라미터를 전달하기 위해 폼객체 준비!
		var frm = new FormData();
		//<form encType="multipart/form-data"
		
		//보내고자 하는 파일을 등록한다.
		frm.append("file", file);
		
		//비동기식 통신
		$.ajax({
			url: "saveImage",
			type: "post",
			dataType: "json",
			//파일을 보낼때는 일반적인 테이터 전송이
			//아님을 증명해야 한다.
			contentType: false,
			processData: false,
			data: frm
		}).done(function(data){
			//에디터에 image경로를 넣어 준다.
			$("#content").summernote(
				"editor.insertImage", data.url);
		}).fail(function(err){
			console.log(err);
		});
	}


	function sendData(){
		//필요한 유효성 검사!
					//현재 문서.여러개의 폼중에첫번째.subject라는 이름의.값
		var subject = document.forms[0].subject.value;
		
		if(subject.trim().length < 1){
			//제목을 입력하지 않은 경우
			alert("제목을 입력하세요!");
			document.forms[0].subject.focus();
			return;
		}
		/* if(writer.trim().length < 1){
			//글쓴이를 입력하지 않은 경우
			alert("작성자를 입력하세요!");
			dcounment.forms[0].subject.focus();
		} */

		document.forms[0].submit();
	}
</script>	
</body>
</html>












    