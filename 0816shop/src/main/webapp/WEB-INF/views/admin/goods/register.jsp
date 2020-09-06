<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>상품 등록 화면</title>
	
<script src="/resources/jquery/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.min.css">
<script src="/resources/bootstrap/bootstrap.min.js"></script>
	
<style>
	body { font-family:'맑은 고딕', verdana; padding:0; margin:0; }
	ul { padding:0; margin:0; list-style:none;  }
 
	div#root { width:90%; margin:0 auto; }
	
	header#header { font-size:60px; padding:20px 0; }
	header#header h1 a { color:#000; font-weight:bold; }
	
	nav#nav { padding:10px; text-align:right; }
	nav#nav ul li { display:inline-block; margin-left:10px; }
 
 	section#container { padding:20px 0; border-top:2px solid #eee; border-bottom:2px solid #eee; }
	section#container::after { content:""; display:block; clear:both; }
	aside { float:left; width:200px; }
	div#container_box { float:right; width:calc(100% - 200px - 20px); }
	
	aside ul li { text-align:center; margin-bottom:10px; }
	aside ul li a { display:block; width:100%; padding:10px 0;}
	aside ul li a:hover { background:#eee; }
	
	footer#footer { background:#f9f9f9; padding:20px; }
	footer#footer ul li { display:inline-block; margin-right:10px; } 
</style>

<style>
.inputArea { margin:10px 0; }
select { width:100px; }
label { display:inline-block; width:70px; padding:5px; }
label[for='gdsDes'] { display:block; }
input { width:150px; }
textarea#gdsDes { width:400px; height:180px; }

.select_img img { margin:20px 0; }

</style>
		 
</head>
<body>
<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="../include/header.jsp" %>
		</div>
	</header>

	<nav id="nav">
		<div id="nav_box">
			<%@ include file="../include/nav.jsp" %>
		</div>
	</nav>
	
	<section id="container">
		<aside>
			<%@ include file="../include/aside.jsp" %>
		</aside>
		<div id="container_box">
			<h2>상품 등록</h2>
			
			<form role="form" method="post" autocomplete="off" enctype="multipart/form-data">

			<div class="inputArea"> 
			 <label>1차 분류</label>
			 <select class="category1">
			  <option value="">전체</option>
			 </select>
			
			 <label>2차 분류</label>
			 <select class="category2" name="cateCode">
			  <option value="">전체</option>
			 </select>
			</div>
			
			<div class="inputArea">
			 <label for="gdsName">상품명</label>
			 <input type="text" id="gdsName" name="gdsName" />
			</div>
			
			<div class="inputArea">
			 <label for="gdsPrice">상품가격</label>
			 <input type="text" id="gdsPrice" name="gdsPrice" />
			</div>
			
			<div class="inputArea">
			 <label for="gdsStock">상품수량</label>
			 <input type="text" id="gdsStock" name="gdsStock" />
			</div>
			
			<div class="inputArea">
			 <label for="gdsDes">상품소개</label>
			 <textarea rows="5" cols="50" id="gdsDes" name="gdsDes"></textarea>
			</div>

			<div class="inputArea">
			 <button type="submit" id="register_Btn" class="btn btn-primary regBtn">등록</button>
			</div>
			
			</form>
			
			<div class="inputArea">
		 	 <label for="gdsImg">이미지</label>
	<div class='uploadDiv'>
		<input type='file' name='uploadFile' multiple>	<button id='uploadBtn'>Upload</button>
	</div>

	<div class='uploadResult'>
		<ul>

		</ul>
	</div>
	
<style>
.bigPictureWrapper {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  top:0%;
  width:100%;
  height:100%;
  background-color: gray; 
  z-index: 100;
}

.bigPicture {
  position: relative;
  display:flex;
  justify-content: center;
  align-items: center;
}
</style>

<div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>	
			 <script>
			 
			 var formObj = $("form");
			 
			 $(".regBtn").on("click", function(e){
				    
				    e.preventDefault();
				    
				    console.log("submit clicked");
				    
				    
				    var str = "";
				    
				    $(".uploadResult li").each(function(i, obj){
				      
				      var jobj = $(obj);
				      
				      console.dir(jobj);
				      console.log("-------------------------");
				      console.log(jobj.data("filename"));
				      
				      
				      str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
				      str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
				      str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
				      str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
				      
				    });
				    
				    console.log(str);
				    
				    formObj.append(str).submit();
				    
				  });
			 
			 var uploadResult = $(".uploadResult");
				
			 	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
				var maxSize = 5242880; //5MB

				function checkExtension(fileName, fileSize) {

					if (fileSize >= maxSize) {
						alert("파일 사이즈 초과");
						return false;
					}

					if (regex.test(fileName)) {
						alert("해당 종류의 파일은 업로드할 수 없습니다.");
						return false;
					}
					return true;
				}

				var cloneObj = $(".uploadDiv").clone();

				$("#uploadBtn").on("click", function(e) {


					var formData = new FormData();

					var inputFile = $("input[name='uploadFile']");

					var files = inputFile[0].files;

					//console.log(files);

					for (var i = 0; i < files.length; i++) {

						if (!checkExtension(files[i].name, files[i].size)) {
							return false;
						}

						console.log(files[i]);
						formData.append("uploadFile", files[i]);

					}

					console.log(formData);

 					$.ajax({
						url : '/uploadAjaxAction',
						processData : false,
						contentType : false,
						data : formData,
						type : 'POST',
						dataType : 'json',
						success : function(result) {

							console.log(result);

							showUploadedFile(result);

							$(".uploadDiv").html(cloneObj.html());

						}
					}); //$.ajax
 
				});	
				
				
				function showUploadedFile(uploadResultArr){
					 
					   var str = "";
					   
					   $(uploadResultArr).each(function(i, obj){
					     
							var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
							str += "<li data-path='"+obj.uploadPath+"'";
							str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
							str +" ><div>";
							str += "<span> "+ obj.fileName+"</span>";
							str += "<button type='button' data-file=\'"+fileCallPath+"\' "
							str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
							str += "<img src='/display?fileName="+fileCallPath+"'>";
							str += "</div>";
							str +"</li>";
							
					   });
					   
					   uploadResult.append(str);
					 }	
				
				function showImage(fileCallPath){
					  
					  //alert(fileCallPath);
					
					  $(".bigPictureWrapper").css("display","flex").show();
					  
					  $(".bigPicture")
					  .html("<img src='/display?fileName="+ encodeURI(fileCallPath)+"'>")
					  .animate({width:'100%', height: '100%'}, 1000);

					}
					
					$(".bigPictureWrapper").on("click", function(e){
					  $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
					  
					  setTimeout(function() {
						  $(".bigPicture").hide();
						  $(".bigPictureWrapper").hide();
					  }, 1000);
					  
					});

			 </script>
			</div>

			
		</div>
	</section>

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>		
	</footer>

</div>

<script>
// 컨트롤러에서 데이터 받기
var jsonData = JSON.parse('${category}');
console.log(jsonData);

var cate1Arr = new Array();
var cate1Obj = new Object();

// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
for(var i = 0; i < jsonData.length; i++) {
 
 if(jsonData[i].level == "1") {
  cate1Obj = new Object();  //초기화
  cate1Obj.cateCode = jsonData[i].cateCode;
  cate1Obj.cateName = jsonData[i].cateName;
  cate1Arr.push(cate1Obj);
 }
}

// 1차 분류 셀렉트 박스에 데이터 삽입
var cate1Select = $("select.category1")

for(var i = 0; i < cate1Arr.length; i++) {
 cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>"
      + cate1Arr[i].cateName + "</option>"); 
}

