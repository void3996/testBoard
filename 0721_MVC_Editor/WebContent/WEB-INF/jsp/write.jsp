
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
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<!-- include summernote-ko-KR -->
<script src="asset/js/summernote-ko-KR.min.js"></script>
<script type="text/javascript">

	$(function() {
		
		$("#content").summernote({
			height: 300,
			minHeight: 200,
			maxHeight: 350,
			focus: true,
			lang: 'ko-KR',
			callbacks:{
				onImageUpload: function(files, editor, welEdit){
					//에디터에 이미지 올리면 무조건 수행하는 곳
					//console.log('Image upload:'+files);
					//서버에 이미지를 올리려고 잡아두는 중
					
					//이미자 배열로 인식된다. 이것을 서버로 비동기식 통신을 하는 함수를 호출한다.
					
					sendFile(files[0], editor, welEdit);
				}
			}
		});
		$("#content").summernote('lineHeight', 0.5);
	});
	
	function sendFile(file, editor, welEdit) {
		
		//파라미터를 전달하기 위해 form 객체를 만든다.
		//파일첨부시에만 적용하자!
		var frm = new FormData();
		//위의 frm 객체에 f라는 파라미터를 지정!
		//file이 f에 들어감
		frm.append("f", file);
		//비동기식 통신
		$.ajax({
			url: "saveImage.okj",	//어디로 보낼건지
			data: frm,
			cache: false,
			contentType: false,	//기본적으로 컨텐트 타입이 application, 그럴경우 파일첨부를 못함
			processData: false,
			type: "POST",	//get 방식과 post 방식중 POST 방식으로
			dataType: "JSON"	//나중에 받을 데이터의 형식을 JSON으로 받겠다
			
		}).done(function(data) {
			//서버에서 정상적인 처리가 완료되었을때 
			//서버에서 반환하는 값을 data로 받아서 수행
			//console.log(data);
			
			var path = data.path;	//이미지 경로
			var fname = data.fname;	//이미지 파일명
			
			//var image = $("<img>").attr("src", path+"/"+fname);
			//에디터에 정의한 img태그를 보여준다.
			//$("#content").summernote('insertNode', image[0]);
			//다른 방법
			$("#content").summernote("editor.insertImage",path+"/"+fname);
			
		}).fail(function(err) {
			//서버에서 오류 발생시 수행하는 곳
			console.log(err);
		});
		
	}
	

	function sendData(){
		for(var i=0 ; i<document.forms[0].elements.length ; i++){
			if(document.forms[0].elements[i].value == ""){
				alert(document.forms[0].elements[i].name+
						"를 입력하세요");
				document.forms[0].elements[i].focus();
				return;//수행 중단
			}
		}

//		document.forms[0].action = "test.jsp";
		document.forms[0].submit();
	}
	
	function goList(){
		
		document.frm.submit();
	}
</script>


</head>
<body>


	<div id="bbs">
	<form action="write_ok.okj" method="post" 
	encType="multipart/form-data">
		
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="title" size="45"/></td>
				</tr>
				
				<tr>
					<th>내용:</th>
					<td><textarea name="content" id="content" cols="50" 
							rows="8"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<td><input type="file" name="file"/></td>
				</tr>

				<tr>
					<td colspan="2">
						<input type="button" value="보내기"
						onclick="sendData()"/>
						<input type="button" value="다시"/>
						<input type="button" value="목록"
						 onclick="goList()"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	
	<form method="post" action="" name="frm">
		<input type="hidden" name="cPage" value="1"/>
	</form>
</body>
</html>












    