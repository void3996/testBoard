<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Valpago Admin</title>
	
<script src="/resources/jquery/jquery-3.5.1.min.js"></script>

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
			
			<form role="form" method="post" autocomplete="off">
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
				
			</form>
		</div>
	</section>

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>		
	</footer>

</div>
<script>
/* 컨트롤러에서 모델로 보낸 값인 ${category}를 jsonData에 저장  
 * 
 */
var jsonData = JSON.parse('${category}');
console.log(jsonData);

var cate1Arr = new Array();
var cate10bj = new Object();

/* 1차 분류 셀렉트 박스에 삽입할 데이터준비 */
/*jsondata에서 level값이 1인 경우에만 cate1Obj에 추가  */
/* cate1Obj에 저장된 값을 cate1Arr 배열에 저장 */
/* cate1Obj에 cateCode와 cateName를 저장 */

for(var i = 0; i < jsonData.length; i++) {
	
	if (jsonData[i].level == "1") {
		cate10bj = new Object();	
		
		cate10bj.cateCode = jsonData[i].cateCode;
		cate10bj.cateName = jsonData[i].cateName;
		
		cate1Arr.push(cate10bj);
	}
}
/* 1차 분류 셀렉트 박스에 데이터 삽입  */
var cate1Select = $("select.category1")
/* cate1Arr에 저장된 값을 cate1Select에 추가 */
for (var i = 0; i < cate1Arr.length; i++) {
	
	cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>"
										+ cate1Arr[i].cateName + "</option>");	
	
}

//클래스가 category1인 select변수의 값이 바뀌었을 때 실행
$(document).on("change", "select.category1", function() {
	
	var cate2Arr = new Array();
	var cate20bj = new Object();
	
	/* 2차 분류 셀렉트 박스에 삽입할 데이터준비 */
	/*jsondata에서 level값이 2인 경우에만 cate2Obj에 추가  */
	/* 초기화 */
	/* cate1Obj에 cateCode와 cateName, cateCodeRef를 저장 */
	for(var i = 0; i < jsonData.length; i++) {
	
		if (jsonData[i].level == "2") {
			cate20bj = new Object();	
			
			cate2Obj.cateCode = jsonData[i].cateCode;
			cate2Obj.cateName = jsonData[i].cateName;
			cate2Obj.cateCodeRef = jsonData[i].cateCodeRef;
			
			cate2Arr.push(cate20bj);
			}
		}
	/* cate2Obj에 저장된 값을 cate2Arr 배열에 저장 */
	
	/* 2차 분류 셀렉트 박스에 데이터 삽입  */
	var cate2Select = $("select.category2");

	/* for (var i = 0; i < cate2Arr.length; i++) {
		 cate1Arr에 저장된 값을 cate1Select에 추가 
		cate2Select.append("<option value ='" + cate2Arr[i].cateCode + "'> "
							+ cate2Arr[i].cateName + "</option>");
	} 1차 분류를 선택했을때 2차  분류가 중복출력됨*/
	
	/* 기존에 있던 데이터 삭제 */
	cate2Select.children().remove();
	/*  현재 선택한 cate1Select의 값을 저장 */
	/*셀렉트 박스에 '전체'라고 표시될 데이터를 추가  */
	/* 변수 selectVal 에 현재 선택된 1차 분류값인 $(this).val()를 저장하고*/
	$("option:selected", this).each(function(){
	
		var selectVal = $(this).val();
		
		cate2Select.append("<option value='" + selectVal + "'>전체</option>");  
		// cate2Select의 '전체'에 현재 선택한 cate1Select와 같은 값 부여
		
		for(var i = 0; i < cate2Arr.length; i++) {
			// 현재 선택한 cate1Select의 값과 일치하는 cate2Arr의 데이터를 가져옴
			if(selectVal == cate2Arr[i].cateCodeRef) {
				cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
									+ cate2Arr[i].cateName + "</option>");
			}	
		}
	});
});
</script>

</body>
</html>