$(document).on("change", "select.category1", function(){

	 var cate2Arr = new Array();
	 var cate2Obj = new Object();
	 
	 // 2차 분류 셀렉트 박스에 삽입할 데이터 준비
	 for(var i = 0; i < jsonData.length; i++) {
	  
	  if(jsonData[i].level == "2") {
	   cate2Obj = new Object();  //초기화
	   cate2Obj.cateCode = jsonData[i].cateCode;
	   cate2Obj.cateName = jsonData[i].cateName;
	   cate2Obj.cateCodeRef = jsonData[i].cateCodeRef;
	   
	   cate2Arr.push(cate2Obj);
	  }
	 }
	 
	 var cate2Select = $("select.category2");
	 
	 /*
	 for(var i = 0; i < cate2Arr.length; i++) {
	   cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
	        + cate2Arr[i].cateName + "</option>");
	 }
	 */
	 
	 cate2Select.children().remove();

	 $("option:selected", this).each(function(){
	  
	  var selectVal = $(this).val();  
	  cate2Select.append("<option value='" + selectVal + "'  >전체</option>");
	  
	  for(var i = 0; i < cate2Arr.length; i++) {
	   if(selectVal == cate2Arr[i].cateCodeRef) {
	    cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
	         + cate2Arr[i].cateName + "</option>");
	   }
	  }
	  
	 });
	 
	});
</script>

<script>
var regExp = /[^0-9]/gi;

$("#gdsPrice").keyup(function(){ numCheck($(this)); });
$("#gdsStock").keyup(function(){ numCheck($(this)); });

function numCheck(selector) {
	var tempVal = selector.val();
	selector.val(tempVal.replace(regExp, ""));
}
</script>
</body>
</html>
